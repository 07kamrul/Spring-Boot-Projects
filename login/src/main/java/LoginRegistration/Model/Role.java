package LoginRegistration.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

//@NoArgsConstructor
//@Getter
//@Setter
@Entity
@Table(name="kamrul_role")
public class Role {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private int id;
	
	@Column(name="role")
	private String role;
	
	
}
