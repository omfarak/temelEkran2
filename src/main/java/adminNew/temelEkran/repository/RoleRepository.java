package adminNew.temelEkran.repository;

import adminNew.temelEkran.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
