package solution.command;

import solution.order.Order;

import static solution.order.entity.GroceryItem.Type.BEER;

public class BeerDiscount implements DiscountCommand {

  public void applyDiscount(Order order) {
    if (order == null || order.getOrderItems() == null) return;

    order.getOrderItems().stream()
        .filter(item -> BEER == item.getItem().getType())
        .forEach(item -> {
          item.setSubtotal(calculateSubTotal(item.getItem(), item.getRequestQuantity()));
          item.setFinalQuantity(item.getRequestQuantity());
          order.adjustTotal(item.getSubtotal());
        });
  }
}
