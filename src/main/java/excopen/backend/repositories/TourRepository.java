package excopen.backend.repositories;

import com.querydsl.core.types.Predicate;
import excopen.backend.entities.Location;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long>, QuerydslPredicateExecutor<Tour> {

    List<Tour> findByLocation(Location location);

    List<Tour> findByDuration(Double duration);

    List<Tour> findByCreator(User creator);

    @Query(value = "SELECT * FROM tours ORDER BY vector_representation <=> CAST(:preferencesVector AS vector) LIMIT 10", nativeQuery = true)
    List<Tour> findRecommendedTours(@Param("preferencesVector") String preferencesVector);

    @Query(value = "SELECT * FROM tours " +
            "WHERE id <> :tourId " +
            "ORDER BY vector_representation <-> CAST(:vector AS vector) " +
            "LIMIT 10", nativeQuery = true)
    List<Tour> findSimilarTours(@Param("tourId") Long tourId, @Param("vector") String vector);
}
