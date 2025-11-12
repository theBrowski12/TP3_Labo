package cf.enseignant_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RequestEnseignantDTO {
    private String nom;
    private String prenom;
    private String cne;
    private String email;
    private String password;
    private String thematique;
}
