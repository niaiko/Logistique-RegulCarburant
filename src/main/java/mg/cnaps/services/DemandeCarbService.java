package mg.cnaps.services;



import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import mg.cnaps.models.DemandeCarbMod;


public interface DemandeCarbService extends CRUDService<DemandeCarbMod> {
	 long seqDemande();
	 List<DemandeCarbMod> getByavecDateDemande(Date datedemande, Boolean validation, String referencecarbu);
	 List<DemandeCarbMod> getBysansDateDemande( Boolean validation, String referencecarbu);
	 public void updatevalidationdmdcarbu(int iddmdcarbu);
	 List<DemandeCarbMod> listevalidationdmdcarbu(Pageable pageable);
	 //List<DemandeCarbMod> listenonvalidationdmdcarbu(Pageable pageable);
}
