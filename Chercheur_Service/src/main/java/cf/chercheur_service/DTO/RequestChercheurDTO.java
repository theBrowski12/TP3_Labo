package cf.chercheur_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestChercheurDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String tel;
    private Long id_enseignant;
    private Long id_projet;
}
