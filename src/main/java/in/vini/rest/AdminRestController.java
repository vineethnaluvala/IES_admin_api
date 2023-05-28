package in.vini.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.vini.entity.CaseWorkerEntity;
import in.vini.entity.PlanEntity;
import in.vini.service.AdminServiceImpl;

@RestController
public class AdminRestController {

	private Logger logger = LoggerFactory.getLogger(AdminRestController.class);

	@Autowired
	private AdminServiceImpl adminService;

	@PostMapping("/create-cw-account")
	public ResponseEntity<CaseWorkerEntity> createCWAccount(@RequestBody CaseWorkerEntity entity) {

		try {

			boolean createAccount = adminService.createAccount(entity);
			if (createAccount) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			logger.error("error occured while creating account for caswworker : ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/view-accounts")
	public ResponseEntity<List<CaseWorkerEntity>> viewAccounts() {

		try {
			List<CaseWorkerEntity> viewAccounts = adminService.viewAccounts();
			return new ResponseEntity<>(viewAccounts, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error occurred while retrieving accounts: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/edit-cw-account")
	public ResponseEntity<CaseWorkerEntity> editCWAcc(@RequestBody CaseWorkerEntity cEntity) {

		try {

			boolean editAccount = adminService.editAccount(cEntity);
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
	public ResponseEntity<PlanEntity> createPlan(@RequestBody PlanEntity pEntity) {

		try {
			boolean createPlan = adminService.createPlan(pEntity);
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
			return new ResponseEntity<>(viewPlans, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error occurred while retrieving plans: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/edit-plan")
	public ResponseEntity<PlanEntity> editPlan(@RequestBody PlanEntity pEntity) {
		try {
			boolean editPlan = adminService.editPlan(pEntity);
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
