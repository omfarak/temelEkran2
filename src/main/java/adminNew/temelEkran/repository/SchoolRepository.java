package adminNew.temelEkran.repository;

import adminNew.temelEkran.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School,Integer> {
    School findTopByName(String name);
    School findSchoolByMail(String mail);
}
