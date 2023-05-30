package in.vini.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class AdminEntity {
	@Id
	private Integer id;
	private String email;
	private String pzwd;
	
	
}
