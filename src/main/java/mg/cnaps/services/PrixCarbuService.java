package mg.cnaps.services;


//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.PrixCarbuMod;

public interface PrixCarbuService extends CRUDService<PrixCarbuMod> {
	long seqDemande();
	PrixCarbuMod findprixcarburant(String carb);
}


