package mg.cnaps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import java.util.List;
import mg.cnaps.models.OnglobeVehiculeMod;

	public interface OnglobeVehiculeRepository extends JpaRepository< OnglobeVehiculeMod,Integer> {
		@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
		long seqDemande();

		@Query("select COUNT(u) from OnglobeVehiculeMod u where idVehicule = ?1 ")
		int existevehic(int idvehicule);

	}

