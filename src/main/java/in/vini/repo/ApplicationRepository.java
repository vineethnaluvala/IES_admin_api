package in.vini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vini.entity.ApplicationEntity;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {

}
