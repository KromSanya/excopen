package excopen.backend.iservices;

import excopen.backend.dto.DescriptionDTO;
import excopen.backend.entities.Description;
import java.util.Optional;


public interface IDescriptionService {
    Description createDescription(Description description, Long tourId);
    Optional<Description> getDescriptionById(Long descriptionId);
    Description getDescriptionByTourId(Long tourId);
    Description updateDescription(Description description);
    void deleteDescription(Long descriptionId);
}
