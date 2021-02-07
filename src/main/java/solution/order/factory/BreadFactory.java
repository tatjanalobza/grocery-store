package solution.order.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import solution.order.entity.Bread;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static solution.order.entity.Bread.Type.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BreadFactory {
  private static final BigDecimal PRICE_PER_UNIT = ONE;
  private static final BigDecimal PRICE_PER_PACK = ONE;

  public static Bread get(int ageInDays) {
    if (ageInDays >= 0 && ageInDays <= 2) {
      return new Bread(FRESH, "Sourdough", PRICE_PER_UNIT, PRICE_PER_PACK, 1);
    } else if (ageInDays > 2 && ageInDays <= 5) {
      return new Bread(STALE, "Soda Bread", PRICE_PER_UNIT, PRICE_PER_PACK, 2);
    } else if (ageInDays == 6) {
      return new Bread(OLD, "Tiger Loaf", PRICE_PER_UNIT, PRICE_PER_PACK, 3);
    } else if (ageInDays > 6) {
      return new Bread(VERY_OLD, "Brown Bread", ZERO, ZERO, 0);
    }
    throw new RuntimeException("Bread age is invalid: " + ageInDays);
  }
}
