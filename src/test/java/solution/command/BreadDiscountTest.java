package solution.command;

import org.junit.jupiter.api.Test;
import solution.order.Order;
import solution.order.OrderItem;
import solution.order.entity.Beer;
import solution.order.entity.Bread;
import solution.order.entity.GroceryItem;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static solution.order.entity.Beer.Type.DUTCH;
import static solution.order.entity.Bread.Type.*;

class BreadDiscountTest {

  private static final GroceryItem SOURDOUGH = new Bread(FRESH, "Sourdough", ONE, ONE, 1);
  private static final GroceryItem SODA_BREAD = new Bread(STALE, "Soda Bread", ONE, ONE, 2);
  private static final GroceryItem TIGER_LOAF = new Bread(OLD, "Tiger Loaf", ONE, ONE, 3);
  private static final GroceryItem BROWN_BREAD = new Bread(VERY_OLD, "Brown Bread", ZERO, ZERO, 0);

  private static final GroceryItem HEINEKEN = new Beer(DUTCH, "Heineken", valueOf(0.8), valueOf(2), 6);

  private BreadDiscount command = new BreadDiscount();

  @Test
  void testApplyDiscount() {
    Order order = Order.builder()
        .orderItems(List.of(
            OrderItem.builder().item(SOURDOUGH).requestQuantity(3).build(),
            OrderItem.builder().item(SODA_BREAD).requestQuantity(3).build(),
            OrderItem.builder().item(SODA_BREAD).requestQuantity(2).build(),
            OrderItem.builder().item(SODA_BREAD).requestQuantity(1).build(),
            OrderItem.builder().item(TIGER_LOAF).requestQuantity(4).build(),
            OrderItem.builder().item(TIGER_LOAF).requestQuantity(3).build(),
            OrderItem.builder().item(HEINEKEN).requestQuantity(6).build(),
            OrderItem.builder().item(TIGER_LOAF).requestQuantity(2).build(),
            OrderItem.builder().item(BROWN_BREAD).requestQuantity(1).build()
        ))
        .build();

    command.applyDiscount(order);

    assertThat(order.getOrderItems()).hasSize(9)
        .extracting("item", "requestQuantity", "subtotal", "finalQuantity")
        .containsExactly(
            tuple(SOURDOUGH, 3, valueOf(3), 3),
            tuple(SODA_BREAD, 3, valueOf(2), 3),
            tuple(SODA_BREAD, 2, valueOf(1), 2),
            tuple(SODA_BREAD, 1, valueOf(1), 1),
            tuple(TIGER_LOAF, 4, valueOf(2), 4),
            tuple(TIGER_LOAF, 3, valueOf(1), 3),
            tuple(HEINEKEN, 6, null, 0),
            tuple(TIGER_LOAF, 2, valueOf(2), 2),
            tuple(BROWN_BREAD, 1, valueOf(0), 0)
        );
    assertThat(order.getTotal()).isEqualTo(valueOf(12));
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
