package mg.cnaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.util.List;
import mg.cnaps.models.PrixCarbuMod;


public interface PrixCarbuRepository extends JpaRepository< PrixCarbuMod,Integer> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();

//	@Query("select u from PrixCarbuMod u where u.carburant = ?1 order by date desc limit 1")
	PrixCarbuMod findFirstByCarburantOrderByDateDesc(String carb);

}
