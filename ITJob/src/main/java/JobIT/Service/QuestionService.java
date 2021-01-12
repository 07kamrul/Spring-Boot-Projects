package JobIT.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JobIT.Model.Question;
import JobIT.Repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public List<Question> GetAllQues() {
		return questionRepository.findAll();
	}
}
