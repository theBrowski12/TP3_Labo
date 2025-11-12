package cf.enseignant_service.service;

import cf.enseignant_service.DTO.RequestEnseignantDTO;
import cf.enseignant_service.DTO.ResponseEnseignantDTO;
import cf.enseignant_service.entites.Enseignant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnseignantService {
    public ResponseEnseignantDTO addEnseignant(RequestEnseignantDTO requestEnseignantDTO);
    public List<ResponseEnseignantDTO> getAllEnseinants();
    public ResponseEnseignantDTO getEnseignantById(Long id);
    void deleteEnseignantById(Long id);
    public ResponseEnseignantDTO updateEnseignant(Long id, RequestEnseignantDTO requestEnseignantDTO);

}
