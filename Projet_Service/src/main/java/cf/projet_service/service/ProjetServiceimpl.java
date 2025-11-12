package cf.projet_service.service;

import cf.projet_service.DTO.RequestProjetDTO;
import cf.projet_service.DTO.ResponseProjetDTO;
import cf.projet_service.FeignClients.ChercheurRestFeign;
import cf.projet_service.entites.Projet;
import cf.projet_service.mapper.ProjetMapper;
import cf.projet_service.repository.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetServiceimpl implements ProjetService {
    private final ProjetRepository projetRepository;
    private final ProjetMapper projetMapper;
    private final ChercheurRestFeign chercheurRestFeign;

    public ProjetServiceimpl(ProjetRepository projetRepository, ProjetMapper projetMapper, ChercheurRestFeign chercheurRestFeign) {
        this.projetRepository = projetRepository;
        this.projetMapper = projetMapper;
        this.chercheurRestFeign = chercheurRestFeign;
    }

    @Override
    public ResponseProjetDTO addProjet(RequestProjetDTO dto) {
        Projet projet = projetMapper.DTO_to_Projet(dto);
        Projet savedProjet = projetRepository.save(projet);
        savedProjet.setChercheur(chercheurRestFeign.getChercheurByID(savedProjet.getId_chercheur()));
        return projetMapper.Projet_to_DTO(savedProjet);
    }

    @Override
    public List<ResponseProjetDTO> getAllProjets() {
        List<Projet> projets = projetRepository.findAll();
        List<ResponseProjetDTO> dtos = new ArrayList<>();

        for (Projet p : projets) {
            p.setChercheur(chercheurRestFeign.getChercheurByID(p.getId_chercheur()));
            dtos.add(projetMapper.Projet_to_DTO(p));
        }
        return dtos;
    }

    @Override
    public ResponseProjetDTO getProjetById(Long id) {
        Projet projet = projetRepository.findById(id).orElse(null);
        if (projet == null) return null;
        projet.setChercheur(chercheurRestFeign.getChercheurByID(projet.getId_chercheur()));
        return projetMapper.Projet_to_DTO(projet);
    }

    @Override
    public ResponseProjetDTO updateProjet(Long id, RequestProjetDTO dto) {
        Projet existing = projetRepository.findById(id).orElse(null);
        if (existing == null) throw new RuntimeException("Projet non trouvé");

        Projet newP = projetMapper.DTO_to_Projet(dto);
        if (newP.getTitre() != null) existing.setTitre(newP.getTitre());
        if (newP.getDescription() != null) existing.setDescription(newP.getDescription());
        if (newP.getId_chercheur() != null) existing.setId_chercheur(newP.getId_chercheur());
        if (newP.getId_enseignant() != null) existing.setId_enseignant(newP.getId_enseignant());

        Projet saved = projetRepository.save(existing);
        saved.setChercheur(chercheurRestFeign.getChercheurByID(saved.getId_chercheur()));
        return projetMapper.Projet_to_DTO(saved);
    }

    @Override
    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }

    @Override
    public long nb_Projet_Enseignant(Long id_enseignant) {
        return projetRepository.total_Projet_Enseignant(id_enseignant);
    }
}
