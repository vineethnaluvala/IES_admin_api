package in.vini.request;

import lombok.Data;

@Data
public class PlanRequest {

	private String planeName;
	private String planStartDate;
	private String planEndDate;
	private String planCategory;
}
