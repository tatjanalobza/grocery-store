package solution;

import solution.command.BeerDiscount;
import solution.command.BreadDiscount;
import solution.command.DiscountCommand;
import solution.order.Order;
import solution.order.OrderItem;
import solution.order.factory.BeerFactory;
import solution.order.factory.BreadFactory;

import java.util.List;

import static solution.order.entity.Beer.Type.*;

public class Store {

  private static final List<DiscountCommand> commandList = List.of(new BeerDiscount(), new BreadDiscount());

  public static void main(String[] args) {
    Order order = Order.builder()
        .orderItems(List.of(
            OrderItem.builder().item(BeerFactory.get(DUTCH)).requestQuantity(3).build(),
            OrderItem.builder().item(BeerFactory.get(BELGIAN)).requestQuantity(6).build(),
            OrderItem.builder().item(BeerFactory.get(GERMAN)).requestQuantity(8).build(),
            OrderItem.builder().item(BreadFactory.get(2)).requestQuantity(3).build(),
            OrderItem.builder().item(BreadFactory.get(4)).requestQuantity(4).build(),
            OrderItem.builder().item(BreadFactory.get(4)).requestQuantity(3).build(),
            OrderItem.builder().item(BreadFactory.get(6)).requestQuantity(5).build(),
            OrderItem.builder().item(BreadFactory.get(8)).requestQuantity(1).build()
        ))
        .build();

    new Store().finaliseOrder(order);

    order.getOrderItems().forEach(item ->
        System.out.println("Item: " + item.getItem().getName() + " Type: " + item.getItem().getType() + " Final Quantity: " + item.getFinalQuantity() + " Subtotal: " + item.getSubtotal())
    );
    System.out.println("-------------------------------------------");
    System.out.println("TOTAL: " + order.getTotal() );
  }

  public void finaliseOrder(Order order) {
    commandList.forEach(command -> command.applyDiscount(order));
  }

}
