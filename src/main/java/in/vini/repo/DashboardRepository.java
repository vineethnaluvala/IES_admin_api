package in.vini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vini.entity.DashBoardEntity;

public interface DashboardRepository extends JpaRepository<DashBoardEntity, Integer> {

}
