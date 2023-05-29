package in.vini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vini.entity.CaseWorkerEntity;
import in.vini.entity.PlanEntity;
import in.vini.repo.CaseWorkerRepository;
import in.vini.repo.PlanEntityRepository;
import in.vini.utils.EmailUtils;
import in.vini.utils.PwdUtils;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CaseWorkerRepository cwRepo;
	@Autowired
	private PlanEntityRepository planRepo;

	@Autowired
	private EmailUtils utils;

	public boolean createAccount(CaseWorkerEntity cwEntity) {

		CaseWorkerEntity findByMail = cwRepo.findByMail(cwEntity.getMail());
		if (findByMail != null) {
			return false;
		}
		CaseWorkerEntity entity = new CaseWorkerEntity();
		BeanUtils.copyProperties(cwEntity, entity);
		String generatePwd = PwdUtils.generatePwd();
		entity.setPwd(generatePwd);
		cwRepo.save(entity);

		String to = cwEntity.getMail();

		String subject = "change your password";

		StringBuilder body = new StringBuilder();

		body.append("use below temparory password to change your password");

		body.append("temparory password : " + generatePwd);

		body.append("<a href =\"http://localhost:8087/reset?pwd=" + to + "\">click here to change your password</a>");

		utils.sendMail(to, subject, body.toString());

		return true;
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

	public boolean createPlan(PlanEntity pEntity) {
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(pEntity, planEntity);
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
	public String getDashboardData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createApplication() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewApplications() {
		// TODO Auto-generated method stub
		return null;
	}

}
