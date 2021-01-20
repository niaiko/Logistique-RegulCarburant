package mg.cnaps.services;

//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.DisponibiliteMod;

public interface DisponibiliteService extends CRUDService<DisponibiliteMod> {
	long seqDemande();
	
//	int compairevehicule(String article, String codearticle);
}
