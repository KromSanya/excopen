package excopen.backend.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.awt.*;


@Data
@Entity
@Table(name = "coordinates")
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Point coordinates;
}
