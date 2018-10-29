package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.create.StoreroomCreateCommand;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.create.StoreroomCreator;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.NewStoreroomRequest;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response.StoreroomCreatedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/storerooms")
public class StoreroomCreateController {
  private final StoreroomCreator storeroomCreator;

  public StoreroomCreateController(final StoreroomCreator service) {
    this.storeroomCreator = service;
  }

  @PostMapping
  public ResponseEntity<StoreroomCreatedResponse> execute(@RequestBody NewStoreroomRequest request) {
    storeroomCreator.execute(StoreroomCreateCommand.builder()
            .withId(request.getId())
            .withName(request.getName())
            .build()
    );

    return ResponseEntity.created(URI.create("/storerooms/" + request.getId()))
            .body(StoreroomCreatedResponse.builder()
                    .withName(request.getName())
                    .build()
            );
  }
}
