package adminNew.temelEkran.entity;

import adminNew.temelEkran.entity.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "exam")
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int examId;

    private String name;  // Sınavın adı
    private String description;  // Sınavın açıklaması
    private LocalDateTime date;  // Sınavın tarihi ve saati
    private int duration;  // Sınavın süresi (dakika cinsinden)
    private String location;  // Sınavın yapılacağı yer
    //private School school;  // Sınavın düzenlendiği okul (okul nesnesi)
    private int maxParticipants;  // Maksimum katılımcı sayısı
    private int registeredParticipants;  // Kayıtlı öğrenci sayısı
    private boolean isActive;  // Sınavın aktif olup olmadığı
    private LocalDateTime registrationDeadline;  // Kayıt için son tarih
    private String examType;  // Sınavın türü (yazılı, sözlü, vb.)
    private double price;  // Sınavın ücreti
    private String course;  // Sınavın ilişkili olduğu kurs (varsa)
    private String language;  // Sınavın yapıldığı dil
    private String examMaterial;  // Sınav materyalleri
    private String status;  // Sınavın durumu (planlandı, tamamlandı, iptal edildi)
    private LocalDateTime createdDate;  // Oluşturulma tarihi
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Student> registeredStudents = new ArrayList<>();


}
