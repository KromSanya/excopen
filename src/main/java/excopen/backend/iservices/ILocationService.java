package excopen.backend.iservices;

import excopen.backend.entities.Location;
import java.util.List;

public interface ILocationService {
    List<Location> getAllLocations();
    Location getLocationById(Long id);
  //  List<LocationResponseDTO> getAllLocationsWithTourCount();
}
