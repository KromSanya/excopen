package excopen.backend.repositories;

import excopen.backend.entities.Review;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT AVG(r.rating) FROM Review r 
        WHERE r.tour.creator = :creator
    """)
    Double calculateAverageRatingByCreator(@Param("creator") User creator);

    @Query("""
        SELECT COUNT(r.id) FROM Review r 
        WHERE r.tour.creator = :creator
    """)
    Integer countReviewsByCreator(@Param("creator") User creator);

    @Query("""
    SELECT AVG(r.rating) FROM Review r 
    WHERE r.tour.creator.id = :creatorId
""")
    Double calculateAverageRatingByCreatorId(@Param("creatorId") Long creatorId);

    @Query("""
    SELECT COUNT(r) FROM Review r 
    WHERE r.tour.creator.id = :creatorId
""")
    Integer countReviewsByCreatorId(@Param("creatorId") Long creatorId);

    Boolean existsByUserIdAndTourId(Long userId, Long tourId);


    List<Review> findByTour(Tour tour);

    List<Review> findByUser(User user);
}
