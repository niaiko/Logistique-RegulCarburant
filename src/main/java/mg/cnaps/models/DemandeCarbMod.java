package mg.cnaps.models;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "demande_carbu")
public class DemandeCarbMod{
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_carbu", unique = true , nullable = false )
	private int idcarbu;

	@Column(name = "prix")
	private Double idprix ;
	
	@Column(name = "distance")
	private Double distance ;
	
	@Column(name = "date_demande")
	private Date datedemande ;
	
	@Column(name = "validation")
	private Boolean validation;
	
	@Column(name = "durer_mission")
	private int durermission;
	
	@Column(name="reference_carbu")
	private String referencecarbu;
	
	@Column(name="motif")
	private String motif;
	
	@Column(name="matricule_demandeur")
	private String matrdmd;
	
	@Column(name="date_debut")
	private Date datedebut;
	
	@Column(name="date_fin")
	private Date datefin;
	
	@Column(name="lieu_debut")
	private String lieudepart;
	
	@Column(name="lieu_arrive")
	private String lieuarrive;
	
	
	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public String getLieudepart() {
		return lieudepart;
	}

	public void setLieudepart(String lieudepart) {
		this.lieudepart = lieudepart;
	}

	public String getLieuarrive() {
		return lieuarrive;
	}

	public void setLieuarrive(String lieuarrive) {
		this.lieuarrive = lieuarrive;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getMatrdmd() {
		return matrdmd;
	}

	public void setMatrdmd(String matrdmd) {
		this.matrdmd = matrdmd;
	}

	public int getIdcarbu() {
		return idcarbu;
	}

	public String getReferencecarbu() {
		return referencecarbu;
	}

	public void setReferencecarbu(String referencecarbu) {
		this.referencecarbu = referencecarbu;
	}

	public void setIdcarbu(int idcarbu) {
		this.idcarbu = idcarbu;
	}

	public Double getIdprix() {
		return idprix;
	}

	public void setIdprix(Double idprix) {
		this.idprix = idprix;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Date getDatedemande() {
		return datedemande;
	}

	public void setDatedemande(Date datedemande) {
		this.datedemande = datedemande;
	}

	public Boolean getValidation() {
		return validation;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}

	public int getDurermission() {
		return durermission;
	}

	public void setDurermission(int durermission) {
		this.durermission = durermission;
	}

	
}
