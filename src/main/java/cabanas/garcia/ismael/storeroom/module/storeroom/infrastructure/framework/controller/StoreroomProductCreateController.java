package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.createProduct.ProductCreateCommand;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.createProduct.ProductCreator;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.NewProductRequest;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response.ProductCreatedResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class StoreroomProductCreateController {

  private final ProductCreator productCreator;

  public StoreroomProductCreateController(@Qualifier("storeroomProductCreator") ProductCreator productCreator) {
    this.productCreator = productCreator;
  }

  @PostMapping(path = "/storerooms/{storeroomId}/products")
  public ResponseEntity<ProductCreatedResponse> execute(
          @PathVariable("storeroomId") String storeroomId,
          @RequestBody NewProductRequest request) {
    productCreator.execute(ProductCreateCommand.builder()
            .withId(request.getId())
            .withName(request.getName())
            .withStoreroomId(storeroomId)
            .build()
    );

    return ResponseEntity.created(URI.create("/storerooms/" + storeroomId + "/products/" + request.getId()))
            .body(ProductCreatedResponse.builder()
                    .withName(request.getName())
                    .build()
            );
  }
}
