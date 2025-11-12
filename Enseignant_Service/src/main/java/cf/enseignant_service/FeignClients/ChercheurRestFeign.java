package cf.enseignant_service.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Chercheur-service", url = "http://localhost:8086")
public interface ChercheurRestFeign {

    @GetMapping("/v1/Chercheurs/statistique/{id}")
    Long nb_chercheur_Enseignant(@PathVariable Long id);

}