package RentalMachineManager.controller;

import RentalMachineManager.model.Machine;
import RentalMachineManager.service.MachineService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MachineController {
  private final MachineService machineService;

  public MachineController(MachineService machineService) {
    this.machineService = machineService;
  }

  @GetMapping("/machines")
  public String getAllMachines(Model model) {
    List<Machine> machines = machineService.getAllMachines();
    model.addAttribute("machines", machines);
    return "machine-list"; // Thymeleafテンプレート名
  }

}
