package RentalMachineManager.repository;

import RentalMachineManager.model.MachineStatus;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MachineStatusRepository {
  @Select("SELECT * FROM machine_statuses")
  List<MachineStatus> findAll();

}
