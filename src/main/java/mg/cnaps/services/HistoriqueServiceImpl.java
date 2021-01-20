package mg.cnaps.services;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.models.HistoriqueMod;
import mg.cnaps.repository.HistoriqueRepository;

@Service
public class HistoriqueServiceImpl implements HistoriqueService{
	
	public static int max=50;
	
	@Autowired
	HistoriqueRepository repository;

	@Override
	public HistoriqueMod save(HistoriqueMod carb) {
		return repository.save(carb);
		
	}

	@Override
	public HistoriqueMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoriqueMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(HistoriqueMod entity) {
		repository.delete(entity);
		
	}

	@Override
	public int nombrepage() {
		return (int)(repository.count()/max)+1;
	}

	@Override
	public long seqDemande() {
		return repository.seqDemande();
	}

	@Override
	public List<HistoriqueMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoriqueMod> findallhistorique(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findallhistorique(pageable);
	}

	@Override
	public List<HistoriqueMod> findbydatehistoandmatriculeuser(Date date, String matr) {
		// TODO Auto-generated method stub
		return repository.findbydatehistoandmatriculeuser(date, matr);
	}
	
	@Override
	public List<HistoriqueMod> findbydatehistoandmatriculeuser2(String matr) {
		// TODO Auto-generated method stub
		return repository.findbydatehistoandmatriculeuser2( matr);
	}

	@Override
	public Object[] findallty() {
		// TODO Auto-generated method stub
		return repository.findallty();
	}


	//@Override
	//public List<RegulcarbMod> getByDestinataireDateCou(String destinataire, Date dateInsert) {
	//	return repository.getByDestinataireDateCou(destinataire, dateInsert);
	//}

	//@Override
	//public List<RegulcarbMod> getByDestinataireDateCouIdserviceAdresse(String destinataire,Date dateCourrier, int idservice, String adresse) {
	//	return repository.getByDestinataireDateCouIdserviceAdresse(destinataire,dateCourrier,idservice,adresse);
	//}
}
