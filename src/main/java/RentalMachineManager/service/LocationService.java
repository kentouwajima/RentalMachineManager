package RentalMachineManager.service;

import RentalMachineManager.model.Location;
import RentalMachineManager.repository.LocationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

  // LocationRepositoryのインスタンスを注入
  private final LocationRepository locationRepository;

  // コンストラクタでLocationRepositoryを受け取り、フィールドにセット
  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  // 全ての営業所情報を取得するメソッド
  public List<Location> getAllLocations() {
    return locationRepository.findAll(); // LocationRepositoryから営業所のデータを取得
  }

  public void saveLocation(Location location) {
    locationRepository.insertLocation(location);
  }

  public Location getLocationById(Long id) {
    return locationRepository.findById(id);
  }

  public void updateLocation(Location location) {
    locationRepository.updateLocation(location);
  }

}
