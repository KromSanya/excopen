package excopen.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@Entity
@Table(name = "tours")
public class Tour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @JoinColumn(name = "location_id")
    private Long locationId;

    private BigDecimal price;
    private String duration;
    private BigDecimal routeLength;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JdbcTypeCode(SqlTypes.VECTOR)
    private int[] vectorRepresentation;

    @JoinColumn(name = "creator_id")
    private Long creatorId;

    private Integer minAge;
    private Integer maxCapacity;

    @Column(precision = 2, scale = 1)
    private BigDecimal rating;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.rating = BigDecimal.ZERO;

      //  this.vectorRepresentation = new float[tagCount];
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
