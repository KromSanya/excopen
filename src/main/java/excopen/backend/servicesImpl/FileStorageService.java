package excopen.backend.servicesImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final String TOUR_UPLOAD_DIR = "uploads/tour_images";
    private static final String REVIEW_UPLOAD_DIR = "uploads/review_images";
    private static final String USER_AVATAR_DIR = "uploads/user_avatars";

    public String storeTourImage(MultipartFile file) {
        return storeFile(file, TOUR_UPLOAD_DIR);
    }

    public String storeReviewImage(MultipartFile file) {
        return storeFile(file, REVIEW_UPLOAD_DIR);
    }

    public String storeUserAvatar(MultipartFile file) {
        return storeFile(file, USER_AVATAR_DIR);
    }

    private String storeFile(MultipartFile file, String uploadDir) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/static/" + uploadDir.split("/")[1] + "/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить файл", e);
        }
    }
}