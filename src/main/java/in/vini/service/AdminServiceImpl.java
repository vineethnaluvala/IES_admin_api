package in.vini.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vini.entity.PlanEntity;
import in.vini.entity.UserEntity;
import in.vini.repo.IESUserRolesRepository;
import in.vini.repo.PlanEntityRepository;
import in.vini.repo.UserRepository;
import in.vini.request.PlanRequest;
import in.vini.request.UserRequest;
import in.vini.utils.EmailUtils;
import in.vini.utils.PwdUtils;

@Service
public class AdminServiceImpl implements AdminService {

	private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PlanEntityRepository plansRepo;
	@Autowired
	private IESUserRolesRepository rolesRepo;

	@Autowired
	private EmailUtils utils;

	public String createAccount(UserRequest user) {
		UserEntity entity = new UserEntity();
		try {

			UserEntity findByUserMail = userRepo.findByUserMail(user.getUserMail());

			if (findByUserMail != null) {
				return "email already exist";
			}

			BeanUtils.copyProperties(user, entity);
			String generatePwd = PwdUtils.generatePwd();
			entity.setUserPwd(generatePwd);
			userRepo.save(entity);

			String to = user.getUserMail();

			String subject = "change your password";

			StringBuilder body = new StringBuilder();

			body.append("  your email : " + to);

			body.append("  temparory password : " + generatePwd);

			body.append("  http://localhost:8087/reset?pwdclick here to change your password");

			utils.sendMail(to, subject, body.toString());

			return "account creation success";

		} catch (Exception e) {
			logger.error(e.getMessage());

			return "error occured while creating account";
		}
	}

	@Override
	public boolean editAccount(Integer id, UserRequest userReq) {

		Optional<UserEntity> findById = userRepo.findById(id);

		if (findById.isPresent()) {
			UserEntity userEntity = findById.get();
			userEntity.setUserName(userReq.getUserName());
			userEntity.setUserMail(userReq.getUserMail());
			userEntity.setUserPhno(userReq.getUserPhno());
			userEntity.setUserDob(userReq.getUserDob());
			userEntity.setUserGender(userReq.getUserGender());
			userEntity.setUserSsn(userReq.getUserSsn());
			userRepo.save(userEntity);
			return true;
		}
		return false;
	}

	@Override
	public List<UserEntity> viewAccounts() {

		List<UserEntity> users = userRepo.findAll();

		return users;
	}

	@Override

	public boolean createPlan(PlanRequest planReq) {
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(planReq, planEntity);
		plansRepo.save(planEntity);
		return true;

	}

	@Override
	public List<PlanEntity> viewPlans() {
		List<PlanEntity> plans = plansRepo.findAll();

		return plans;
	}

	@Override
	public boolean editPlan(Integer id, PlanRequest planReq) {
		Optional<PlanEntity> findById = plansRepo.findById(id);
		if (findById.isPresent()) {
			PlanEntity planEntity = findById.get();

			planEntity.setPlaneName(planReq.getPlaneName());
			planEntity.setPlanStartDate(planReq.getPlanStartDate());
			planEntity.setPlanEndDate(planReq.getPlanEndDate());
			planEntity.setPlanCategory(planReq.getPlanCategory());
			
			plansRepo.save(planEntity);
			return true;
		}
		return false;
	}
}
