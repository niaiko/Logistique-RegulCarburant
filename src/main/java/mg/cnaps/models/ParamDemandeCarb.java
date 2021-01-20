package mg.cnaps.models;


import java.util.Date;

import javax.persistence.Id;

public class ParamDemandeCarb{
	
	
	@Id
	private Integer idcarbu;
	private int idprix;
	private Date datedemande;
	private int durermission;	
	private String referencecarbu ;
	private Double distance;
	private Boolean validation;
	
	public Integer getIdcarbu() {
		return idcarbu;
	}
	public void setIdcarbu(Integer idcarbu) {
		this.idcarbu = idcarbu;
	}
	public int getIdprix() {
		return idprix;
	}
	public void setIdprix(int idprix) {
		this.idprix = idprix;
	}
	public Date getDatedemande() {
		return datedemande;
	}
	public void setDatedemande(Date datedemande) {
		this.datedemande = datedemande;
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
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Boolean getValidation() {
		return validation;
	}
	public void setValidation(Boolean validation) {
		this.validation = validation;
	}
}
