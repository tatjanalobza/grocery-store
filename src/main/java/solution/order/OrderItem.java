package solution.order;

import lombok.Builder;
import lombok.Data;
import solution.order.entity.GroceryItem;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItem {

  private GroceryItem item;
  private int requestQuantity;
  private BigDecimal subtotal;
  private int finalQuantity;

}
