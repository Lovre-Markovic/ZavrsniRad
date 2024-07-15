package ZavrsniRad.AppUnn.Repository;

import ZavrsniRad.AppUnn.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import ZavrsniRad.AppUnn.Entity.Answer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {



    @Query("SELECT u FROM Answer u WHERE u.id = :id")
    Answer findByAnswerId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Answer u WHERE u.id =:id")
    void deleteById(@Param("id") long id);

    @Query(value = "SELECT u FROM Answer u where u.task.id =:id")
    List<Answer> findByTaskId(@Param("id") long id);

}
