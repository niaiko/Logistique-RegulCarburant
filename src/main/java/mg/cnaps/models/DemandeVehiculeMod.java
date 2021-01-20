package mg.cnaps.models;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import mg.cnaps.utils.SqlTimeDeserializer;
@Entity
@Table(name = "demande_vehicule")
public class DemandeVehiculeMod{
	
	
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_demande",nullable = false )
	private int iddemande;
	
	@Column(name="code_service")
	private String codeservice;
	
	@Column(name="nbrpersonne")
	private int nbrpersonne;
	
	@Column(name="codedr")
	private String codedr;
	
	@Column(name="motif_deplacement", length=100)
	private String motifdepl;
	
	@Column(name="definition_dmd", length=100)
	private String defdmd;
	
	@Column(name="trajet", length=100)
	private String trajet;
	
	@Column(name="personne_trasporter", length=250)
	private String perstrans;
	
	@Column(name="date_depart")
	private Date datedepart;
	
	@Column(name="matricule_demandeur")
	private String matrdmd;
	
	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	@Column(name="heure_depart")
	private Time heure;
	
	@Column(name="reference_vehi")
	private String refdmdvh;

	@Column(name="validation")
	private Boolean validation;
	
	public Boolean getValidation() {
		return validation;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}

	public String getRefdmdvh() {
		return refdmdvh;
	}

	public void setRefdmdvh(String refdmdvh) {
		this.refdmdvh = refdmdvh;
	}

	public int getIddemande() {
		return iddemande;
	}

	public void setIddemande(int iddemande) {
		this.iddemande = iddemande;
	}

	public String getCodeservice() {
		return codeservice;
	}

	public void setCodeservice(String codeservice) {
		this.codeservice = codeservice;
	}

	public String getMotifdepl() {
		return motifdepl;
	}

	public void setMotifdepl(String motifdepl) {
		this.motifdepl = motifdepl;
	}

	public String getTrajet() {
		return trajet;
	}

	public void setTrajet(String trajet) {
		this.trajet = trajet;
	}

	public String getPerstrans() {
		return perstrans;
	}

	public void setPerstrans(String perstrans) {
		this.perstrans = perstrans;
	}

	public Date getDatedepart() {
		return datedepart;
	}

	public void setDatedepart(Date datedepart) {
		this.datedepart = datedepart;
	}

	public Time getHeure() {
		return heure;
	}

	public void setHeure(Time heure) {
		this.heure = heure;
	}

	public String getDefdmd() {
		return defdmd;
	}

	public void setDefdmd(String defdmd) {
		this.defdmd = defdmd;
	}

	public String getCodedr() {
		return codedr;
	}

	public void setCodedr(String codedr) {
		this.codedr = codedr;
	}

	public int getNbrpersonne() {
		return nbrpersonne;
	}

	public void setNbrpersonne(int nbrpersonne) {
		this.nbrpersonne = nbrpersonne;
	}

	public String getMatrdmd() {
		return matrdmd;
	}

	public void setMatrdmd(String matrdmd) {
		this.matrdmd = matrdmd;
	}
	
}
