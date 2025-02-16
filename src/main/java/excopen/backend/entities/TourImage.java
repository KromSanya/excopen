package excopen.backend.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "tour_images")
public class TourImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "tour_id")
    private Long tourId;

    private String imageUrl;
}
