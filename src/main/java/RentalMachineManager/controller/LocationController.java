package RentalMachineManager.controller;

import RentalMachineManager.model.Location;
import RentalMachineManager.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LocationController {

  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  // 営業所一覧の表示
  @GetMapping("/locations")
  public String showLocationList(Model model) {
    model.addAttribute("locations", locationService.getAllLocations());
    return "location-list";
  }

  // 営業所登録フォームの表示
  @GetMapping("/locations/register")
  public String showRegisterForm() {
    return "location-register";
  }

  // 営業所登録の処理
  @PostMapping("/locations/register")
  public String registerLocation(@ModelAttribute Location location) {
    locationService.saveLocation(location);
    return "redirect:/locations";
  }

  // 営業所編集フォームの表示
  @GetMapping("/locations/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    model.addAttribute("location", locationService.getLocationById(id));
    return "location-edit";
  }

  // 営業所編集の処理
  @PostMapping("/locations/edit")
  public String editLocation(@ModelAttribute Location location) {
    locationService.updateLocation(location);
    return "redirect:/locations";
  }
}
