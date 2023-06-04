package in.vini.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.vini.entity.PlanEntity;
import in.vini.entity.UserEntity;
import in.vini.request.PlanRequest;
import in.vini.request.UserRequest;
import in.vini.response.PlanResponse;
import in.vini.response.UserResponse;
import in.vini.service.AdminServiceImpl;

@RestController
public class AdminRestController {

	private Logger logger = LoggerFactory.getLogger(AdminRestController.class);

	@Autowired
	private AdminServiceImpl adminService;

	@PostMapping("/create-account")
	public ResponseEntity<UserResponse> createUserAccount(@RequestBody UserRequest userReq) {

		try {

			String createAccount = adminService.createAccount(userReq);

			if (createAccount.contains("account creation success")) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			logger.error("error occured while creating account for caswworker : ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/view-accounts")
	public ResponseEntity<List<UserEntity>> viewAccounts() {

		try {
			List<UserEntity> viewAccounts = adminService.viewAccounts();

			for (UserEntity entity : viewAccounts) {
				entity.setUserPwd(null);
			}

			return new ResponseEntity<>(viewAccounts, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error occurred while retrieving accounts: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/edit-account/{id}")
	public ResponseEntity<UserEntity> editAccount(@PathVariable("id") Integer id, @RequestBody UserRequest userReq) {

		try {

			boolean editAccount = adminService.editAccount(id, userReq);
			if (editAccount) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			logger.error("error occured while updating account for caseworker : ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/create-plan")
	public ResponseEntity<PlanResponse> createPlan(@RequestBody PlanRequest planReq) {

		try {
			boolean createPlan = adminService.createPlan(planReq);
			if (createPlan) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("error occured while creating plan : ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/view-plans")
	public ResponseEntity<List<PlanEntity>> viewPlans() {

		try {
			List<PlanEntity> viewPlans = adminService.viewPlans();
			return new ResponseEntity<>(viewPlans, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("error occurred while retrieving plans: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/edit-plan/{id}")
	public ResponseEntity<PlanEntity> editPlan(@PathVariable("id") Integer id, @RequestBody PlanRequest planReq) {
		try {
			boolean editPlan = adminService.editPlan(id, planReq);
			if (editPlan) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("error occured while updating plan : ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
