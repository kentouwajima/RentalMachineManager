package RentalMachineManager.service;

import RentalMachineManager.model.Machine;
import RentalMachineManager.repository.MachineRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineService {
  private final MachineRepository machineRepository;

  @Autowired
  public MachineService(MachineRepository machineRepository) {
    this.machineRepository = machineRepository;
  }

  public List<Machine> getAllMachines() {
    return machineRepository.findAll();
  }

  public Machine getMachineById(int id) {
    return machineRepository.findById(id); // 詳細情報を取得する
  }

  public void createMachine(Machine machine) {
    machineRepository.insertMachine(machine);
  }


}
