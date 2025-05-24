package excopen.backend.repositories;

import excopen.backend.entities.Favorite;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f.tour.id FROM Favorite f WHERE f.user.id = :userId AND f.tour.id IN :tourIds")
    Set<Long> findExistingIds(Long userId, List<Long> tourIds);

    boolean existsByUserAndTour(User user, Tour tour);

    Optional<Favorite> findByUserAndTour(User user, Tour tour);

    @Query("SELECT f.tour FROM Favorite f WHERE f.user = :user")
    List<Tour> findToursByUser(User user);
}
