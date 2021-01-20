package mg.cnaps.services;

//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.TypeAssuranceMod;

public interface TypeAssuranceService extends CRUDService<TypeAssuranceMod> {
	long seqDemande();
}
