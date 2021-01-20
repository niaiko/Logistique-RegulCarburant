package mg.cnaps.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import mg.cnaps.models.CarteCarbMod;


public interface CarteCarbService extends CRUDService<CarteCarbMod> {
	 long seqDemande();
	 List<CarteCarbMod>getCarteDispo(int dispo);
	 List<CarteCarbMod> getbydispoandtype(int dispo, int type, String dr, String numcarte);
	 List<CarteCarbMod> getautocompliteCarteDispo(String numcarte);
	 List<CarteCarbMod> fillalllistecarte(Pageable pageable);
	 List<CarteCarbMod> listecartecarbdispo();
	 public void updatecartecarbu(int idcarte, int idcompetrol, String drpossession, int codecarte, String utilisation, String codedr, int iddispo, int idtypecarte, String numerocarete, Double montant);
}
