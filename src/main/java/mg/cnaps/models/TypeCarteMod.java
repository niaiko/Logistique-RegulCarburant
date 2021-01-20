package mg.cnaps.models;


	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
	
	@Entity
	@Table(name = "type_carte")
	public class TypeCarteMod {
		
		
		@Id
		@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
		@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
		@Column(name = "id_type", unique = true , nullable = false )
		private int idtype;
		
		@Column(name = "libelle")
		private String libelle;

		public int getIdtype() {
			return idtype;
		}

		public void setIdtype(int idtype) {
			this.idtype = idtype;
		}

		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		
}
