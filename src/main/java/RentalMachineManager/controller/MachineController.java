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
      return "index";
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
    model.addAttribute("statuses", machineStatusService.getAllStatuses());
    model.addAttribute("locations", locationService.getAllLocations());
    return "machine-edit";
  }

  @PostMapping("/machines/edit/{id}")
  public String updateMachine(
      @PathVariable("id") int id,
      @RequestParam("statusId") int statusId,
      @RequestParam("locationId") int locationId,
      Machine machine
  ) {
    MachineStatus status = new MachineStatus();
    status.setId(statusId);
    machine.setStatus(status);

    Location location = new Location();
    location.setId(locationId);
    machine.setLocation(location);

    machine.setId(id);
    machineService.updateMachine(machine);
    return "redirect:/machines";
  }


  @GetMapping("/machines/new")
    public String showNewMachineForm(Model model) {
      model.addAttribute("statuses", machineStatusService.getAllStatuses());
      model.addAttribute("locations", locationService.getAllLocations());
      return "machine-new";
    }

    @PostMapping("/machines")
    public String createMachine(
        @RequestParam("statusId") int statusId,
        @RequestParam("locationId") int locationId,
        Machine machine
    ) {
      MachineStatus status = new MachineStatus();
      status.setId(statusId);
      machine.setStatus(status);

      Location location = new Location();
      location.setId(locationId);
      machine.setLocation(location);

      machineService.createMachine(machine);
      return "redirect:/machines";
    }

  @PostMapping("/machines/delete/{id}")
  public String deleteMachine(@PathVariable("id") int id) {
    machineService.deleteMachine(id);
    return "redirect:/machines";
  }

}
