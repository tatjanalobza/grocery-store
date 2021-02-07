package solution.order;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

  @Test
  public void testAdjustTotal() {
    Order order = new Order(List.of(), ONE);
    order.adjustTotal(valueOf(2));

    assertThat(order.getTotal()).isEqualTo(valueOf(3));
  }

  @Test
  public void testAdjustTotal_SubTotalIsNull() {
    Order order = new Order(List.of(), ONE);
    order.adjustTotal(null);

    assertThat(order.getTotal()).isEqualTo(ONE);
  }

  @Test
  public void testAdjustTotal_SubTotalIsNegative() {
    Order order = new Order(List.of(), ONE);
    order.adjustTotal(valueOf(-3));

    assertThat(order.getTotal()).isEqualTo(ONE);
  }
}
