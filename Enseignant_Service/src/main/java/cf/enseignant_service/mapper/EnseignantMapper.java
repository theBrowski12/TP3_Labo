package cf.enseignant_service.mapper;

import cf.enseignant_service.DTO.RequestEnseignantDTO;
import cf.enseignant_service.DTO.ResponseEnseignantDTO;
import cf.enseignant_service.entites.Enseignant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EnseignantMapper {
    public Enseignant DTO_TO_ENSEIGNANT(RequestEnseignantDTO requestEnseignantDTO) {
        Enseignant enseignant = new Enseignant();
        BeanUtils.copyProperties(requestEnseignantDTO, enseignant);
        return enseignant;
    }
    public ResponseEnseignantDTO Enseignant_TO_DTO(Enseignant enseignant) {
        ResponseEnseignantDTO dto = new ResponseEnseignantDTO();
        BeanUtils.copyProperties(enseignant, dto);
        return dto;
    }
}
