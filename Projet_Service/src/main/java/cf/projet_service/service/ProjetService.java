package cf.projet_service.service;

import cf.projet_service.DTO.RequestProjetDTO;
import cf.projet_service.DTO.ResponseProjetDTO;

import java.util.List;

public interface ProjetService {
    ResponseProjetDTO addProjet(RequestProjetDTO dto);
    List<ResponseProjetDTO> getAllProjets();
    ResponseProjetDTO getProjetById(Long id);
    ResponseProjetDTO updateProjet(Long id, RequestProjetDTO dto);
    void deleteProjet(Long id);
    long nb_Projet_Enseignant(Long id_enseignant);
}
