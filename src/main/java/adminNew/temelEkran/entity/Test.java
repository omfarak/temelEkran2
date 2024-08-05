package adminNew.temelEkran.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "test")
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    int deneme1;
    int deneme2;
}
