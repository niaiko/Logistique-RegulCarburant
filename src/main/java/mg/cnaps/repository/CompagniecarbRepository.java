package mg.cnaps.repository;

//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.cnaps.models.CompagnieCarbMod;

public interface CompagniecarbRepository extends JpaRepository<CompagnieCarbMod,Integer> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();
}
