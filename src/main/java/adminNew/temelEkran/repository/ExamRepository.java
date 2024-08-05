package adminNew.temelEkran.repository;

import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {
    List<Exam> findExamsBySchoolName(String schoolName);
    List<Exam> findExamsBySchoolNameAndCourse(String schoolName,String course);

    @Query("SELECT s FROM Student s JOIN s.exam e WHERE e.examId = :examId")
    List<Student> findRegisteredStudentsByExamId(int examId);
}
