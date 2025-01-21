package RentalMachineManager.controller;

import RentalMachineManager.model.Machine;
import RentalMachineManager.service.MachineService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
