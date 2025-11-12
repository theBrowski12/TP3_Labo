package cf.enseignant_service.service;

import cf.enseignant_service.DTO.RequestEnseignantDTO;
import cf.enseignant_service.DTO.ResponseEnseignantDTO;
import cf.enseignant_service.FeignClients.ChercheurRestFeign;
import cf.enseignant_service.FeignClients.ProjetRestFeign;
import cf.enseignant_service.entites.Enseignant;
import cf.enseignant_service.mapper.EnseignantMapper;
import cf.enseignant_service.repository.EnseignantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnseignantServiceImpl implements EnseignantService {
    private final EnseignantRepository repo;
    private final EnseignantMapper mapper;
    private ChercheurRestFeign chercheurRestFeign;
    private ProjetRestFeign projetRestFeign;
    public EnseignantServiceImpl(EnseignantRepository repo, EnseignantMapper mapper, ChercheurRestFeign chercheur, ProjetRestFeign projet) {
        this.repo = repo;
        this.mapper = mapper;
        this.chercheurRestFeign = chercheur;
        this.projetRestFeign = projet;
    }

    @Override
    public ResponseEnseignantDTO addEnseignant(RequestEnseignantDTO requestEnseignantDTO) {
        Enseignant enseignant = mapper.DTO_TO_ENSEIGNANT(requestEnseignantDTO);
        Enseignant saved_Enseignant= repo.save(enseignant);
        return mapper.Enseignant_TO_DTO(saved_Enseignant);
    }

    @Override
    public List<ResponseEnseignantDTO> getAllEnseinants() {
        List<Enseignant> enseignants = repo.findAll();
        List<ResponseEnseignantDTO> result = new ArrayList<>();
        for (Enseignant enseignant : enseignants) {
            result.add(mapper.Enseignant_TO_DTO(enseignant));
        }
        return result;
    }

    @Override
    public ResponseEnseignantDTO getEnseignantById(Long id) {
        Enseignant enseignant = repo.findById(id).orElse(null);
        return mapper.Enseignant_TO_DTO(enseignant);
    }

    @Override
    public void deleteEnseignantById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public ResponseEnseignantDTO updateEnseignant(Long id, RequestEnseignantDTO requestEnseignantDTO){
        Enseignant enseignant = repo.findById(id).orElse(null);
        Enseignant new_Enseignant = mapper.DTO_TO_ENSEIGNANT(requestEnseignantDTO);
        if (new_Enseignant.getNom()!=null) enseignant.setNom(new_Enseignant.getNom());
        if (new_Enseignant.getPrenom()!=null) enseignant.setPrenom(new_Enseignant.getPrenom());
        if (new_Enseignant.getEmail()!=null) enseignant.setEmail(new_Enseignant.getEmail());
        if (new_Enseignant.getCne()!=null) enseignant.setCne(new_Enseignant.getCne());
        if (new_Enseignant.getPassword()!=null) enseignant.setPassword(new_Enseignant.getPassword());
        if (new_Enseignant.getThematique()!=null) enseignant.setThematique(new_Enseignant.getThematique());
        Enseignant saved_Enseignant = repo.save(enseignant);
        return mapper.Enseignant_TO_DTO(saved_Enseignant);
    }

    public Map<String,Long> statistique(Long id){

        Long nb_chercheur = chercheurRestFeign.nb_chercheur_Enseignant(id);
        Long nb_projet = projetRestFeign.nb_Projet_Enseignant(id);

        Map<String, Long> Statistiques = new HashMap<>();
        Statistiques.put("Id de l'enseignant",id);
        Statistiques.put("nombre de projet",nb_projet);
        Statistiques.put("nombre de chercheur",nb_chercheur);
        return  Statistiques;
    }
}
