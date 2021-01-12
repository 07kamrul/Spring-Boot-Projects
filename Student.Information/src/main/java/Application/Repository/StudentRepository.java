package Application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import Application.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
//public interface StudentRepository extends CrudRepository<Student, Integer> {
	@Query(value = "SELECT s FROM Student s WHERE s.name LIKE '%' || :keyword || '%'"
			+ " OR s.phone LIKE '%' || :keyword || '%'" + " OR s.cgpa LIKE '%' || :keyword || '%'"
			+ " OR s.country LIKE '%' || :keyword || '%'" + " OR s.dob LIKE '%' || :keyword || '%'"
			+ " OR s.email LIKE '%' || :keyword || '%'" + " OR s.gender LIKE '%' || :keyword || '%'")
	public List<Student> search(@Param("keyword") String keyword);

}
