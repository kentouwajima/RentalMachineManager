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
    this.locationRepository = locationRepository; // インスタンスを初期化
  }

  // 全ての営業所情報を取得するメソッド
  public List<Location> getAllLocations() {
    return locationRepository.findAll(); // LocationRepositoryから営業所のデータを取得して返却
  }

  // 新しい営業所情報を保存するメソッド
  public void saveLocation(Location location) {
    locationRepository.insertLocation(location); // LocationRepositoryに新規営業所データを挿入
  }

  // 営業所IDを指定して詳細情報を取得するメソッド
  public Location getLocationById(Long id) {
    return locationRepository.findById(id); // LocationRepositoryから指定IDの営業所データを取得
  }

  // 営業所情報を更新するメソッド
  public void updateLocation(Location location) {
    locationRepository.updateLocation(location); // LocationRepositoryを通じて営業所データを更新
  }

}
