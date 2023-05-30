package in.vini.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DashBoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer did;
	private String numberOfPlans;
	private String citizensApproved;
	private String citizensDenied;
	private String benefitGiven;
}
