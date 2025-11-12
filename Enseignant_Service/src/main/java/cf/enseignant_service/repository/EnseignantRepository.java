package cf.enseignant_service.repository;

import cf.enseignant_service.entites.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {


}
