package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller.request.NewProductRequest;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller.response.ProductCreatedResponse;
import cabanas.garcia.ismael.storeroom.product.application.newproduct.NewProduct;
import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("/products")
public class NewProductController {

  private final NewProduct newProduct;

  public NewProductController(final NewProduct service) {
    this.newProduct = service;
  }

  @PostMapping
  public ResponseEntity<ProductCreatedResponse> execute(@RequestBody NewProductRequest request) {
    Product product =
            newProduct.execute(
                    ProductId.productId().withId(request.getId()).build(),
                    ProductName.productName().withName(request.getName()).build());
    return ResponseEntity.created(URI.create("/products/" + product.getId()))
            .body(ProductCreatedResponse.productCreatedResponse()
              .withId(product.getId())
              .withName(product.getName())
              .build()
            );
  }
}