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

import in.vini.entity.ApplicationEntity;
import in.vini.entity.CaseWorkerEntity;
import in.vini.entity.DashBoardEntity;
import in.vini.entity.PlanEntity;
import in.vini.request.AdminCw;
import in.vini.request.ApplicationRequest;
import in.vini.request.PlanRequest;
import in.vini.response.ApplicationResponse;
import in.vini.response.CwResponse;
import in.vini.service.AdminServiceImpl;

@RestController
public class AdminRestController {

	private Logger logger = LoggerFactory.getLogger(AdminRestController.class);

	@Autowired
	private AdminServiceImpl adminService;

	@PostMapping("/create-cw-account")
	public ResponseEntity<CwResponse> createCWAccount(@RequestBody AdminCw adminCw) {

		try {

			boolean createAccount = adminService.createAccount(adminCw);
			if (createAccount) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			logger.error("error occured while creating account for caswworker : ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/view-cw-accounts")
	public ResponseEntity<List<CaseWorkerEntity>> viewAccounts() {

		try {
			List<CaseWorkerEntity> viewAccounts = adminService.viewAccounts();

			for (CaseWorkerEntity entity : viewAccounts) {
				entity.setPwd(null);
			}

			return new ResponseEntity<>(viewAccounts, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error occurred while retrieving accounts: ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/edit-cw-account/{id}")
	public ResponseEntity<CaseWorkerEntity> editCWAcc(@PathVariable("id") Integer id,
			@RequestBody CaseWorkerEntity cEntity) {

		try {

			boolean editAccount = adminService.editAccount(id, cEntity);
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
	public ResponseEntity<PlanEntity> createPlan(@RequestBody PlanRequest pReq) {

		try {
			boolean createPlan = adminService.createPlan(pReq);
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
	public ResponseEntity<PlanEntity> editPlan(@PathVariable("id") Integer id, @RequestBody PlanEntity pEntity) {
		try {
			boolean editPlan = adminService.editPlan(id, pEntity);
			if (editPlan) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("error occured while updating plan : ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/dashboard-data")
	public ResponseEntity<List<DashBoardEntity>> dashboardData() {

		List<DashBoardEntity> dashboardData = adminService.getDashboardData();

		return new ResponseEntity<>(dashboardData, HttpStatus.CREATED);

	}

	@PostMapping("/create-applications")
	public ResponseEntity<ApplicationResponse> createApplication(@RequestBody ApplicationRequest applicationReq) {

		try {
			String createApplication = adminService.createApplication(applicationReq);

			if (createApplication.contains("success")) {

				return new ResponseEntity<>(HttpStatus.CREATED);

			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("error occured while creating application : ", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/view-applications")
	public ResponseEntity<List<ApplicationEntity>> viewApplications() {

		List<ApplicationEntity> viewApplications = adminService.viewApplications();

		return new ResponseEntity<>(viewApplications, HttpStatus.CREATED);

	}

}
