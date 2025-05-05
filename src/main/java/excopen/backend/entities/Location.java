package excopen.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "locations")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String region;
    private String country;
    private String imageUrl;

    private Integer tourCount;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Tour> tours;
}
