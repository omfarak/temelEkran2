package adminNew.temelEkran.repository;


import adminNew.temelEkran.entity.ExamStudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamStudentRegistrationRepository extends JpaRepository<ExamStudentRegistration,Integer> {

    List<ExamStudentRegistration> findExamStudentRegistrationsByExam_ExamId(int examId);


}
