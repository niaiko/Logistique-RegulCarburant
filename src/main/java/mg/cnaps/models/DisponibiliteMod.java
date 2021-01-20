package mg.cnaps.models;

	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
	
	@Entity
	@Table(name = "disponibilite")
	public class DisponibiliteMod {
		
		
		@Id
		@Column(name = "id_disponibilite", unique = true , nullable = false )
		private int iddispo;
		
		@Column(name = "libille")
		private String libelle;

		public int getIddispo() {
			return iddispo;
		}

		public void setIddispo(int iddispo) {
			this.iddispo = iddispo;
		}

		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		
}
