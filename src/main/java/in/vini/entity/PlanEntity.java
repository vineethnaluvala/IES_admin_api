package in.vini.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class PlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	private String planeName;
	private String planStartDate;
	private String planEndDate;
	private String planCategory;
	private String activeSwitch = "Y";
	@CreationTimestamp
	private LocalDate createDate;
	@UpdateTimestamp
	private LocalDate updateDate;

	@ManyToOne
	@JoinColumn(name = "createdBy")
	private UserEntity createdBy;
	@ManyToOne
	@JoinColumn(name = "updatedBy")
	private UserEntity updatedBy;
}
