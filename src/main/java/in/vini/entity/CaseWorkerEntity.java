package in.vini.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CaseWorkerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;
	private String name;
	private String mail;
	private String phno;
	private String gender;
	private String dob;
	private String ssn;
	private String accStatus;
	private String role;
	private String pwd;
}
