package solution.command;

import org.junit.jupiter.api.Test;
import solution.order.Order;
import solution.order.OrderItem;
import solution.order.entity.Beer;
import solution.order.entity.Bread;
import solution.order.entity.GroceryItem;

import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static solution.order.entity.Beer.Type.*;
import static solution.order.entity.Bread.Type.FRESH;

public class BeerDiscountTest {

  private static final GroceryItem HEINEKEN = new Beer(DUTCH, "Heineken", valueOf(0.8), valueOf(2), 6);
  private static final GroceryItem AFFLIGEM = new Beer(BELGIAN, "Affligem", valueOf(0.9), valueOf(3), 6);
  private static final GroceryItem GROLSCH = new Beer(GERMAN, "Grolsch", valueOf(0.5), valueOf(1), 6);

  private static final GroceryItem SOURDOUGH = new Bread(FRESH, "Sourdough", ONE, ONE, 1);

  private BeerDiscount command = new BeerDiscount();

  @Test
  void testApplyDiscount() {
    Order order = Order.builder()
        .orderItems(List.of(
            OrderItem.builder().item(SOURDOUGH).requestQuantity(3).build(),
            OrderItem.builder().item(HEINEKEN).requestQuantity(3).build(),
            OrderItem.builder().item(HEINEKEN).requestQuantity(6).build(),
            OrderItem.builder().item(HEINEKEN).requestQuantity(8).build(),
            OrderItem.builder().item(AFFLIGEM).requestQuantity(4).build(),
            OrderItem.builder().item(AFFLIGEM).requestQuantity(6).build(),
            OrderItem.builder().item(AFFLIGEM).requestQuantity(8).build(),
            OrderItem.builder().item(GROLSCH).requestQuantity(1).build(),
            OrderItem.builder().item(GROLSCH).requestQuantity(6).build(),
            OrderItem.builder().item(GROLSCH).requestQuantity(9).build()
        ))
        .build();

    command.applyDiscount(order);

    assertThat(order.getOrderItems()).hasSize(10)
        .extracting("item", "requestQuantity", "subtotal", "finalQuantity")
        .containsExactly(
            tuple(SOURDOUGH, 3, null, 0),
            tuple(HEINEKEN, 3, valueOf(2.4), 3),
            tuple(HEINEKEN, 6, valueOf(2.0), 6),
            tuple(HEINEKEN, 8, valueOf(3.6), 8),
            tuple(AFFLIGEM, 4, valueOf(3.6), 4),
            tuple(AFFLIGEM, 6, valueOf(3.0), 6),
            tuple(AFFLIGEM, 8, valueOf(4.8), 8),
            tuple(GROLSCH, 1, valueOf(0.5), 1),
            tuple(GROLSCH, 6, valueOf(1.0), 6),
            tuple(GROLSCH, 9, valueOf(2.5), 9)
        );
    assertThat(order.getTotal()).isEqualTo(valueOf(23.4));
  }

  @Test
  void testApplyDiscount_WithEmptyOrderList() {
    Order order = Order.builder().orderItems(List.of()).build();

    command.applyDiscount(order);

    assertThat(order.getOrderItems()).isEmpty();
    assertThat(order.getTotal()).isEqualTo(valueOf(0));
  }

  @Test
  void testApplyDiscount_WithNullOrderList() {
    Order order = Order.builder().orderItems(null).build();

    command.applyDiscount(order);

    assertThat(order.getOrderItems()).isNull();
    assertThat(order.getTotal()).isEqualTo(valueOf(0));
  }

}


