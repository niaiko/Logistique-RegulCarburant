package mg.cnaps.models;


import java.util.Date;

import javax.persistence.Id;

public class ParamMouvvehic{
	
	
	@Id
	private Integer idmouv;
	private int idcartecarb ;
	private Date datedepart;
	private Date datearriver;	
	private double  kmdepart ;
	private double kmarriver ;
	private String idchauffeur;
	private Integer nbrpersonne ;
	private String idmission;
	private int iddemande;
	private int idvehicule;
	private double quantitecarb;
	private double prixcarb;
	private String refdmdvh;
	private Double consomveh;
	private String codeservice;
	private String defdmd;
	private String codedr;
	private Double distance;
	private int sejour;
	private String matrdmd;
	private String typecarbu;
	private Boolean validation;
	
	public Boolean getValidation() {
		return validation;
	}
	public void setValidation(Boolean validation) {
		this.validation = validation;
	}
	public String getMatrdmd() {
		return matrdmd;
	}
	public void setMatrdmd(String matrdmd) {
		this.matrdmd = matrdmd;
	}
	public int getIdvehicule() {
		return idvehicule;
	}
	public void setIdvehicule(int idvehicule) {
		this.idvehicule = idvehicule;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public int getSejour() {
		return sejour;
	}
	public void setSejour(int sejour) {
		this.sejour = sejour;
	}
	public String getCodedr() {
		return codedr;
	}
	public void setCodedr(String codedr) {
		this.codedr = codedr;
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
	public int getIddemande() {
		return iddemande;
	}
	public void setIddemande(int iddemande) {
		this.iddemande = iddemande;
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
	public String getRefdmdvh() {
		return refdmdvh;
	}
	public void setRefdmdvh(String refdmdvh) {
		this.refdmdvh = refdmdvh;
	}
	public Double getConsomveh() {
		return consomveh;
	}
	public void setConsomveh(Double consomveh) {
		this.consomveh = consomveh;
	}
	public String getCodeservice() {
		return codeservice;
	}
	public void setCodeservice(String codeservice) {
		this.codeservice = codeservice;
	}
	public String getDefdmd() {
		return defdmd;
	}
	public void setDefdmd(String defdmd) {
		this.defdmd = defdmd;
	}
	public String getTypecarbu() {
		return typecarbu;
	}
	public void setTypecarbu(String typecarbu) {
		this.typecarbu = typecarbu;
	}
	
}
