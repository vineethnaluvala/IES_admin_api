package in.vini.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vini.entity.AdminEntity;
import in.vini.entity.ApplicationEntity;
import in.vini.entity.CaseWorkerEntity;
import in.vini.entity.DashBoardEntity;
import in.vini.entity.PlanEntity;
import in.vini.repo.AdminRepository;
import in.vini.repo.ApplicationRepository;
import in.vini.repo.CaseWorkerRepository;
import in.vini.repo.DashboardRepository;
import in.vini.repo.PlanEntityRepository;
import in.vini.request.AdminCw;
import in.vini.request.ApplicationRequest;
import in.vini.request.PlanRequest;
import in.vini.utils.EmailUtils;
import in.vini.utils.PwdUtils;

@Service
public class AdminServiceImpl implements AdminService {

	private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private CaseWorkerRepository cwRepo;
	@Autowired
	private PlanEntityRepository planRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private DashboardRepository dashBoardRepo;
	@Autowired
	private ApplicationRepository appRepo;
	@Autowired
	private EmailUtils utils;

	public boolean createAccount(AdminCw adminCw) {

		try {
			AdminEntity findByEmailAndPzwd = adminRepo.findByEmailAndPzwd(adminCw.getAdminReq().getEmail(),
					adminCw.getAdminReq().getPzwd());

			if (findByEmailAndPzwd != null) {
				CaseWorkerEntity findByMail = cwRepo.findByMail(adminCw.getCwReq().getMail());
				if (findByMail != null) {
					return false;
				}
				CaseWorkerEntity entity = new CaseWorkerEntity();
				BeanUtils.copyProperties(adminCw.getCwReq(), entity);
				entity.setAccStatus("inactive");
				entity.setRole("caseworker");
				String generatePwd = PwdUtils.generatePwd();
				entity.setPwd(generatePwd);
				cwRepo.save(entity);

				String to = adminCw.getCwReq().getMail();

				String subject = "change your password";

				StringBuilder body = new StringBuilder();

				body.append("  your email : " + to);

				body.append("  temparory password : " + generatePwd);

				body.append("  http://localhost:8087/reset?pwdclick here to change your password");

				utils.sendMail(to, subject, body.toString());

				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("error occured while creating account" + e.getMessage());
		}
		return false;

	}

	public List<CaseWorkerEntity> viewAccounts() {

		List<CaseWorkerEntity> view = cwRepo.findAll();

		return view;
	}

	public boolean editAccount(Integer id, CaseWorkerEntity cwEntity) {

		Optional<CaseWorkerEntity> findById = cwRepo.findById(id);

		if (findById.isPresent()) {
			CaseWorkerEntity caseWorkerEntity = findById.get();
			caseWorkerEntity.setName(cwEntity.getName());
			caseWorkerEntity.setMail(cwEntity.getMail());
			caseWorkerEntity.setPhno(cwEntity.getPhno());
			caseWorkerEntity.setGender(cwEntity.getGender());
			caseWorkerEntity.setDob(cwEntity.getDob());
			caseWorkerEntity.setSsn(cwEntity.getSsn());
			caseWorkerEntity.setAccStatus(cwEntity.getAccStatus());
			caseWorkerEntity.setRole(cwEntity.getRole());
			cwRepo.save(caseWorkerEntity);
			return true;
		}
		return false;
	}

	public boolean createPlan(PlanRequest pReq) {
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(pReq, planEntity);
		planEntity.setAccStatus("inactive");
		planRepo.save(planEntity);
		return true;
	}

	public boolean editPlan(Integer id, PlanEntity pEntity) {
		Optional<PlanEntity> findById = planRepo.findById(id);
		if (findById.isPresent()) {
			PlanEntity planEntity = findById.get();

			planEntity.setName(pEntity.getName());
			planEntity.setAccStatus(pEntity.getAccStatus());
			planEntity.setStartDate(pEntity.getStartDate());
			pEntity.setEndDate(pEntity.getEndDate());

			planRepo.save(planEntity);
			return true;
		}
		return false;
	}

	@Override
	public List<PlanEntity> viewPlans() {
		List<PlanEntity> view = planRepo.findAll();

		return view;
	}

	@Override
	public List<DashBoardEntity> getDashboardData() {

		List<DashBoardEntity> dashboardData = dashBoardRepo.findAll();

		return dashboardData;
	}

	private Integer caseNo = 2158;

	public String createApplication(ApplicationRequest applicationReq) {

		try {
			ApplicationEntity entity = new ApplicationEntity();
			BeanUtils.copyProperties(applicationReq, entity);
			entity.setCaseNo(caseNo);
			appRepo.save(entity);
			caseNo++;
			return "success";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "application failed to created";
		}

	}

	@Override
	public List<ApplicationEntity> viewApplications() {

		List<ApplicationEntity> viewApplications = appRepo.findAll();

		return viewApplications;
	}

}
