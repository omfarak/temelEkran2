package adminNew.temelEkran.repository;

import adminNew.temelEkran.entity.Postal_code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeRepository extends JpaRepository<Postal_code,Integer> {
    public Postal_code findPostal_codeByPostalCode(int postalCode);

}
