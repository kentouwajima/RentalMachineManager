package RentalMachineManager.controller;

import RentalMachineManager.model.Location;
import RentalMachineManager.model.Machine;
import RentalMachineManager.model.MachineStatus;
import RentalMachineManager.service.LocationService;
import RentalMachineManager.service.MachineService;
import RentalMachineManager.service.MachineStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MachineController {
  private final MachineService machineService;
  private final LocationService locationService;
  private final MachineStatusService machineStatusService;

  @Autowired
  public MachineController(MachineService machineService,LocationService locationService, MachineStatusService machineStatusService) {
    this.machineService = machineService;
    this.locationService = locationService;
    this.machineStatusService = machineStatusService;
  }

  @GetMapping("/")
    public String showTopPage(Model model) {
      // 必要に応じてモデルに属性を追加できます
      return "index"; // トップページのテンプレート名
    }

    @GetMapping("/machines")
    public String getAllMachines(Model model) {
      List<Machine> machines = machineService.getAllMachines();
      model.addAttribute("machines", machines);
      return "machine-list";
    }

    @GetMapping("/machines/{id}")
    public String getMachineDetails(@PathVariable("id") int id, Model model) {
      Machine machine = machineService.getMachineById(id);
      model.addAttribute("machine", machine);
      return "machine-details";
    }

    @GetMapping("/machines/edit/{id}")
    public String editMachine(@PathVariable("id") int id, Model model) {
      Machine machine = machineService.getMachineById(id);
      model.addAttribute("machine", machine);
      return "machine-edit";
    }

    @GetMapping("/machines/new")
    public String showNewMachineForm(Model model) {
      model.addAttribute("statuses", machineStatusService.getAllStatuses());
      model.addAttribute("locations", locationService.getAllLocations());
      return "machine-new"; // Thymeleafテンプレートの名前
    }

    @PostMapping("/machines")
    public String createMachine(
        @RequestParam("statusId") int statusId,
        @RequestParam("locationId") int locationId,
        Machine machine
    ) {
      // ステータスと営業所のオブジェクトを作成
      MachineStatus status = new MachineStatus();
      status.setId(statusId);
      machine.setStatus(status);

      Location location = new Location();
      location.setId(locationId);
      machine.setLocation(location);

      // データベースに保存
      machineService.createMachine(machine);
      return "redirect:/machines";
    }

}
