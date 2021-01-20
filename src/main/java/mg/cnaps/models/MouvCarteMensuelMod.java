package mg.cnaps.models;

import java.util.Date;

public class MouvCarteMensuelMod {
		
		
		private String mois;
		private String numcarte;
		private Date datemouv;
		private Double entree;
		private Double sortant;
		private Double minus;
		
		public String getMois() {
			return mois;
		}
		public void setMois(String mois) {
			this.mois = mois;
		}
		public String getNumcarte() {
			return numcarte;
		}
		public void setNumcarte(String numcarte) {
			this.numcarte = numcarte;
		}
		public Date getDatemouv() {
			return datemouv;
		}
		public void setDatemouv(Date datemouv) {
			this.datemouv = datemouv;
		}
		public Double getEntree() {
			return entree;
		}
		public void setEntree(Double entree) {
			this.entree = entree;
		}
		public Double getSortant() {
			return sortant;
		}
		public void setSortant(Double sortant) {
			this.sortant = sortant;
		}
		public Double getMinus() {
			return minus;
		}
		public void setMinus(Double minus) {
			this.minus = minus;
		}
		
}
