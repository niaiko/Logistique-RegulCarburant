package mg.cnaps.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.cnaps.models.MouvCarteMensuelMod;
//import java.util.List;
import mg.cnaps.models.MouvCarteMod;


public interface MouvCarteRepository extends JpaRepository<MouvCarteMod,Integer> {
	@Query(value = "select nextval('\"RFM\".mouvement_carte_id_mouv_carte_seq')", nativeQuery=true)
	long seqDemande();
	
	@Query("SELECT u.datemouv, u.numcarte, SUM(u.entree) as entree, SUM(u.sortant) as sortant, (SUM(u.entree)-sum(u.sortant)) as minus  FROM MouvCarteMod u WHERE to_char(u.datemouv, 'MM') = '01' group by u.numcarte, u.datemouv")
	List<MouvCarteMensuelMod> Mouvcartemensuel(String mois,Pageable pageable);
	
	@Query("SELECT u FROM MouvCarteMod u WHERE refdmd = '' and entree = 0 ")
	List<MouvCarteMod> listeCarteRetourner();
	
	@Query("SELECT u FROM MouvCarteMod u WHERE refdmd = '' and entree = 0 and (datemouv=?1 or ?1 = '') and (numerocarte like concat(lower(?2), '%') or ?2 = '')")
	List<MouvCarteMensuelMod> rechCarteRetourner(Date retour, String numcarte);
}


