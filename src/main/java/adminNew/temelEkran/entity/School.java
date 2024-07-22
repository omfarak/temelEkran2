package adminNew.temelEkran.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "schools")
@Data
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String adress;

    private String phone;

    private String city;




}
