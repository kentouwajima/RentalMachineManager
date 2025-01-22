package RentalMachineManager.controller;

import RentalMachineManager.model.Machine;
import RentalMachineManager.service.LocationService;
import RentalMachineManager.service.MachineService;
import RentalMachineManager.service.MachineStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
