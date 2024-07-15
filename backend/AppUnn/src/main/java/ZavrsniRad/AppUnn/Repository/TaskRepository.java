package ZavrsniRad.AppUnn.Repository;

import ZavrsniRad.AppUnn.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT t FROM Task t WHERE t.level.id = :levelId")
    List<Task> findByLevelId(@Param("levelId") Long levelId);

    List<Task> findAll();

    @Query("SELECT u FROM Task u WHERE u.id = :id")
    Task findByTaskId(@Param("id") Long id);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Task u where u.id = :id")
    void deleteById(@Param("id") Long id);



}
