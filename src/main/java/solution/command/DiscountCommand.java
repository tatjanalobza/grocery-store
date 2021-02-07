package solution.command;

import solution.order.Order;
import solution.order.entity.GroceryItem;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

public interface DiscountCommand {

  void applyDiscount(Order order);

  default BigDecimal calculateSubTotal(GroceryItem item, int quantity) {
    if (item.getPackSize() == 0) return ZERO;

    return item.getPricePerPack()
        .multiply(valueOf(quantity / item.getPackSize()))
        .add(item.getPricePerUnit().multiply(valueOf(quantity % item.getPackSize())));
  }

}
