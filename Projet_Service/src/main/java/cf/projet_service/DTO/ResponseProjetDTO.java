package cf.projet_service.DTO;

import cf.projet_service.models.Chercheur;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProjetDTO {
    private Long id;
    private String titre;
    private String description;
    private Long id_chercheur;
    private Long id_enseignant;
    private Chercheur chercheur;
}
