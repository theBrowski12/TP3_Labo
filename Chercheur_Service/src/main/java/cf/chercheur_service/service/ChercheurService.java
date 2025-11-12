package cf.chercheur_service.service;

import cf.chercheur_service.DTO.RequestChercheurDTO;
import cf.chercheur_service.DTO.ResponseChercheurDTO;
import cf.chercheur_service.entities.Chercheur;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChercheurService {
    public ResponseChercheurDTO addChercheur(RequestChercheurDTO requestChercheurDTO);

    public List<ResponseChercheurDTO> getAllChercheurs();

    public ResponseChercheurDTO getChercheurById(Long id);

    public ResponseChercheurDTO updateChercheur(Long id_chercheur, RequestChercheurDTO requestChercheurDTO);

    public void deleteChercheur(Long id_chercheur);

    public long getNombreDeChercheurParEnseignant(Long id_enseignant);
}