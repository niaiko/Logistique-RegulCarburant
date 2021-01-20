package mg.cnaps.models;

import java.util.List;

public class ParamCompPetrol {

	private int comptrol;
	private int page;
	private int nbPage;
	private List<CarteCarbMod> l;

	public int getComptrol() {
		return comptrol;
	}

	public void setComptrol(int comptrol) {
		this.comptrol = comptrol;
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

	public List<CarteCarbMod> getL() {
		return l;
	}

	public void setL(List<CarteCarbMod> l) {
		this.l = l;
	}

}
