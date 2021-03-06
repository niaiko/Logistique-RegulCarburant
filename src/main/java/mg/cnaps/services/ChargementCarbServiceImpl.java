package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.ChargementCarbMod;
import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.repository.ChargementcarbRepository;
@Service
public class ChargementCarbServiceImpl implements ChargementCarbService{
	
	public static int max=50;
	
	@Autowired
	ChargementcarbRepository repository;

	@Override
	public ChargementCarbMod save(ChargementCarbMod carb) {
		return repository.save(carb);
		
	}

	@Override
	public ChargementCarbMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChargementCarbMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(ChargementCarbMod entity) {
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
	public List<ChargementCarbMod> getAll() {
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
