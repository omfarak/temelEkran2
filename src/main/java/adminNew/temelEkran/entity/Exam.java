package adminNew.temelEkran.entity;

import adminNew.temelEkran.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "exam")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int examId;

    private String name;  // Sınavın adı
    private String description;  // Sınavın açıklaması
    private LocalDateTime date;  // Sınavın tarihi ve saati
    private int duration;  // Sınavın süresi (dakika cinsinden)
    private int maxParticipants;  // Maksimum katılımcı sayısı
    private int registeredParticipants;  // Kayıtlı öğrenci sayısı
    private boolean isActive;  // Sınavın aktif olup olmadığı
    private LocalDateTime registrationDeadline;  // Kayıt için son tarih
    private String course;  // Sınavın ilişkili olduğu seviye (varsa)
    private String language;  // Sınavın yapıldığı dil
    private String examMaterial;  // Sınav materyalleri
    private String status;  // Sınavın durumu (planlandı, tamamlandı, iptal edildi)
    private LocalDateTime createdDate;  // Oluşturulma tarihi
    private LocalDateTime updatedDate;
    private String schoolName;
    private String schoolAddress;

    private Boolean inMyList = false;

    private int schriftlichTelcEntryPrice;
    private int schriftlichComissionFeePrice;
    private int schriftlichSellingPrice;
    private int mundlicheTelcEntryPrice;
    private int mundlicheComissionFeePrice;
    private int mundlicheSellingPrice;
    private int gesamtlischeTelcEntryPrice;
    private int gesamtlischeComissionFeePrice;
    private int gesamtlischeSellingPrice;




    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Student> registeredStudents = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pruferId", referencedColumnName = "id")
    private Prufer prufer;





//    @ManyToOne
//    @JoinTable(
//            name = "school_exam",
//            joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "examId")
//    )
//    private School school;

}



