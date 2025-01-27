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

  // 全ての機材情報を取得
  public List<Machine> getAllMachines() {
    return machineRepository.findAll(); // 機材一覧を取得する
  }

  public List<Machine> searchMachines(String name, String manufacturer, String modelParam, Integer status, Integer location) {
    return machineRepository.searchMachines(name, manufacturer, modelParam, status, location);
  }

  // 機材IDを指定して詳細情報を取得
  public Machine getMachineById(int id) {
    return machineRepository.findById(id); // 詳細情報を取得する
  }

  // 新規機材の登録
  public void createMachine(Machine machine) {
    machineRepository.insertMachine(machine); // 新規機材を追加する
  }

  // 機材情報の更新
  public void updateMachine(Machine machine) {
    machineRepository.updateMachine(machine); // Repositoryでの更新処理を呼び出す
  }

  // 機材の削除
  public void deleteMachine(int id) {
    machineRepository.deleteById(id); // 機材を削除する
  }

}
