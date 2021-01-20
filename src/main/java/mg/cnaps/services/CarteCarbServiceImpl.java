package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mg.cnaps.models.CarteCarbMod;
import mg.cnaps.models.DemandeVehiculeMod;
import mg.cnaps.repository.CarteCarbRepository;
@Service
public class CarteCarbServiceImpl implements CarteCarbService{
	
	public static int max=50;
	
	@Autowired
	CarteCarbRepository repository;

	@Override
	public CarteCarbMod save(CarteCarbMod carb) {
		return repository.save(carb);
		
	}

	@Override
	public CarteCarbMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarteCarbMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(CarteCarbMod entity) {
		repository.delete(entity);
		
	}
	
	@Override
	public List<CarteCarbMod> getCarteDispo(int dispo) {
		return repository.getCarteDispo(dispo);
	}
	
	@Override
	public List<CarteCarbMod> getbydispoandtype(int dispo, int type,String dr, String numcarte) {
		return repository.getbydispoandtype(dispo,type,dr, numcarte);
	}
	
	@Override
	public List<CarteCarbMod> getautocompliteCarteDispo(String numcarte) {
		return repository.getautocompliteCarteDispo(numcarte);
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
	public List<CarteCarbMod> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DemandeVehiculeMod> getAll(PageRequest gotoPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarteCarbMod> fillalllistecarte(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.fillalllistecarte(pageable);
	}

	@Override
	public void updatecartecarbu(int idcarte, int idcompetrol, String drpossession, int codecarte, String utilisation,
			String codedr, int iddispo, int idtypecarte, String numerocarete, Double montant) {
		repository.updatecartecarbu(idcarte, idcompetrol, drpossession, codecarte, utilisation, codedr,  iddispo, idtypecarte, numerocarete, montant);
	}

	@Override
	public List<CarteCarbMod> listecartecarbdispo() {
		// TODO Auto-generated method stub
		return repository.listecartecarbdispo();
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
