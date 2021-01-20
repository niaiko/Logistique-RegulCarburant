package mg.cnaps.models;

import java.util.Date;
import java.util.List;

public class ParamCarbMod {

	private String refCarbu;
	private String matrDmd;
	private Date dateDmd;
	private int page;
	private int nbPages;
	private List<DemandeCarbMod> demande;

	public ParamCarbMod() {
		super();
	}

	public String getRefCarbu() {
		return refCarbu;
	}

	public void setRefCarbu(String refCarbu) {
		this.refCarbu = refCarbu;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public List<DemandeCarbMod> getDemande() {
		return demande;
	}

	public void setDemande(List<DemandeCarbMod> demande) {
		this.demande = demande;
	}

	public String getMatrDmd() {
		return matrDmd;
	}

	public void setMatrDmd(String matrDmd) {
		this.matrDmd = matrDmd;
	}

	public Date getDateDmd() {
		return dateDmd;
	}

	public void setDateDmd(Date dateDmd) {
		this.dateDmd = dateDmd;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
