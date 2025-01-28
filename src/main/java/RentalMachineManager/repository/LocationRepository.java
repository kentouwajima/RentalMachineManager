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
  List<Location> findAll();

  @Insert("INSERT INTO locations (name, address) VALUES (#{name}, #{address})")
  void insertLocation(Location location);

  @Select("SELECT * FROM locations WHERE id = #{id}")
  Location findById(Long id);

  @Update("UPDATE locations SET name = #{name}, address = #{address} WHERE id = #{id}")
  void updateLocation(Location location);

}
