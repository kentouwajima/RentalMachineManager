package RentalMachineManager.controller;

import RentalMachineManager.model.Location;
import RentalMachineManager.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/locations")
public class LocationController {

  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  public String showLocationList(Model model) {
    model.addAttribute("locations", locationService.getAllLocations());
    return "location-list";
  }

  @GetMapping("/register")
  public String showRegisterForm() {
    return "location-register";
  }

  @PostMapping("/register")
  public String registerLocation(@ModelAttribute Location location) {
    locationService.saveLocation(location);
    return "redirect:/locations";
  }

  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    model.addAttribute("location", locationService.getLocationById(id));
    return "location-edit";
  }

  @PostMapping("/edit")
  public String editLocation(@ModelAttribute Location location) {
    locationService.updateLocation(location);
    return "redirect:/locations";
  }

}
