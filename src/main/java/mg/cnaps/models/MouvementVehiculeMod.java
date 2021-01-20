package mg.cnaps.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "mouvement_vehicule")
public class MouvementVehiculeMod{
	
	
	@Id
//	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "id_mouv", unique = true , nullable = false )
	private Integer idmouv;

	@Column(name = "id_carte_carb")
	private int idcartecarb ;
	
	@Column(name = "date_depart")
	private Date datedepart;

	@Column(name = "date_arriver")
	private Date datearriver;	
	
	@Column(name = "km_depart")
	private double  kmdepart ;
	
	@Column(name = "km_arriver")
	private double kmarriver ;
	
	@Column(name = "id_chauffeur")
	private String idchauffeur;
	
	@Column(name = "nbr_personne")
	private Integer nbrpersonne ;
	
	@Column(name = "id_mission")
	private String idmission;
	
	@Column(name = "id_demande")
	private int iddemande;
	
	@Column(name = "quantite_carb")
	private double quantitecarb;
	
	@Column(name = "prix_carb")
	private double prixcarb;

	@Column(name = "reference_dmd_vehi")
	private String refdmdvh;
	
	@Column(name = "id_vehicule")
	private int idvehicule;
	
	@Column(name = "consommation_veh")
	private Double consomveh;
	
	@Column(name = "service")
	private String service;
	
	public int getIddemande() {
		return iddemande;
	}

	public void setIddemande(int iddemande) {
		this.iddemande = iddemande;
	}

	public String getRefdmdvh() {
		return refdmdvh;
	}

	public void setRefdmdvh(String refdmdvh) {
		this.refdmdvh = refdmdvh;
	}

	public Integer getIdmouv() {
		return idmouv;
	}

	public void setIdmouv(Integer idmouv) {
		this.idmouv = idmouv;
	}

	public int getIdcartecarb() {
		return idcartecarb;
	}

	public void setIdcartecarb(int idcartecarb) {
		this.idcartecarb = idcartecarb;
	}

	public Date getDatedepart() {
		return datedepart;
	}

	public void setDatedepart(Date datedepart) {
		this.datedepart = datedepart;
	}

	public Date getDatearriver() {
		return datearriver;
	}

	public void setDatearriver(Date datearriver) {
		this.datearriver = datearriver;
	}

	public double getKmdepart() {
		return kmdepart;
	}

	public void setKmdepart(double kmdepart) {
		this.kmdepart = kmdepart;
	}

	public double getKmarriver() {
		return kmarriver;
	}

	public void setKmarriver(double kmarriver) {
		this.kmarriver = kmarriver;
	}

	public String getIdchauffeur() {
		return idchauffeur;
	}

	public void setIdchauffeur(String idchauffeur) {
		this.idchauffeur = idchauffeur;
	}

	public Integer getNbrpersonne() {
		return nbrpersonne;
	}

	public void setNbrpersonne(Integer nbrpersonne) {
		this.nbrpersonne = nbrpersonne;
	}

	public String getIdmission() {
		return idmission;
	}

	public void setIdmission(String idmission) {
		this.idmission = idmission;
	}

	public double getQuantitecarb() {
		return quantitecarb;
	}

	public void setQuantitecarb(double quantitecarb) {
		this.quantitecarb = quantitecarb;
	}

	public double getPrixcarb() {
		return prixcarb;
	}

	public void setPrixcarb(double prixcarb) {
		this.prixcarb = prixcarb;
	}

	public Double getConsomveh() {
		return consomveh;
	}

	public void setConsomveh(Double consomveh) {
		this.consomveh = consomveh;
	}

	public int getIdvehicule() {
		return idvehicule;
	}

	public void setIdvehicule(int idvehicule) {
		this.idvehicule = idvehicule;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
}
