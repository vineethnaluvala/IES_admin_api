package in.vini.service;

import java.util.List;

import in.vini.entity.ApplicationEntity;
import in.vini.entity.CaseWorkerEntity;
import in.vini.entity.DashBoardEntity;
import in.vini.entity.PlanEntity;
import in.vini.request.AdminCw;
import in.vini.request.ApplicationRequest;
import in.vini.request.PlanRequest;

public interface AdminService {

	public boolean createAccount(AdminCw adminCw);

	public List<CaseWorkerEntity> viewAccounts();

	public boolean editAccount(Integer id, CaseWorkerEntity cid);

	public boolean createPlan(PlanRequest pReq);

	public List<PlanEntity> viewPlans();

	public boolean editPlan(Integer id, PlanEntity pid);

	public List<DashBoardEntity> getDashboardData();

	public String createApplication(ApplicationRequest applicationReq);

	public List<ApplicationEntity> viewApplications();

}
