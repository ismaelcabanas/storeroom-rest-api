package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.product.application.create.ProductCreateCommand;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller.request.NewProductRequest;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller.response.ProductCreatedResponse;
import cabanas.garcia.ismael.storeroom.product.application.create.ProductCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("/products")
public class ProductCreateController {

  private final ProductCreator productCreator;

  public ProductCreateController(final ProductCreator service) {
    this.productCreator = service;
  }

  @PostMapping
  public ResponseEntity<ProductCreatedResponse> execute(@RequestBody NewProductRequest request) {
    productCreator.execute(ProductCreateCommand.builder()
                    .withId(request.getId())
                    .withName(request.getName())
                    .build()
    );

    return ResponseEntity.created(URI.create("/products/" + request.getId()))
            .body(ProductCreatedResponse.productCreatedResponse()
              .withName(request.getName())
              .build()
            );
  }
}