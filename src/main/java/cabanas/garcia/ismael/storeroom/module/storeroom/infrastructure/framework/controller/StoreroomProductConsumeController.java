package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.consume.ConsumeProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.consume.ConsumeProductCommand;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductNotInStoreroomException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomNotFoundException;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.ConsumeProductRequestBody;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response.ErrorDetailsResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreroomProductConsumeController {
  private final ConsumeProduct consumeProduct;

  public StoreroomProductConsumeController(ConsumeProduct consumeProduct) {
    this.consumeProduct = consumeProduct;
  }

  @PostMapping(path = "/storerooms/{storeroomId}/products/{productId}/consume")
  public ResponseEntity<Void> execute(
          @PathVariable("storeroomId") String storeroomId,
          @PathVariable("productId") String productId,
          @RequestBody ConsumeProductRequestBody requestBody) {

    consumeProduct.execute(ConsumeProductCommand.builder()
            .withStoreroomId(storeroomId)
            .withProductId(productId)
            .withQuantity(requestBody.getQuantity())
            .build());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ExceptionHandler(ProductNotInStoreroomException.class)
  public final ResponseEntity<ErrorDetailsResponseBody> handleProductNotInStoreroomException(
          ProductNotInStoreroomException ex) {
    ErrorDetailsResponseBody errorDetails = ErrorDetailsResponseBody.builder().withMessage(ex.getMessage()).build();
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(StoreroomNotFoundException.class)
  public final ResponseEntity<ErrorDetailsResponseBody> handleStoreroomNotFoundException(
          StoreroomNotFoundException ex) {
    ErrorDetailsResponseBody errorDetails = ErrorDetailsResponseBody.builder().withMessage(ex.getMessage()).build();
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}
