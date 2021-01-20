package mg.cnaps.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import mg.cnaps.models.MouvCarteMensuelMod;
//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.MouvCarteMod;

public interface MouvCarteService extends CRUDService<MouvCarteMod> {
	long seqDemande();
	
	List<MouvCarteMensuelMod> Mouvcartemensuel(String mois,Pageable pageable);
	
	List<MouvCarteMod> listeCarteRetourner();
	
	List<MouvCarteMensuelMod> rechCarteRetourner(Date retour, String numcarte);
}
