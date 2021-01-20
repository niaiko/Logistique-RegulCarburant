package mg.cnaps.models.article;

import java.sql.Date;

public class CodeArticleMod {
	private String idCodeArt;
    private int refArticle;
    private String etat;
    private double prix;
    private Date datePremierAcqui;
    private int estCondamne;
    private int estMelange;
    private String melangeAvec;
    private String imputation;
    private String marque;
    private String reference;
    private String tef;
    private String fournisseur;
	public String getIdCodeArt() {
		return idCodeArt;
	}
	public void setIdCodeArt(String idCodeArt) {
		this.idCodeArt = idCodeArt;
	}
	public int getRefArticle() {
		return refArticle;
	}
	public void setRefArticle(int refArticle) {
		this.refArticle = refArticle;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Date getDatePremierAcqui() {
		return datePremierAcqui;
	}
	public void setDatePremierAcqui(Date datePremierAcqui) {
		this.datePremierAcqui = datePremierAcqui;
	}
	public int getEstCondamne() {
		return estCondamne;
	}
	public void setEstCondamne(int estCondamne) {
		this.estCondamne = estCondamne;
	}
	public int getEstMelange() {
		return estMelange;
	}
	public void setEstMelange(int estMelange) {
		this.estMelange = estMelange;
	}
	public String getMelangeAvec() {
		return melangeAvec;
	}
	public void setMelangeAvec(String melangeAvec) {
		this.melangeAvec = melangeAvec;
	}
	public String getImputation() {
		return imputation;
	}
	public void setImputation(String imputation) {
		this.imputation = imputation;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTef() {
		return tef;
	}
	public void setTef(String tef) {
		this.tef = tef;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
    
}
