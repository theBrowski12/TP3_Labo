package cf.chercheur_service.API;

import cf.chercheur_service.DTO.RequestChercheurDTO;
import cf.chercheur_service.DTO.ResponseChercheurDTO;
import cf.chercheur_service.service.ChercheurServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Chercheurs",
                description = "Offre la gestion des chercheurs et la communication avec le microservice Enseignant.",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8086/" // adapte selon ton port Chercheur-Service
        )
)
@RestController
@RequestMapping("/v1/Chercheurs")
public class Chercheur_API {
    private final ChercheurServiceImpl chercheurService;

    public Chercheur_API(ChercheurServiceImpl chercheurService) {
        this.chercheurService = chercheurService;
    }

    // ✅ Créer un chercheur
    @PostMapping
    public ResponseEntity<ResponseChercheurDTO> addChercheur(@RequestBody RequestChercheurDTO dto) {
        ResponseChercheurDTO responseChercheurDTO = chercheurService.addChercheur(dto);
        return ResponseEntity.ok(responseChercheurDTO);
    }

    // ✅ Lister tous les chercheurs
    @GetMapping
    public ResponseEntity<List<ResponseChercheurDTO>> list() {
        List<ResponseChercheurDTO> responseChercheurDTOS = chercheurService.getAllChercheurs();
        return ResponseEntity.ok(responseChercheurDTOS);
    }

        // ✅ Récupérer un chercheur par ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseChercheurDTO> getById(@PathVariable Long id) {
        ResponseChercheurDTO chercheurDTO = chercheurService.getChercheurById(id);
        if (chercheurDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(chercheurDTO);
    }

        // ✅ Mettre à jour un chercheur
    @PutMapping("/{id}")
    public ResponseEntity<ResponseChercheurDTO> update(@PathVariable Long id, @RequestBody RequestChercheurDTO requestChercheurDTO) {
        ResponseChercheurDTO updated = chercheurService.updateChercheur(id, requestChercheurDTO);
        return ResponseEntity.ok(updated);
    }

        // ✅ Supprimer un chercheur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chercheurService.deleteChercheur(id);
        return ResponseEntity.ok().build();
    }

    // ✅ Statistique : nombre de chercheurs encadrés par un enseignant
    @GetMapping("/statistique/{id_enseignant}")
    public ResponseEntity<Long> getStatistique(@PathVariable Long id_enseignant) {
        long nbChercheurs = chercheurService.getNombreDeChercheurParEnseignant(id_enseignant);
        return ResponseEntity.ok(nbChercheurs);
    }

}

