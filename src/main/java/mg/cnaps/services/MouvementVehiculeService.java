package mg.cnaps.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import mg.cnaps.models.MouvementVehiculeMod;
//import java.sql.Date;
	//import java.util.List;
	public interface MouvementVehiculeService extends CRUDService<MouvementVehiculeMod> {
		long seqDemande();
		List<MouvementVehiculeMod>getByDatedepart(Date datedepart);
		List<MouvementVehiculeMod> getByIdchauffeur(String idchauffeur);
		List<MouvementVehiculeMod>findByRefdmdvh(String refdmdvh);
		public void updatemouvveh(Double prix , Double quantite, int idmouv);
		public void updatearrivermouv( Date datearriver, int idmouv);
		List<MouvementVehiculeMod> rechmultipleMvt(Date datedepart,Date arriver,int idvehicule, String idchauffuer);
		int countMouv(int iddemande);
		List<MouvementVehiculeMod> findallmouv(Pageable pageable);
	}
	


