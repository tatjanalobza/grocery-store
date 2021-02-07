package solution.order.factory;

import lombok.NoArgsConstructor;
import solution.order.entity.Beer;

import static java.math.BigDecimal.valueOf;
import static lombok.AccessLevel.PRIVATE;
import static solution.order.entity.Beer.Type.*;

@NoArgsConstructor(access = PRIVATE)
public class BeerFactory {

  public static Beer get(Beer.Type type){
    if (type == null) throw new RuntimeException("Input type must be non null");

    switch (type) {
      case DUTCH:
        return new Beer(DUTCH, "Heineken", valueOf(0.8), valueOf(2), 6);
      case BELGIAN:
        return new Beer(BELGIAN, "Affligem", valueOf(0.9), valueOf(3), 6);
      case GERMAN:
        return new Beer(GERMAN, "Grolsch", valueOf(0.5), valueOf(1), 6);
      default:
        throw new RuntimeException("Unknown beer type: " + type);
    }
  }

}
