package cf.enseignant_service.API;

import cf.enseignant_service.DTO.RequestEnseignantDTO;
import cf.enseignant_service.DTO.ResponseEnseignantDTO;
import cf.enseignant_service.service.EnseignantServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des Enseignants",
                description = "Offre la gestion des enseignants et la communication avec les microservices Chercheurs et Projet.",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8085/"
        )
)
@RestController
@RequestMapping("/v1/Enseignants")
public class Enseignant_API {
    private final EnseignantServiceImpl enseignantService;

    public Enseignant_API(EnseignantServiceImpl enseignantService) {
        this.enseignantService = enseignantService;
    }
    @PostMapping
    public ResponseEntity<ResponseEnseignantDTO> addEnseignant(@RequestBody RequestEnseignantDTO dto){
        ResponseEnseignantDTO responseEnseignantDTO = enseignantService.addEnseignant(dto);
        return ResponseEntity.ok(responseEnseignantDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseEnseignantDTO>> list(){
        List<ResponseEnseignantDTO> responseEnseignantDTOS = enseignantService.getAllEnseinants();
        return ResponseEntity.ok(responseEnseignantDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEnseignantDTO> getById(@PathVariable Long id){
        ResponseEnseignantDTO r = enseignantService.getEnseignantById(id);
        if(r==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEnseignantDTO> Update(@PathVariable Long id, @RequestBody RequestEnseignantDTO requestEnseignantDTO) {
        ResponseEnseignantDTO e = enseignantService.updateEnseignant(id, requestEnseignantDTO);
        return ResponseEntity.ok(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enseignantService.deleteEnseignantById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistique/{id}")
    public ResponseEntity<Map<String,Long>> statistique(@PathVariable Long id){
        return  ResponseEntity.ok(enseignantService.statistique(id));
    }
}
