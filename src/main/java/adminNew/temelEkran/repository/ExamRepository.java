package adminNew.temelEkran.repository;

import adminNew.temelEkran.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {
    List<Exam> findExamsBySchoolName(String schoolName);
}
