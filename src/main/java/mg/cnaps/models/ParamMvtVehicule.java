package mg.cnaps.models;

import java.util.List;

public class ParamMvtVehicule {

	private String refDem;
	private String service;
	private int page;
	private int size = 10;
	private int nbPage;
	private List<MouvementVehiculeMod> mouvementVehiculeMod;

	public ParamMvtVehicule() {
		super();
	}

	public String getRefDem() {
		return refDem;
	}

	public void setRefDem(String refDem) {
		this.refDem = refDem;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
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

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public List<MouvementVehiculeMod> getMouvementVehiculeMod() {
		return mouvementVehiculeMod;
	}

	public void setMouvementVehiculeMod(List<MouvementVehiculeMod> mouvementVehiculeMod) {
		this.mouvementVehiculeMod = mouvementVehiculeMod;
	}

}
