package cf.chercheur_service.service;

import cf.chercheur_service.DTO.RequestChercheurDTO;
import cf.chercheur_service.DTO.ResponseChercheurDTO;
import cf.chercheur_service.FeignClients.EnseignantRestFeign;
import cf.chercheur_service.entities.Chercheur;
import cf.chercheur_service.mapper.ChercheurMapper;
import cf.chercheur_service.repository.ChercheurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChercheurServiceImpl implements ChercheurService {
    private ChercheurRepository chercheurRepository;
    private ChercheurMapper chercheurMapper;
    private EnseignantRestFeign enseignantRestFeign;

    public ChercheurServiceImpl(ChercheurRepository chercheurRepository, ChercheurMapper chercheurMapper, EnseignantRestFeign enseignantRestFeign) {
        this.chercheurRepository = chercheurRepository;
        this.chercheurMapper = chercheurMapper;
        this.enseignantRestFeign = enseignantRestFeign;
    }

    @Override
    public ResponseChercheurDTO addChercheur(RequestChercheurDTO requestChercheurDTO) {
        Chercheur chercheur = chercheurMapper.DTO_to_Chercheur(requestChercheurDTO);
        Chercheur saved_chercheur= chercheurRepository.save(chercheur);
        return chercheurMapper.Chercheur_to_DTO(saved_chercheur);
    }

    @Override
    public List<ResponseChercheurDTO> getAllChercheurs() {
        List<Chercheur> chercheurList = chercheurRepository.findAll();
        List<ResponseChercheurDTO> responseChercheurDTOList = new ArrayList<>();
        for (Chercheur chercheur : chercheurList) {
            ResponseChercheurDTO dto=  chercheurMapper.Chercheur_to_DTO(chercheur);
            chercheur.setEnseignant(enseignantRestFeign.getEnseignant_ByID(chercheur.getId_enseignant()));
            dto.setEnseignant(enseignantRestFeign.getEnseignant_ByID(chercheur.getId_enseignant()));
            responseChercheurDTOList.add(dto);
        }

        return responseChercheurDTOList;
    }

    @Override
    public ResponseChercheurDTO getChercheurById(Long id) {
        Chercheur ch= chercheurRepository.findById(id).orElse(null);
        ch.setEnseignant(enseignantRestFeign.getEnseignant_ByID(ch.getId_enseignant()));
        return  chercheurMapper.Chercheur_to_DTO(ch);
    }

    @Override
    public ResponseChercheurDTO updateChercheur(Long id, RequestChercheurDTO requestChercheurDTO) {
        Chercheur existingChercheur = chercheurRepository.findById(id).orElse(null);
        if (existingChercheur == null) {
            throw new RuntimeException("Chercheur avec ID " + id + " non trouvé !");
        }

        Chercheur newChercheur = chercheurMapper.DTO_to_Chercheur(requestChercheurDTO);

        if (newChercheur.getNom() != null) existingChercheur.setNom(newChercheur.getNom());
        if (newChercheur.getPrenom() != null) existingChercheur.setPrenom(newChercheur.getPrenom());
        if (newChercheur.getEmail() != null) existingChercheur.setEmail(newChercheur.getEmail());
        if (newChercheur.getId_enseignant()!= null) existingChercheur.setId_enseignant(newChercheur.getId_enseignant());
        if (newChercheur.getId_projet() != null) existingChercheur.setId_projet(newChercheur.getId_projet());
        if (newChercheur.getTel() != null) existingChercheur.setTel(newChercheur.getTel());
        if (newChercheur.getPassword() != null) existingChercheur.setPassword(newChercheur.getPassword());


        Chercheur savedChercheur = chercheurRepository.save(existingChercheur);
        return chercheurMapper.Chercheur_to_DTO(savedChercheur);
    }

    @Override
    public void deleteChercheur(Long id) {
        chercheurRepository.deleteById(id);
    }

    @Override
    public long getNombreDeChercheurParEnseignant(Long id_enseignant) {
        return chercheurRepository.total_chercheur(id_enseignant);
    }
}
