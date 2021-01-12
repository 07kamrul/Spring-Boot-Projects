package JobIT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import JobIT.Model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
