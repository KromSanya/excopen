package excopen.backend.entities;

import excopen.backend.constants.TourType;
import excopen.backend.constants.TransportType;
import excopen.backend.constants.TourAccessibility; // Добавляем enum для доступности
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tours")
public class Tour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    private int price;

    private Double duration;        // в часах
    private Double routeLength;     // в километрах

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @JdbcTypeCode(SqlTypes.VECTOR)
    private int[] vectorRepresentation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", updatable = false)
    private User creator;

    @OneToOne(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Description description;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TourImage> images;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    private Integer minAge;
    private Integer maxCapacity;

    private Double rating;         // от 0.0 до 5.0
    private Integer reviewCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "tour_type")
    private TourType tourType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_type")
    private TransportType transportType;

    @Enumerated(EnumType.STRING)  // Добавляем новый enum
    @Column(name = "tour_accessibility")
    private TourAccessibility accessibility;  // доступность для детей

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.rating = 0.0;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
