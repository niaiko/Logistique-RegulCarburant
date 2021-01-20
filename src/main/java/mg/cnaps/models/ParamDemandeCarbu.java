package mg.cnaps.models;



import java.sql.Date;

public class ParamDemandeCarbu{
	
	private String userinsert;
	private String matriculeuser;
	private int idcarbu;
	private int idhistorique;
	private Double idprix ;
	private Double distance ;
	private Date datedemande ;
	private Boolean validation;
	private int durermission;
	private String referencecarbu;
	private String motif;
	private String matrdmd;
	private Date datedebut;
	private Date datefin;
	private String lieudepart;
	private String lieuarrive;
	
	public int getIdhistorique() {
		return idhistorique;
	}
	public void setIdhistorique(int idhistorique) {
		this.idhistorique = idhistorique;
	}
	public String getMatriculeuser() {
		return matriculeuser;
	}
	public void setMatriculeuser(String matriculeuser) {
		this.matriculeuser = matriculeuser;
	}
	public String getUserinsert() {
		return userinsert;
	}
	public void setUserinsert(String userinsert) {
		this.userinsert = userinsert;
	}
	public int getIdcarbu() {
		return idcarbu;
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
	public String getReferencecarbu() {
		return referencecarbu;
	}
	public void setReferencecarbu(String referencecarbu) {
		this.referencecarbu = referencecarbu;
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
	
}
