package in.vini.service;

import java.util.List;

import in.vini.entity.PlanEntity;
import in.vini.entity.UserEntity;
import in.vini.request.PlanRequest;
import in.vini.request.UserRequest;

public interface AdminService {

	public String createAccount(UserRequest user);

	public List<UserEntity> viewAccounts();

	public boolean editAccount(Integer id, UserRequest userReq);

	public boolean createPlan(PlanRequest planReq);

	public List<PlanEntity> viewPlans();

	public boolean editPlan(Integer id, PlanRequest planReq);

}
