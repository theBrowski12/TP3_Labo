package cf.projet_service.mapper;

import cf.projet_service.DTO.RequestProjetDTO;
import cf.projet_service.DTO.ResponseProjetDTO;
import cf.projet_service.entites.Projet;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProjetMapper {

    public Projet DTO_to_Projet(RequestProjetDTO dto) {
        Projet projet = new Projet();
        BeanUtils.copyProperties(dto, projet);
        return projet;
    }

    public ResponseProjetDTO Projet_to_DTO(Projet projet) {
        ResponseProjetDTO dto = new ResponseProjetDTO();
        BeanUtils.copyProperties(projet, dto);
        dto.setChercheur(projet.getChercheur());
        return dto;
    }
}