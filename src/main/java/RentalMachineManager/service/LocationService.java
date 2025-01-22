package RentalMachineManager.service;

import RentalMachineManager.model.Location;
import RentalMachineManager.repository.LocationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
  private final LocationRepository locationRepository;

  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public List<Location> getAllLocations() {
    return locationRepository.findAll();
  }

}
