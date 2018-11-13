package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.addproduct.AddProductCommand;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.addproduct.AddProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.NewProductRequest;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response.ProductCreatedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class StoreroomProductCreateController {

  private static final Logger LOGGER = LoggerFactory.getLogger(StoreroomProductCreateController.class.getName());

  private final AddProduct productCreator;

  public StoreroomProductCreateController(@Qualifier("storeroomProductCreator") AddProduct productCreator) {
    this.productCreator = productCreator;
  }

  @PostMapping(path = "/storerooms/{storeroomId}/products")
  public ResponseEntity<ProductCreatedResponse> execute(
          @PathVariable("storeroomId") String storeroomId,
          @RequestBody NewProductRequest request) {
    LOGGER.debug("Request: {}", request);
    Product product = productCreator.execute(AddProductCommand.builder()
            .withId(request.getId())
            .withName(request.getName())
            .withStoreroomId(storeroomId)
            .build()
    );

    return ResponseEntity.created(URI.create("/storerooms/" + storeroomId + "/products/" + product.id().getValue()))
            .body(ProductCreatedResponse.builder()
                    .withName(product.name().getName())
                    .withStock(product.getStock().getValue())
                    .build()
            );
  }
}
