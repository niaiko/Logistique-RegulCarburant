package mg.cnaps.models;


	import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
	
	@Entity
	@Table(name = "solde")
	public class SoldeMod {
		
		
		@Id
		@Column(name = "id_solde", unique = true , nullable = false )
		private int idsolde;
		
		@Column(name = "solde")
		private Double solde;
		
		@Column(name = "date_solde")
		private Date datesolde;
		
		@Column(name = "num_carte")
		private String numcarte;

		public int getIdsolde() {
			return idsolde;
		}

		public void setIdsolde(int idsolde) {
			this.idsolde = idsolde;
		}

		public Double getSolde() {
			return solde;
		}

		public void setSolde(Double solde) {
			this.solde = solde;
		}

		public Date getDatesolde() {
			return datesolde;
		}

		public void setDatesolde(Date datesolde) {
			this.datesolde = datesolde;
		}

		public String getNumcarte() {
			return numcarte;
		}

		public void setNumcarte(String numcarte) {
			this.numcarte = numcarte;
		}
}
