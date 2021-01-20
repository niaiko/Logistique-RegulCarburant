package mg.cnaps.models;

import java.util.List;

public class ParamDmdVehicule {

	private String matricule;
	private String codeDr;
	private String refDem;
	private int page;
	private int size = 10;
	private int nbPage;
	private List<DemandeVehiculeMod> vehiculeMod;

	public ParamDmdVehicule() {
		super();
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getCodeDr() {
		return codeDr;
	}

	public void setCodeDr(String codeDr) {
		this.codeDr = codeDr;
	}

	public String getRefDem() {
		return refDem;
	}

	public void setRefDem(String refDem) {
		this.refDem = refDem;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<DemandeVehiculeMod> getVehiculeMod() {
		return vehiculeMod;
	}

	public void setVehiculeMod(List<DemandeVehiculeMod> vehiculeMod) {
		this.vehiculeMod = vehiculeMod;
	}

}
