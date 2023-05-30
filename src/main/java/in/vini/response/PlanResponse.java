package in.vini.response;

import lombok.Data;

@Data
public class PlanResponse {

	private Integer pid;
	private String name;
	private String startDate;
	private String endDate;
	private String accStatus;
}
