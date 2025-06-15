package excopen.backend.constants;

import org.springframework.data.domain.Sort;

import java.util.Arrays;

public enum TourSort {
    FOR_POPULAR("reviewCount", Sort.Direction.DESC),
    FOR_CHEAP("priceForPerson", Sort.Direction.ASC),
    FOR_EXPENSIVE("priceForPerson", Sort.Direction.DESC),
    FOR_RATING("rating", Sort.Direction.DESC),
    FOR_RECOMMENDATION(null, null);

    private final String field;
    private final Sort.Direction direction;

    TourSort(String field, Sort.Direction direction) {
        this.field = field;
        this.direction = direction;
    }

    public static Sort parseSort(String sortValue) {
        try {
            return valueOf(sortValue.toUpperCase()).toSort();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unknown sort value: " + sortValue +
                            ". Supported values: " + Arrays.toString(values())
            );
        }
    }

    public Sort toSort() {
        return Sort.by(direction, field);
    }

    public boolean isRecommendation() {
        return this == FOR_RECOMMENDATION;
    }
}