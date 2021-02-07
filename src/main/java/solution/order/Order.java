package solution.order;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Collections.emptyList;

@Data
@Builder
public class Order {

  @Builder.Default
  private List<OrderItem> orderItems = emptyList();
  @Builder.Default
  private BigDecimal total = ZERO;

  public void adjustTotal(BigDecimal subtotal) {
    if (subtotal != null && subtotal.compareTo(BigDecimal.ZERO) > 0) {
      this.total = this.total.add(subtotal);
    }
  }

}
