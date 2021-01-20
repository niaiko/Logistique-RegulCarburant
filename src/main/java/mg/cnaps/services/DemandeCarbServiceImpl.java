package mg.cnaps.services;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DemandeCarbMod;
import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.repository.DemandeCarbRepository;
@Service
public class DemandeCarbServiceImpl implements DemandeCarbService{
	
	public static int max=50;
	
	@Autowired
	DemandeCarbRepository repository;

	@Override
	public DemandeCarbMod save(DemandeCarbMod carb) {
		return repository.save(carb);
		
	}

	@Override
	public DemandeCarbMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DemandeCarbMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(DemandeCarbMod entity) {
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
	public List<DemandeCarbMod> getByavecDateDemande(Date datedemande, Boolean validation, String referencecarbu){
		return repository.getByavecDateDemande(datedemande, validation, referencecarbu);
		
	}
	
	@Override
	public List<DemandeCarbMod> getBysansDateDemande(Boolean validation, String referencecarbu){
		return repository.getBysansDateDemande(validation, referencecarbu);
		
	}
	
	@Override
	public List<DemandeCarbMod> listevalidationdmdcarbu(Pageable pageable){
		return repository.listevalidationdmdcarbu(pageable);
		
	}
	
//	@Override
//	public List<DemandeCarbMod> listenonvalidationdmdcarbu(Pageable pageable){
//		return repository.listenonvalidationdmdcarbu(pageable) ;	
//	}

	@Override
	public List<DemandeCarbMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatevalidationdmdcarbu(int iddmdcarbu){
		repository.updatevalidationdmdcarbu(iddmdcarbu);
		
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
