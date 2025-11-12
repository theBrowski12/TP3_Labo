package cf.enseignant_service.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Projet-service", url = "http://localhost:8087")
public interface ProjetRestFeign {

    @GetMapping("/v1/Projets/statistique/{id}")
    //@CircuitBreaker(name = "projet-count",fallbackMethod = "Projet_fallbackMethod")
    //@Retry(name = "projet-count",fallbackMethod = "Chercheur_fallbackMethod")
    Long nb_Projet_Enseignant(@PathVariable Long id);

    default Long Projet_fallbackMethod(Long id,Exception e){
        return null;
    }

}