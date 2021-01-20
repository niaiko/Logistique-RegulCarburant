package mg.cnaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.util.List;
import mg.cnaps.models.TypeAssuranceMod;


public interface TypeAssuranceRepository extends JpaRepository<TypeAssuranceMod,Integer> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();


}
