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

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CaseWorkerRepository cwRepo;
	@Autowired
	private PlanEntityRepository planRepo;

	public boolean createAccount(CaseWorkerEntity cwEntity) {

		CaseWorkerEntity findByMail = cwRepo.findByMail(cwEntity.getMail());
		if (findByMail != null) {
			return false;
		}
		CaseWorkerEntity entity = new CaseWorkerEntity();
		BeanUtils.copyProperties(cwEntity, entity);
		entity.setAccStatus("inactive");
		cwRepo.save(entity);
		return true;
	}

	public List<CaseWorkerEntity> viewAccounts() {

		List<CaseWorkerEntity> view = cwRepo.findAll();

		return view;
	}

	public boolean editAccount(CaseWorkerEntity cwEntity) {

		Optional<CaseWorkerEntity> findById = cwRepo.findById(cwEntity.getCid());
		if (findById.isPresent()) {
			CaseWorkerEntity caseWorkerEntity = findById.get();

			if (caseWorkerEntity == null) {
				return false;
			}
			CaseWorkerEntity entity = new CaseWorkerEntity();
			BeanUtils.copyProperties(cwEntity, entity);

			cwRepo.save(entity);

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

	public boolean editPlan(PlanEntity pEntity) {
		Optional<PlanEntity> findById = planRepo.findById(pEntity.getPid());
		if (findById.isPresent()) {
			PlanEntity planEntity = findById.get();
			if (planEntity == null) {
				return false;
			}

			PlanEntity entity = new PlanEntity();
			BeanUtils.copyProperties(pEntity, entity);
			planRepo.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public List<PlanEntity> viewPlans() {
		List<PlanEntity> view = planRepo.findAll();

		return view;
	}

}
