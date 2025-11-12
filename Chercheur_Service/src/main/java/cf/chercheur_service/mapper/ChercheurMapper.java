package cf.chercheur_service.mapper;

import cf.chercheur_service.DTO.RequestChercheurDTO;
import cf.chercheur_service.DTO.ResponseChercheurDTO;
import cf.chercheur_service.entities.Chercheur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ChercheurMapper {
    public Chercheur DTO_to_Chercheur(RequestChercheurDTO requestChercheurDTO) {
        Chercheur chercheur = new Chercheur();
        BeanUtils.copyProperties(requestChercheurDTO, chercheur);
        return chercheur;
    }
    public ResponseChercheurDTO Chercheur_to_DTO(Chercheur chercheur) {
        ResponseChercheurDTO responseChercheurDTO = new ResponseChercheurDTO();
        BeanUtils.copyProperties(chercheur, responseChercheurDTO);
        return responseChercheurDTO;
    }
}
