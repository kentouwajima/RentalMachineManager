package RentalMachineManager.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Machine {
  private int id;
  @NotNull
  private String name;
  @NotNull
  private String manufacturer;
  @NotNull
  private String model;
  @NotNull
  private MachineStatus status;
  @NotNull
  private Location location;

}
