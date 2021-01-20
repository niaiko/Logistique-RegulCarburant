package mg.cnaps.services;

//import java.sql.Date;
	//import java.util.List;
	import mg.cnaps.models.OnglobeVehiculeMod;
	
	public interface OnglobeVehiculeService extends CRUDService<OnglobeVehiculeMod> {
		long seqDemande();
		int existevehic(int idvehicule);
	}


