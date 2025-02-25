package excopen.backend.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "tour_tags")
public class TourTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assumes auto-increment primary key
    private Long id;

    @JoinColumn(name = "tour_id", nullable = false)
    private Long tourId;

    @JoinColumn(name = "tag_id", nullable = false)
    private Long tagId;
}
