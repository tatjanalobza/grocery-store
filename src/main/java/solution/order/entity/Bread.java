package solution.order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class Bread extends GroceryItem {

  private Type breadType;

  public Bread(Bread.Type type, String name, BigDecimal pricePerUnit, BigDecimal pricePerPack, int packSize) {
    super(name, pricePerUnit, pricePerPack, packSize, GroceryItem.Type.BREAD);
    this.breadType = type;
  }

  public enum Type {
    FRESH, STALE, OLD, VERY_OLD
  }

}
