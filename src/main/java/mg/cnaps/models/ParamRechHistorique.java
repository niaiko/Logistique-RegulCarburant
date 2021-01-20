package mg.cnaps.models;

import java.sql.Date;
import java.util.List;

public class ParamRechHistorique {

	private int idhistorique;
	private String tache;
	private Date datehisto;
	private String userinsert;
	private String matriculeuser;
	private int page;
	private int nbPage;
	private List<HistoriqueMod> l;

	public int getIdhistorique() {
		return idhistorique;
	}

	public void setIdhistorique(int idhistorique) {
		this.idhistorique = idhistorique;
	}

	public String getTache() {
		return tache;
	}

	public void setTache(String tache) {
		this.tache = tache;
	}

	public Date getDatehisto() {
		return datehisto;
	}

	public void setDatehisto(Date datehisto) {
		this.datehisto = datehisto;
	}

	public String getUserinsert() {
		return userinsert;
	}

	public void setUserinsert(String userinsert) {
		this.userinsert = userinsert;
	}

	public String getMatriculeuser() {
		return matriculeuser;
	}

	public void setMatriculeuser(String matriculeuser) {
		this.matriculeuser = matriculeuser;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public List<HistoriqueMod> getL() {
		return l;
	}

	public void setL(List<HistoriqueMod> l) {
		this.l = l;
	}

}
