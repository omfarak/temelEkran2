package adminNew.temelEkran.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "prufer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prufer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phone;
    private String course;
    private String email;
    private String adress;
    private Boolean active;
    private String photoName;
    private String licenceName;
    private String schoolName;


    @Lob
    @Column(name ="licencePath", length = 1000)
    private String licence;

    @Lob
    @Column(name = "photoPath", length = 1000)
    private String photo;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

}
