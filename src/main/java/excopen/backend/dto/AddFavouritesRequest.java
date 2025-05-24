package excopen.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddFavouritesRequest {
    private List<Long> ids;
}
