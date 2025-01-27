package RentalMachineManager.repository;

import RentalMachineManager.model.Machine;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MachineRepository {

  // 全ての機材情報を取得
  @Select("SELECT * FROM machines")
  List<Machine> findAll(); // 機材一覧を取得

  // 機材IDを指定して詳細情報を取得
  @Select("""
      SELECT 
        m.id AS machine_id, m.name AS machine_name, m.manufacturer, m.model,
        s.id AS status_id, s.status AS status_name,
        l.id AS location_id, l.name AS location_name, l.address AS location_address
      FROM machines m
      LEFT JOIN machine_statuses s ON m.status_id = s.id
      LEFT JOIN locations l ON m.location_id = l.id
      WHERE m.id = #{id}
      """)
  @Results({
      @Result(property = "id", column = "machine_id"),
      @Result(property = "name", column = "machine_name"),
      @Result(property = "manufacturer", column = "manufacturer"),
      @Result(property = "model", column = "model"),
      @Result(property = "status.id", column = "status_id"),
      @Result(property = "status.status", column = "status_name"),
      @Result(property = "location.id", column = "location_id"),
      @Result(property = "location.name", column = "location_name"),
      @Result(property = "location.address", column = "location_address")
  })
  Machine findById(int id); // 機材IDに基づき詳細情報を取得

  // 新規機材をデータベースに挿入
  @Insert("""
        INSERT INTO machines (name, manufacturer, model, status_id, location_id)
        VALUES (#{name}, #{manufacturer}, #{model}, #{status.id}, #{location.id})
    """)
  void insertMachine(Machine machine); // 新規機材を追加する

  // 機材情報を更新
  @Update("""
    UPDATE machines
    SET name = #{name}, manufacturer = #{manufacturer}, model = #{model}, 
        status_id = #{status.id}, location_id = #{location.id}
    WHERE id = #{id}
""")
  void updateMachine(Machine machine); // 機材情報の更新処理

  // 機材をIDで削除
  @Delete("DELETE FROM machines WHERE id = #{id}")
  void deleteById(int id); // 機材を削除する

  @Select("""
    SELECT 
        m.id AS machine_id, m.name AS machine_name, m.manufacturer, m.model,
        s.id AS status_id, s.status AS status_name,
        l.id AS location_id, l.name AS location_name, l.address AS location_address
    FROM machines m
    LEFT JOIN machine_statuses s ON m.status_id = s.id
    LEFT JOIN locations l ON m.location_id = l.id
    WHERE 
        (#{name} IS NULL OR m.name LIKE CONCAT('%', #{name}, '%')) 
        AND (#{manufacturer} IS NULL OR m.manufacturer LIKE CONCAT('%', #{manufacturer}, '%'))
        AND (#{model} IS NULL OR m.model LIKE CONCAT('%', #{model}, '%'))
        AND (#{status} IS NULL OR m.status_id = #{status})
        AND (#{location} IS NULL OR m.location_id = #{location})
""")
  @Results({
      @Result(property = "id", column = "machine_id"),
      @Result(property = "name", column = "machine_name"),
      @Result(property = "manufacturer", column = "manufacturer"),
      @Result(property = "model", column = "model"),
      @Result(property = "status.id", column = "status_id"),
      @Result(property = "status.status", column = "status_name"),
      @Result(property = "location.id", column = "location_id"),
      @Result(property = "location.name", column = "location_name"),
      @Result(property = "location.address", column = "location_address")
  })
  List<Machine> searchMachines(String name, String manufacturer, String model, Integer status, Integer location);

}
