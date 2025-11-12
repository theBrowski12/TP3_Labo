package cf.chercheur_service.DTO;

import cf.chercheur_service.models.Enseignant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChercheurDTO {
    private  Long id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private Long id_enseignant;
    private Long id_projet;
    private Enseignant enseignant;
}
