package excopen.backend.iservices;

import excopen.backend.entities.Tour;
import excopen.backend.entities.User;

import java.util.List;

public interface IFavoriteService {
    void addTourToFavorites(User user, Long tourId);
    void addToursToFavourites(User user, List<Long> tourIds);
    void removeTourFromFavorites(Long userId, Long tourId);
    List<Tour> getFavoriteToursByUser(Long userId);
}
