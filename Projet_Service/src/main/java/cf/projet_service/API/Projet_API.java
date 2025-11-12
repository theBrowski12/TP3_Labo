package cf.projet_service.API;

import cf.projet_service.DTO.RequestProjetDTO;
import cf.projet_service.DTO.ResponseProjetDTO;
import cf.projet_service.service.ProjetServiceimpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Projets",
                description = "Microservice de gestion des projets de recherche et communication avec le microservice Chercheur.",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8087/"
        )
)
@RestController
@RequestMapping("/v1/Projets")
public class Projet_API {
    private final ProjetServiceimpl projetService;

    public Projet_API(ProjetServiceimpl projetService) {
        this.projetService = projetService;
    }

    // ✅ Ajouter un projet
    @PostMapping
    public ResponseEntity<ResponseProjetDTO> addProjet(@RequestBody RequestProjetDTO dto) {
        ResponseProjetDTO response = projetService.addProjet(dto);
        return ResponseEntity.ok(response);
    }

    // ✅ Lister tous les projets
    @GetMapping
    public ResponseEntity<List<ResponseProjetDTO>> list() {
        List<ResponseProjetDTO> projets = projetService.getAllProjets();
        return ResponseEntity.ok(projets);
    }

    // ✅ Récupérer un projet par ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseProjetDTO> getById(@PathVariable Long id) {
        ResponseProjetDTO projet = projetService.getProjetById(id);
        if (projet == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(projet);
    }

    // ✅ Mettre à jour un projet
    @PutMapping("/{id}")
    public ResponseEntity<ResponseProjetDTO> update(@PathVariable Long id,
                                                    @RequestBody RequestProjetDTO dto) {
        ResponseProjetDTO updated = projetService.updateProjet(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ✅ Supprimer un projet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.ok().build();
    }

    // ✅ Statistique : nombre de projets encadrés par un enseignant
    @GetMapping("/statistique/{id_enseignant}")
    public ResponseEntity<Long> getStatistique(@PathVariable Long id_enseignant) {
        long nbProjets = projetService.nb_Projet_Enseignant(id_enseignant);
        return ResponseEntity.ok(nbProjets);
    }
}
