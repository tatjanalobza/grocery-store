package solution.order.factory;

import org.junit.jupiter.api.Test;
import solution.order.entity.Bread;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BreadFactoryTest {
  private static final BigDecimal PRICE = ONE;

  @Test
  void testGet() {
    assertThat(BreadFactory.get(1))
        .extracting("breadType", "name", "pricePerUnit", "pricePerPack", "packSize")
        .containsExactly(Bread.Type.FRESH, "Sourdough", PRICE, PRICE, 1);

    assertThat(BreadFactory.get(4))
        .extracting("breadType", "name", "pricePerUnit", "pricePerPack", "packSize")
        .containsExactly(Bread.Type.STALE, "Soda Bread", PRICE, PRICE, 2);

    assertThat(BreadFactory.get(6))
        .extracting("breadType", "name", "pricePerUnit", "pricePerPack", "packSize")
        .containsExactly(Bread.Type.OLD, "Tiger Loaf", PRICE, PRICE, 3);

    assertThat(BreadFactory.get(9))
        .extracting("breadType", "name", "pricePerUnit", "pricePerPack", "packSize")
        .containsExactly(Bread.Type.VERY_OLD, "Brown Bread", ZERO, ZERO, 0);
  }

  @Test
  void testGet_NegativeAge() {
    Exception exception = assertThrows(
        RuntimeException.class,
        () -> BreadFactory.get(-4)
    );

    assertThat(exception.getMessage()).contains("Bread age is invalid: -4");
  }

}
