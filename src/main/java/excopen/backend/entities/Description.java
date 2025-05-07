package excopen.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "description")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    private String mainInfo;

    private String whatToExpect;

    private List<String> places;

    private List<String> topics;

    private String orgDetails;

    private String meetingPlace;
}
