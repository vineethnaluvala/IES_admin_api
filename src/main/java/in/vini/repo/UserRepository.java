package in.vini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vini.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByUserMail(String userMail);
}
