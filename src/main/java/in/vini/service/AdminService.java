package in.vini.service;

import java.util.List;

import in.vini.entity.CaseWorkerEntity;
import in.vini.entity.PlanEntity;

public interface AdminService {

	public boolean createAccount(CaseWorkerEntity cwEntity);
	
	public List<CaseWorkerEntity> viewAccounts();
	
	public boolean editAccount(CaseWorkerEntity cwEntity);

	public boolean createPlan(PlanEntity pEntity);
	
	public List<PlanEntity> viewPlans();
	
	public boolean editPlan(PlanEntity pEntity);

}
