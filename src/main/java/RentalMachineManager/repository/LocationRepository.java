package RentalMachineManager.repository;

import RentalMachineManager.model.Location;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LocationRepository {

  // locationsテーブルから全ての営業所データを取得
  @Select("SELECT * FROM locations")
  List<Location> findAll();

}
