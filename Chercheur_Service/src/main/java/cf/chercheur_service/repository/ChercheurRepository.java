package cf.chercheur_service.repository;

import cf.chercheur_service.entities.Chercheur;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChercheurRepository extends JpaRepository<Chercheur, Long> {
    @Query("SELECT COUNT(c) FROM Chercheur c WHERE c.id_enseignant = :enseignantId")
    long total_chercheur(@Param("enseignantId") Long enseignantId);
}
