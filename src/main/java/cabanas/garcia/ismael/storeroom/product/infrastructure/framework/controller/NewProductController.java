package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.request.NewProductRequest;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.response.ProductCreatedResponse;
import cabanas.garcia.ismael.storeroom.product.application.NewProductApplicationService;
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

  private final NewProductApplicationService newProductApplicationService;

  public NewProductController(final NewProductApplicationService service) {
    this.newProductApplicationService = service;
  }

  @PostMapping
  public ResponseEntity<ProductCreatedResponse> execute(@RequestBody NewProductRequest request) {
    Product product =
            newProductApplicationService.execute(ProductName.productName()
                    .withName(request.getName())
                    .build()
            );
    ProductId productId = product.getProductId();
    return ResponseEntity.created(URI.create("/products/" + productId.getId())).body(ProductCreatedResponse.productCreatedResponse()
            .withName(product.getProductName())
            .build());
  }
}
