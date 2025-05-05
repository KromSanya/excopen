package excopen.backend.entities;

import excopen.backend.config.TagCountHolder;
import excopen.backend.constants.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String googleId;

    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Tour> createdTours;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JdbcTypeCode(SqlTypes.VECTOR)
    private int[] preferencesVector;

    @JdbcTypeCode(SqlTypes.VECTOR)
    private int[] secondVector;
    private String description;
    private String city;
    private String avatarUrl;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "vk",       column = @Column(name = "contacts_vk")),
            @AttributeOverride(name = "telegram", column = @Column(name = "contacts_telegram")),
            @AttributeOverride(name = "phone",    column = @Column(name = "contacts_phone"))
    })
    private Contact contacts;

    private Double guideRating = 0.0;     // средняя оценка гида
    private Integer totalReviews = 0;     // количество отзывов к его турам

//    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.role = Role.USER;

        this.preferencesVector = new int[TagCountHolder.TAG_COUNT];
        this.secondVector = new int[TagCountHolder.TAG_COUNT];
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
