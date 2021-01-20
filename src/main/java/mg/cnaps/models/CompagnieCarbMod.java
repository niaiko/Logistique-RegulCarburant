package mg.cnaps.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "compagnie_petroliere")
public class CompagnieCarbMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_comp_petrol", unique = true , nullable = false )
	private int idcomppetrol;

	@Column(name = "libelle",length=100)
	private String idcartecarb ;

	public Integer getIdcomppetrol() {
		return idcomppetrol;
	}

	public String getIdcartecarb() {
		return idcartecarb;
	}

	public void setIdcartecarb(String idcartecarb) {
		this.idcartecarb = idcartecarb;
	}

	public void setIdcomppetrol(int idcomppetrol) {
		this.idcomppetrol = idcomppetrol;
	}

	
	
}
