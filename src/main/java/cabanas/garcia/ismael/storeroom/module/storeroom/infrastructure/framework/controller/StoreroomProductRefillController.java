package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.refill.RefillProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.refill.RefillProductCommand;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductNotInStoreroomException;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.RefillProductRequestBody;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response.ErrorDetailsResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreroomProductRefillController {

  private final RefillProduct refillProduct;

  public StoreroomProductRefillController(RefillProduct refillProduct) {
    this.refillProduct = refillProduct;
  }

  @PostMapping(path = "/storerooms/{storeroomId}/products/{productId}/refill")
  public ResponseEntity<Void> execute(
          @PathVariable("storeroomId") String storeroomId,
          @PathVariable("productId") String productId,
          @RequestBody RefillProductRequestBody request) {

    refillProduct.execute(RefillProductCommand.builder()
            .withStoreroomId(storeroomId)
            .withProductId(productId)
            .withQuantity(request.getQuantity())
            .build());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ExceptionHandler(ProductNotInStoreroomException.class)
  public final ResponseEntity<ErrorDetailsResponseBody> handleProductNotInStoreroomException(ProductNotInStoreroomException ex) {
    ErrorDetailsResponseBody errorDetails = new ErrorDetailsResponseBody("", ex.getMessage());
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}
