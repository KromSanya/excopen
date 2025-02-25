package excopen.backend.entities;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "review_images")
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "review_id")
    private Long reviewId;

    private String imageUrl;
}