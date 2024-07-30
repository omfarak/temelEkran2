package adminNew.temelEkran.repository;

import adminNew.temelEkran.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Integer> {
    Optional<File> findTopByName(String fileName);
}
