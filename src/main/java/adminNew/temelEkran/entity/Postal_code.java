package adminNew.temelEkran.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "postal_codes")
@Data
public class Postal_code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "country_code", length = 10)
    private String countryCode;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column(name = "place_name", length = 255)
    private String placeName;

    @Column(name = "admin_name", length = 255)
    private String adminName;

    @Column(name = "admin_code", length = 10)
    private String adminCode;

    @Column(name = "admin_name3", length = 255)
    private String adminName3;

    @Column(name = "admin_code3")
    private Integer adminCode3;

    @Column(name = "latitude", precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6)
    private BigDecimal longitude;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "object_id", length = 50)
    private String objectId;

    @Column(name = "class_name", length = 50)
    private String className;
}
