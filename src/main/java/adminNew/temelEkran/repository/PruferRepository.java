package adminNew.temelEkran.repository;

import adminNew.temelEkran.entity.File;
import adminNew.temelEkran.entity.Prufer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PruferRepository extends JpaRepository<Prufer,Integer> {
    List<Prufer> findPrufersBySchoolName(String schoolName);
    List<Prufer> findPrufersBySchoolNameAndCourseIn(String schoolName, List<String> courses);
}
