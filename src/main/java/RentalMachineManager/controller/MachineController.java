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
  public MachineController(MachineService machineService, LocationService locationService, MachineStatusService machineStatusService) {
    this.machineService = machineService;
    this.locationService = locationService;
    this.machineStatusService = machineStatusService;
  }

  // トップページの表示
  @GetMapping("/")
  public String showTopPage(Model model) {
    return "index";
  }

  // 全ての機材一覧を取得して表示
  @GetMapping("/machines")
  public String getAllMachines(Model model) {
    List<MachineStatus> statuses = machineStatusService.getAllStatuses();  // 全ステータスを取得
    List<Location> locations = locationService.getAllLocations();  // 全営業所を取得
    model.addAttribute("statuses", statuses);  // ステータスをビューに渡す
    model.addAttribute("locations", locations);  // 営業所をビューに渡す
    List<Machine> machines = machineService.getAllMachines();  // 全機材を取得
    model.addAttribute("machines", machines);  // 機材一覧をビューに渡す
    return "machine-list";
  }

  // 検索結果の表示
  @GetMapping("/machines/search")
  public String searchMachines(
      @RequestParam(required = false) String name,  // 機材名で検索
      @RequestParam(required = false) String manufacturer,  // メーカー名で検索
      @RequestParam(required = false) String modelParam,  // 型式で検索
      @RequestParam(required = false) Integer status,  // ステータスIDで検索
      @RequestParam(required = false) Integer location,  // 営業所IDで検索
      Model model
  ) {
    List<MachineStatus> statuses = machineStatusService.getAllStatuses();  // 全ステータスを取得
    List<Location> locations = locationService.getAllLocations();  // 全営業所を取得
    model.addAttribute("statuses", statuses);  // ステータスをビューに渡す
    model.addAttribute("locations", locations);  // 営業所をビューに渡す

    // 検索条件に一致する機材を取得
    List<Machine> machines = machineService.searchMachines(name, manufacturer, modelParam, status, location);
    model.addAttribute("machines", machines);  // 検索結果をビューに渡す

    // 検索条件をフォームに保持
    model.addAttribute("name", name);
    model.addAttribute("manufacturer", manufacturer);
    model.addAttribute("modelParam", modelParam);
    model.addAttribute("statusParam", status);
    model.addAttribute("locationParam", location);

    return "machine-list";  // 検索結果を表示するビューに遷移
  }

  // 機材詳細の表示
  @GetMapping("/machines/{id}")
  public String getMachineDetails(@PathVariable("id") int id, Model model) {
    Machine machine = machineService.getMachineById(id);  // IDに対応する機材を取得
    model.addAttribute("machine", machine);  // 機材情報をビューに渡す
    return "machine-details";
  }

  // 新規機材登録フォームの表示
  @GetMapping("/machines/new")
  public String showNewMachineForm(Model model) {
    model.addAttribute("statuses", machineStatusService.getAllStatuses());  // ステータス一覧をビューに渡す
    model.addAttribute("locations", locationService.getAllLocations());  // 営業所一覧をビューに渡す
    return "machine-new";
  }

  // 新規機材の登録処理
  @PostMapping("/machines")
  public String createMachine(
      @RequestParam("statusId") int statusId,  // ステータスIDを取得
      @RequestParam("locationId") int locationId,  // 営業所IDを取得
      Machine machine  // 機材の情報を取得
  ) {
    MachineStatus status = new MachineStatus();
    status.setId(statusId);  // ステータスを設定
    machine.setStatus(status);

    Location location = new Location();
    location.setId(locationId);  // 営業所を設定
    machine.setLocation(location);

    machineService.createMachine(machine);  // 新規機材を登録
    return "redirect:/machines";  // 一覧ページにリダイレクト
  }

  // 機材編集ページの表示
  @GetMapping("/machines/edit/{id}")
  public String editMachine(@PathVariable("id") int id, Model model) {
    Machine machine = machineService.getMachineById(id);  // 編集対象の機材を取得
    model.addAttribute("machine", machine);  // 機材情報をビューに渡す
    model.addAttribute("statuses", machineStatusService.getAllStatuses());  // ステータス一覧をビューに渡す
    model.addAttribute("locations", locationService.getAllLocations());  // 営業所一覧をビューに渡す
    return "machine-edit";
  }

  // 機材情報の更新処理
  @PostMapping("/machines/edit/{id}")
  public String updateMachine(
      @PathVariable("id") int id,  // 更新対象の機材ID
      @RequestParam("statusId") int statusId,  // 新しいステータスID
      @RequestParam("locationId") int locationId,  // 新しい営業所ID
      Machine machine  // 更新後の機材情報
  ) {
    MachineStatus status = new MachineStatus();
    status.setId(statusId);  // ステータスを設定
    machine.setStatus(status);

    Location location = new Location();
    location.setId(locationId);  // 営業所を設定
    machine.setLocation(location);

    machine.setId(id);  // 機材IDを設定
    machineService.updateMachine(machine);  // 機材情報を更新
    return "redirect:/machines";  // 一覧ページにリダイレクト
  }

  // 機材削除処理
  @PostMapping("/machines/delete/{id}")
  public String deleteMachine(@PathVariable("id") int id) {
    machineService.deleteMachine(id);  // 機材を削除
    return "redirect:/machines";  // 一覧ページにリダイレクト
  }

}
