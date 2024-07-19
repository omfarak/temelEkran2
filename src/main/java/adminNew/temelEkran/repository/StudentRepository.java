package adminNew.temelEkran.repository;

import adminNew.temelEkran.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository <Student,Integer> {

}
