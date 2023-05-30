package in.vini.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ApplicationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer aid;
	private String name;
	private Integer caseNo;
	private String mail;
	private String phno;
	private String gender;
	private String dob;
	private String ssn;

}
