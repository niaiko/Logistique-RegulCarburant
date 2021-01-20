package mg.cnaps.services;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import mg.cnaps.models.DemandeVehiculeMod;

public interface DemandeVehiculeService extends CRUDService<DemandeVehiculeMod> {
	 long seqDemande();
	
	 List<DemandeVehiculeMod>getByDateHeureDepart(Date datedepart,Time heuredepart);
	 
	 List<DemandeVehiculeMod> getByDefMouv(String defdmd, String codedr, String validation);
	 
	 public void updatevalidationdmdveh(int iddemande);
	 
	 //List<DemandeVehiculeMod> listauthoriser(Pageable pageable);
	 
	 //List<DemandeVehiculeMod> findByValidation(Pageable pageable);
	 List<DemandeVehiculeMod> getByDefMouv2(String defdmd, String codedr, Date datedepart, String validation);
}
