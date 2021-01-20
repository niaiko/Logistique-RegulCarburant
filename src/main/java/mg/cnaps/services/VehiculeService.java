package mg.cnaps.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.VehiculeMod;

public interface VehiculeService extends CRUDService<VehiculeMod> {
	long seqDemande();
	
	int compairevehicule(String codearticle, String libelle);
	
	List<VehiculeMod> getvehiculenondetail(Pageable page);
	public void updatedonneevehic(int idvehicule,Double Consommation,String coder,String matrvehic);
	List<VehiculeMod> getlistevehiculedispo(Pageable pageable);
	List<VehiculeMod> findioAll();
//	List<VehiculeMod> getvehiculenondetail(Pageable pageable);
}