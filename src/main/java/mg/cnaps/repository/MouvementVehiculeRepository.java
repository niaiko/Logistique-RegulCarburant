package mg.cnaps.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mg.cnaps.models.MouvementVehiculeMod;
//import java.util.List;

public interface MouvementVehiculeRepository extends JpaRepository<MouvementVehiculeMod, Integer> {
	@Query(value = "select nextval('\"RFM\".mouvement_vehicule_id_mouv_seq')", nativeQuery = true)
	long seqDemande();

	@Query("SELECT u FROM MouvementVehiculeMod u WHERE datedepart=?1")
	List<MouvementVehiculeMod> getByDatedepart(Date datedepart);

	@Query("SELECT u FROM MouvementVehiculeMod u WHERE idchauffeur=?1")
	List<MouvementVehiculeMod> getByIdchauffeur(String idchauffeur);

	List<MouvementVehiculeMod> findByRefdmdvh(String refdmdvh);

	@Query("SELECT u FROM MouvementVehiculeMod u")
	public List<MouvementVehiculeMod> findallmouv(Pageable pageable);

	@Query("select u from MouvementVehiculeMod u where (u.refdmdvh =:#{#x.refdmdvh} or coalesce(:#{#x.refdmdvh},null) is null) and"
			+ " (u.service =:#{#x.service} or coalesce(:#{#x.service},null) is null)  ")
	public List<MouvementVehiculeMod> findallmouv2(@Param("x") MouvementVehiculeMod m, Pageable pageable);

	@Query("select u from MouvementVehiculeMod u where ( lower(u.refdmdvh) like '%' || lower(cast(?1 as string)) || '%' or ?1 is null ) and (u.service = ?2 or ?2 is null)")
	Page<MouvementVehiculeMod> rechercheParServiceAndRefDem(String refDem, String service, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "update MouvementVehiculeMod u set u.prixcarb = ?1,u.quantitecarb = ?2 where u.idmouv = ?3 ")
	public void updatemouvveh(Double prix, Double quantite, int idmouv);

	@Transactional
	@Modifying
	@Query(value = "update MouvementVehiculeMod u set u.datearriver = ?1 where u.idmouv = ?2 ")
	public void updatearrivermouv(Date datearriver, int idmouv);

	@Query("SELECT u FROM MouvementVehiculeMod u WHERE (datedepart=?1 or cast(?1 as date) is null) and (datearriver=?2 or cast(?2 as date) is null) and (idvehicule = ?3 or ?3  is null) and (idchauffeur = ?4 or ?4 is null)")
	List<MouvementVehiculeMod> rechmultipleMvt(Date datedepart, Date arriver, int idvehicule, String idchauffuer);

	@Query("SELECT COUNT(u) FROM MouvementVehiculeMod u WHERE u.iddemande = ?1 ")
	int countMouv(int iddemande);
}
