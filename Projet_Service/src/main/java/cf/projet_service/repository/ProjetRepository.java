package cf.projet_service.repository;

import cf.projet_service.entites.Projet;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjetRepository extends JpaRepository<Projet,Long> {
    @Query("SELECT COUNT(p) FROM Projet p WHERE p.id_enseignant = :enseignantId")
    long total_Projet_Enseignant(@Param("enseignantId") Long enseignantId);
}
