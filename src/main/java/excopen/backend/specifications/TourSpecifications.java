package excopen.backend.specifications;

import excopen.backend.constants.TourType;
import excopen.backend.constants.TransportType;
import excopen.backend.dto.FilterToursDTO;
import excopen.backend.entities.Tour;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TourSpecifications {

    public static Specification<Tour> hasTitle(String title) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Tour> hasLocationId(Long locationId) {
        return (root, query, cb) -> cb.equal(root.get("locationId"), locationId);
    }

    public static Specification<Tour> hasPriceGreaterThanOrEqualTo(Integer priceFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), priceFrom);
    }

    public static Specification<Tour> hasPriceLessThanOrEqualTo(Integer priceTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), priceTo);
    }

    public static Specification<Tour> hasDurationGreaterThanOrEqualTo(BigDecimal durationFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("duration"), durationFrom);
    }

    public static Specification<Tour> hasDurationLessThanOrEqualTo(BigDecimal durationTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("duration"), durationTo);
    }

    public static Specification<Tour> hasRouteLengthGreaterThanOrEqualTo(BigDecimal routeLengthFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("routeLength"), routeLengthFrom);
    }

    public static Specification<Tour> hasRouteLengthLessThanOrEqualTo(BigDecimal routeLengthTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("routeLength"), routeLengthTo);
    }

    public static Specification<Tour> hasRatingGreaterThanOrEqualTo(BigDecimal ratingFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("rating"), ratingFrom);
    }

    public static Specification<Tour> hasRatingLessThanOrEqualTo(BigDecimal ratingTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("rating"), ratingTo);
    }

    public static Specification<Tour> hasTourType(TourType tourType) {
        return (root, query, cb) -> cb.equal(root.get("tourType"), tourType);
    }

    public static Specification<Tour> hasTransportType(TransportType transportType) {
        return (root, query, cb) -> cb.equal(root.get("transportType"), transportType);
    }

    public static Specification<Tour> hasMinAge(Integer minAge) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("minAge"), minAge);
    }

    public static Specification<Tour> hasCapacity(Integer capacity) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("maxCapacity"), capacity);
    }

    public static Specification<Tour> buildFilterSpec(FilterToursDTO filter) {
        Specification<Tour> spec = Specification.where(null);

        if (filter.getTitle() != null && !filter.getTitle().trim().isEmpty()) {
            spec = spec.and(hasTitle(filter.getTitle()));
        }
        if (filter.getLocationId() != null) {
            spec = spec.and(hasLocationId(filter.getLocationId()));
        }
        if (filter.getPriceFrom() != null) {
            spec = spec.and(hasPriceGreaterThanOrEqualTo(filter.getPriceFrom()));
        }
        if (filter.getPriceTo() != null) {
            spec = spec.and(hasPriceLessThanOrEqualTo(filter.getPriceTo()));
        }
        if (filter.getDurationFrom() != null) {
            spec = spec.and(hasDurationGreaterThanOrEqualTo(filter.getDurationFrom()));
        }
        if (filter.getDurationTo() != null) {
            spec = spec.and(hasDurationLessThanOrEqualTo(filter.getDurationTo()));
        }
        if (filter.getRouteLengthFrom() != null) {
            spec = spec.and(hasRouteLengthGreaterThanOrEqualTo(filter.getRouteLengthFrom()));
        }
        if (filter.getRouteLengthTo() != null) {
            spec = spec.and(hasRouteLengthLessThanOrEqualTo(filter.getRouteLengthTo()));
        }
        if (filter.getRatingFrom() != null) {
            spec = spec.and(hasRatingGreaterThanOrEqualTo(filter.getRatingFrom()));
        }
        if (filter.getRatingTo() != null) {
            spec = spec.and(hasRatingLessThanOrEqualTo(filter.getRatingTo()));
        }
        if (filter.getTourType() != null) {
            spec = spec.and(hasTourType(filter.getTourType()));
        }
        if (filter.getTransportType() != null) {
            spec = spec.and(hasTransportType(filter.getTransportType()));
        }
        if (filter.getMinAge() != null) {
            spec = spec.and(hasMinAge(filter.getMinAge()));
        }
        if (filter.getCapacity() != null) {
            spec = spec.and(hasCapacity(filter.getCapacity()));
        }

        return spec;
    }
}
