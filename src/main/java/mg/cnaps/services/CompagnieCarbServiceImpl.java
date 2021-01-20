package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.CompagnieCarbMod;
import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.repository.CompagniecarbRepository;
@Service
public class CompagnieCarbServiceImpl implements CompagnieCarbService{
	
	public static int max=50;
	
	@Autowired
	CompagniecarbRepository repository;

	@Override
	public CompagnieCarbMod save(CompagnieCarbMod carb) {
		return repository.save(carb);
		
	}

	@Override
	public CompagnieCarbMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompagnieCarbMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(CompagnieCarbMod entity) {
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
	public List<CompagnieCarbMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
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
