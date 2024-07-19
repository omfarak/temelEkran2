package adminNew.temelEkran.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "student")
@Entity
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phone;
    private String email;
    private String adress;
    private String country;


}
