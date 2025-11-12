package cf.chercheur_service.entities;

import cf.chercheur_service.models.Enseignant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Chercheur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String tel;

    private Long id_enseignant;
    private Long id_projet;


    @Transient
    private Enseignant enseignant;
}
