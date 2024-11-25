package excopen.backend.repositories;

import excopen.backend.entities.Favorite;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUserAndTour(User user, Tour tour); // Проверка на наличие записи

    Optional<Favorite> findByUserIdAndTourId(Long userId, Long tourId); // Поиск по ID пользователя и тура

    List<Long> findTourIdsByUserId(Long userId); // Получаем все tour_id по user_id
}


