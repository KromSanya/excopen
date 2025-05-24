package excopen.backend.servicesImpl;

import excopen.backend.entities.Favorite;
import excopen.backend.entities.Tour;
import excopen.backend.entities.User;
import excopen.backend.exceptions.NotFoundException;
import excopen.backend.iservices.IFavoriteService;
import excopen.backend.repositories.FavoriteRepository;
import excopen.backend.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final TourRepository tourRepository;
    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,
                               TourRepository tourRepository) {
        this.favoriteRepository = favoriteRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    @Transactional
    public void addTourToFavorites(User user, Long tourId) {
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
    public void addToursToFavourites(User user, List<Long> tourIds) {
        List<Tour> tours = tourRepository.findAllById(tourIds);

        if (tours.size() != tourIds.size()) {
            Set<Long> existingIds = tours.stream()
                    .map(Tour::getId)
                    .collect(Collectors.toSet());

            List<Long> notFoundIds = tourIds.stream()
                    .filter(id -> !existingIds.contains(id))
                    .toList();

            throw new NotFoundException("Tours not found with IDs: " + notFoundIds);
        }

        Set<Long> existingFavorites = favoriteRepository.findExistingIds(
                user.getId(),
                tourIds
        );

        List<Favorite> newFavorites = tours.stream()
                .filter(t -> !existingFavorites.contains(t.getId()))
                .map(t -> {
                    Favorite favorite = new Favorite();
                    favorite.setUser(user);
                    favorite.setTour(t);
                    return favorite;
                })
                .collect(Collectors.toList());

        if (!newFavorites.isEmpty()) {
            favoriteRepository.saveAll(newFavorites);
        }
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
