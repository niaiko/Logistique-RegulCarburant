package mg.cnaps.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import mg.cnaps.models.HistoriqueMod;



public interface HistoriqueService extends CRUDService<HistoriqueMod> {
	 long seqDemande();
	 List<HistoriqueMod> findallhistorique(Pageable pageable);
	 
	 List<HistoriqueMod> findbydatehistoandmatriculeuser(Date date, String matr);
	 List<HistoriqueMod> findbydatehistoandmatriculeuser2( String matr);
	 Object[] findallty();
}
