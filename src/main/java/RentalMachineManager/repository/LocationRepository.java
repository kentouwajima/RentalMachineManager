package RentalMachineManager.repository;

import RentalMachineManager.model.Location;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LocationRepository {

  // locationsテーブルから全ての営業所データを取得
  @Select("SELECT * FROM locations")
  List<Location> findAll(); // 全営業所データを取得

  // 新しい営業所をlocationsテーブルに挿入
  @Insert("INSERT INTO locations (name, address) VALUES (#{name}, #{address})")
  void insertLocation(Location location); // 営業所情報を新規登録

  // 営業所IDを指定して詳細情報を取得
  @Select("SELECT * FROM locations WHERE id = #{id}")
  Location findById(Long id); // 指定されたIDの営業所を取得

  // 営業所情報を更新
  @Update("UPDATE locations SET name = #{name}, address = #{address} WHERE id = #{id}")
  void updateLocation(Location location); // 営業所情報の更新処理

}
