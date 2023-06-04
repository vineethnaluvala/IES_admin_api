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
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String userMail;
	private String userPhno;
	private String userGender;
	private String userDob;
	private String userSsn;
	private String activeSwitch = "Y";
	private String accStatus = "LOCKED";
	private String userPwd;
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
	@ManyToOne
	@JoinColumn(name = "roleId")
	private IesUserRoles userRole;
}
