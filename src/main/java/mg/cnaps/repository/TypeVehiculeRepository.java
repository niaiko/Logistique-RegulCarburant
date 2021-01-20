package mg.cnaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.util.List;
import mg.cnaps.models.TypeVehiculeMod;


public interface TypeVehiculeRepository extends JpaRepository<TypeVehiculeMod,Integer> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();


}
