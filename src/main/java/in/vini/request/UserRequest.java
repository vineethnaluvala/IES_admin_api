package in.vini.request;

import lombok.Data;

@Data
public class UserRequest {

	private String userName;
	private String userMail;
	private String userPhno;
	private String userGender;
	private String userDob;
	private String userSsn;
	private Integer roleId;
}
