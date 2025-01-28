package RentalMachineManager.service;

import RentalMachineManager.model.MachineStatus;
import RentalMachineManager.repository.MachineStatusRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MachineStatusService {

  // MachineStatusRepositoryのインスタンスを注入
  private final MachineStatusRepository machineStatusRepository;

  // コンストラクタでMachineStatusRepositoryを受け取り、フィールドにセット
  public MachineStatusService(MachineStatusRepository machineStatusRepository) {
    this.machineStatusRepository = machineStatusRepository; // インスタンスを初期化
  }

  // すべての機材ステータス情報を取得するメソッド
  public List<MachineStatus> getAllStatuses() {
    return machineStatusRepository.findAll(); // MachineStatusRepositoryから機材ステータスのデータを取得して返却
  }

}
