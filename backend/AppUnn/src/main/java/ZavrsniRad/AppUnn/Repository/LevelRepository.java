package ZavrsniRad.AppUnn.Repository;

import ZavrsniRad.AppUnn.Entity.Level;
import ZavrsniRad.AppUnn.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> findById(Long levelId);

    List<Level> findAll();
}
