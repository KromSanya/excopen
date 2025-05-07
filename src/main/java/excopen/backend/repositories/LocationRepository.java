package excopen.backend.repositories;

import excopen.backend.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

//    @Query("SELECT new excopen.backend.dto.LocationResponseDto(" +
//            "l.id, l.city, l.region, l.country, l.imageUrl, COUNT(t)) " +
//            "FROM Location l " +
//            "LEFT JOIN l.tours t " +  // Используем название поля из Entity
//            "GROUP BY l.id, l.city, l.region, l.country, l.imageUrl")
//    List<LocationResponseDTO> findAllWithTourCount();
}
