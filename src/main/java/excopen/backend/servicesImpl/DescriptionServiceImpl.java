package excopen.backend.servicesImpl;

import excopen.backend.dto.DescriptionDTO;
import excopen.backend.entities.Description;
import excopen.backend.iservices.IDescriptionService;
import excopen.backend.mapper.DescriptionMapper;
import excopen.backend.repositories.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DescriptionServiceImpl implements IDescriptionService {

    private final DescriptionRepository descriptionRepository;
    @Autowired
    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public Description createDescription(Description description, Long tourId) {
        description.setTourId(tourId);
        return descriptionRepository.save(description);
    }


    @Override
    public Optional<Description> getDescriptionById(Long descriptionId) {
        return descriptionRepository.findById(descriptionId);
    }

    @Override
    public Description getDescriptionByTourId(Long tourId) {
        return descriptionRepository.findByTourId(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Description not found"));
    }


    @Override
    public Description updateDescription(Description description) {
        return descriptionRepository.save(description);
    }

    @Override
    public void deleteDescription(Long descriptionId) {
        descriptionRepository.deleteById(descriptionId);
    }
}
