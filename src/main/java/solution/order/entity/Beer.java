package solution.order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

import static solution.order.entity.GroceryItem.Type.BEER;

@Data
@EqualsAndHashCode(callSuper = true)
public class Beer extends GroceryItem {

  private Type beerType;

  public Beer(Type beerType, String name, BigDecimal pricePerUnit, BigDecimal pricePerPack, int packSize) {
    super(name, pricePerUnit, pricePerPack, packSize, BEER);
    this.beerType = beerType;
  }

  public enum Type {
    DUTCH, GERMAN, BELGIAN
  }

}
