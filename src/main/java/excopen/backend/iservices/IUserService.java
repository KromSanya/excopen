package excopen.backend.iservices;

import excopen.backend.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);
    Optional<User> getUserById(Long userId);
    Optional<User> findByGoogleId(String googleId);
    List<User> getAllUsers();
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
    User updatePreferencesVector(Long userId, int[] preferencesVector);
}
