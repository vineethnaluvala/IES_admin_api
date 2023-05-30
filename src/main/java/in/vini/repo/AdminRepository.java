package in.vini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vini.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

	public AdminEntity findByEmailAndPzwd(String mail, String pzwd);
}
