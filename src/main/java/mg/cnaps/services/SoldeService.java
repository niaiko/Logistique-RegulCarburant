package mg.cnaps.services;

//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.SoldeMod;

public interface SoldeService extends CRUDService<SoldeMod> {
	long seqDemande();
	
//	int compairevehicule(String article, String codearticle);
}
