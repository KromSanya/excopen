package excopen.backend.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TourAccessibility {
    WITH_CHILDREN("with_children"),
    WITHOUT_CHILDREN("without_children");

    private final String value;

    TourAccessibility(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TourAccessibility fromValue(String value) {
        for (TourAccessibility item : values()) {
            if (item.value.equalsIgnoreCase(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}