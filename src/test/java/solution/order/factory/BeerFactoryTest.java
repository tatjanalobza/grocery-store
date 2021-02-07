package solution.order.factory;

import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static solution.order.entity.Beer.Type.*;

class BeerFactoryTest {

  @Test
  void testGet() {
    assertThat(BeerFactory.get(DUTCH))
        .extracting("beerType", "name", "pricePerUnit", "pricePerPack", "packSize")
        .containsExactly(DUTCH, "Heineken", valueOf(0.8), valueOf(2), 6);

    assertThat(BeerFactory.get(BELGIAN))
        .extracting("beerType", "name", "pricePerUnit", "pricePerPack", "packSize")
        .containsExactly(BELGIAN, "Affligem", valueOf(0.9), valueOf(3), 6);

    assertThat(BeerFactory.get(GERMAN))
        .extracting("beerType", "name", "pricePerUnit", "pricePerPack", "packSize")
        .containsExactly(GERMAN, "Grolsch", valueOf(0.5), valueOf(1), 6);
  }

  @Test
  void testGet_UnrecognizedType() {
    Exception exception = assertThrows(
        RuntimeException.class,
        () -> BeerFactory.get(null)
    );

    assertThat(exception.getMessage()).contains("Input type must be non null");
  }

}
