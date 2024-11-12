package excopen.backend.iservices;

import excopen.backend.entities.ReviewImage;

import java.util.List;
import java.util.Optional;

public interface IReviewImageService {

    ReviewImage addImageToReview(Long reviewId, String imageUrl);

    List<ReviewImage> getImagesByReview(Long reviewId);

    Optional<ReviewImage> getImageById(Long reviewImageId);

    void deleteImage(Long reviewImageId);
}

