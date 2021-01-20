package mg.cnaps.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import mg.cnaps.models.CarteCarbMod;

public interface CarteCarbRepository extends JpaRepository<CarteCarbMod, Integer> {
	@Query(value = "select nextval('\"RFM\".carte_carburant_id_carte_carb_seq')", nativeQuery = true)
	long seqDemande();

	@Query("select u from CarteCarbMod u where idtypecarte = ?1 ")
	List<CarteCarbMod> getCarteDispo(int dispo);

	@Query("select u from CarteCarbMod u where u.idcomptrol =?1 ")
	List<CarteCarbMod> getByCompPetrol(Integer comptrol, Pageable pageable);

	@Query("select u from CarteCarbMod u where u.idcomptrol =?1 ")
	Page<CarteCarbMod> getByCompePetrol(Integer comptrol, Pageable pageable);

	@Query("select u from CarteCarbMod u where (iddisponibilite = ?1 or ?1 = 0) and (idtypecarte = ?2 or ?2 =0) and (drpossession like concat(lower(?3), '%') or ?3 ='' ) and (numerocarte like concat(lower(?4), '%') or ?4 ='' )")
	List<CarteCarbMod> getbydispoandtype(int dispo, int type, String dr, String numcarte);

	@Query("select u from CarteCarbMod u where numerocarte like concat(lower(?1), '%') and id_disponibilite = 1")
	List<CarteCarbMod> getautocompliteCarteDispo(String numcarte);

	@Query("select u from CarteCarbMod u ")
	List<CarteCarbMod> fillalllistecarte(Pageable pageable);

	@Query("select u from CarteCarbMod u where iddisponibilite = 1")
	List<CarteCarbMod> listecartecarbdispo();

	@Transactional
	@Modifying
	@Query(value = "update CarteCarbMod u set u.idcomptrol = ?2, u.drpossession = ?3,u.codecarte = ?4 ,u.utilisation = ?5,u.codedr = ?6,u.iddisponibilite = ?7,u.idtypecarte = ?8,u.numerocarte = ?9,u.montant = ?10 where u.idcartecarburant = ?1 ")
	public void updatecartecarbu(int idcarte, int idcompetrol, String drpossession, int codecarte, String utilisation,
			String codedr, int iddispo, int idtypecarte, String numerocarete, Double montant);
}
