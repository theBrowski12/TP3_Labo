package cf.projet_service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enseignant {
    private Long id;
    private String nom;
    private String prenom;
    private String cne;
    private String email;
    private String password;
    private String thematique;
}
