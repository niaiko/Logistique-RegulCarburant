package mg.cnaps.services;

//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.TypeCarteMod;

public interface TypeCarteService extends CRUDService<TypeCarteMod> {
	long seqDemande();
	
}
