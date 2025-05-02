package excopen.backend.servicesImpl;

import excopen.backend.entities.Favorite;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.iservices.IFavoriteService;
import excopen.backend.repositories.FavoriteRepository;
import excopen.backend.repositories.TourRepository;
import excopen.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,
                               TourRepository tourRepository,
                               UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void addTourToFavorites(Long userId, Long tourId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found with ID: " + tourId));

        if (favoriteRepository.existsByUserAndTour(user, tour)) {
            throw new IllegalArgumentException("This tour is already in the user's favorites.");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setTour(tour);
        favoriteRepository.save(favorite);
    }

    @Override
    @Transactional
    public void removeTourFromFavorites(Long userId, Long tourId) {
        User user = new User();
        user.setId(userId);
        Tour tour = new Tour();
        tour.setId(tourId);

        Favorite favorite = favoriteRepository.findByUserAndTour(user, tour)
                .orElseThrow(() -> new IllegalArgumentException("Favorite tour not found for this user."));
        favoriteRepository.delete(favorite);
    }

    @Override
    public List<Tour> getFavoriteToursByUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return favoriteRepository.findToursByUser(user);
    }
}
