package RentalMachineManager.service;

import RentalMachineManager.model.MachineStatus;
import RentalMachineManager.repository.MachineStatusRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MachineStatusService {
  private final MachineStatusRepository machineStatusRepository;

  public MachineStatusService(MachineStatusRepository machineStatusRepository) {
    this.machineStatusRepository = machineStatusRepository;
  }

  public List<MachineStatus> getAllStatuses() {
    return machineStatusRepository.findAll();
  }

}
