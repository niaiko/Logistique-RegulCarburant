package mg.cnaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.util.List;
import mg.cnaps.models.SoldeMod;


public interface SoldeRepository extends JpaRepository<SoldeMod,Integer> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();

//	@Query("select COUNT(u) from VehiculeMod u where matricule = ?1 and refvehicule = ?2 ")
//	int compairevehicule(String article, String codearticle);
}


