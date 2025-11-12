package cf.chercheur_service.FeignClients;

import cf.chercheur_service.models.Enseignant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Enseignant-service",url = "http://localhost:8085")
public interface EnseignantRestFeign {
    @GetMapping("/v1/Enseignants/{id}")
    Enseignant getEnseignant_ByID(@PathVariable Long id);
}
