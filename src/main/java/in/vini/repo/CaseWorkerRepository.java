package in.vini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vini.entity.CaseWorkerEntity;

public interface CaseWorkerRepository extends JpaRepository<CaseWorkerEntity, Integer> {

	public CaseWorkerEntity findByMail(String mail);
}
