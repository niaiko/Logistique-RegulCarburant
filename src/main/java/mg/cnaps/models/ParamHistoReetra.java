package mg.cnaps.models;

import java.util.List;

public class ParamHistoReetra {

	private int page;
	private int nbPage;
	private List<HistoriqueMod> l;

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
