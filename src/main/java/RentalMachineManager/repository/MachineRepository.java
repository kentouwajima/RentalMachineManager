package RentalMachineManager.repository;

import RentalMachineManager.model.Machine;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MachineRepository {
  @Select("SELECT * FROM machines")
  List<Machine> findAll();

}
