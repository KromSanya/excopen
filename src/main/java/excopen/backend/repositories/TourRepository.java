package excopen.backend.repositories;

import excopen.backend.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    List<Tour> findByLocationId(Long locationId);

    List<Tour> findByDuration(String duration);

    @Query(value = "SELECT * FROM tours ORDER BY vector_representation <=> CAST(:preferencesVector AS vector) LIMIT 10", nativeQuery = true)
    List<Tour> findRecommendedTours(@Param("preferencesVector") String preferencesVector);

    @Query(value = "SELECT * FROM tours " +
            "WHERE id <> :tourId " +
            "ORDER BY vector_representation <-> CAST(:vector AS vector) " +
            "LIMIT 10", nativeQuery = true)
    List<Tour> findSimilarTours(@Param("tourId") Long tourId, @Param("vector") String vector);
}
