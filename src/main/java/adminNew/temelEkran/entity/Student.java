package adminNew.temelEkran.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Table(name = "student")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private int postalCode;
    private int selectedExamType;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;



}
