package solution.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public abstract class GroceryItem {

  private String name;
  private BigDecimal pricePerUnit;
  private BigDecimal pricePerPack;
  private int packSize;
  private Type type;

  public enum Type {
    BEER, BREAD
  }
}
