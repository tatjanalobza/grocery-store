package solution.command;

import solution.order.entity.Bread;
import solution.order.entity.GroceryItem;
import solution.order.Order;

import static solution.order.entity.Bread.Type.VERY_OLD;
import static solution.order.entity.GroceryItem.Type.BREAD;

public class BreadDiscount implements DiscountCommand {

  public void applyDiscount(Order order) {
    if (order == null || order.getOrderItems() == null) return;

    order.getOrderItems().stream()
        .filter(item -> BREAD == item.getItem().getType())
        .forEach(item -> {
          GroceryItem bread = item.getItem();
          item.setSubtotal(calculateSubTotal(bread, item.getRequestQuantity()));
          item.setFinalQuantity(((Bread) bread).getBreadType() != VERY_OLD ? item.getRequestQuantity() : 0);
          order.adjustTotal(item.getSubtotal());
        });
  }
}
