package RentalMachineManager.repository;

import RentalMachineManager.model.Machine;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MachineRepository {
  @Select("SELECT * FROM machines")
  List<Machine> findAll();

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
  Machine findById(int id);

  @Insert("""
        INSERT INTO machines (name, manufacturer, model, status_id, location_id)
        VALUES (#{name}, #{manufacturer}, #{model}, #{status.id}, #{location.id})
    """)
  void insertMachine(Machine machine);

}
