package cf.projet_service.FeignClients;

import cf.projet_service.models.Chercheur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Chercheur-service",url = "http://localhost:8086")
public interface ChercheurRestFeign {

    @GetMapping("/v1/Chercheurs/{id}")
    Chercheur getChercheurByID(@PathVariable Long id);

}