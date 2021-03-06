package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.create.StoreroomCreateCommand;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.create.StoreroomCreator;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.NewStoreroomRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/storerooms")
public class StoreroomCreateController {

  private static final Logger LOGGER = LoggerFactory.getLogger(StoreroomCreateController.class.getName());

  private final StoreroomCreator storeroomCreator;

  public StoreroomCreateController(final StoreroomCreator service) {
    this.storeroomCreator = service;
  }

  @PostMapping
  public ResponseEntity<Void> execute(@RequestBody NewStoreroomRequest request) {
    LOGGER.debug("Create Storeroom Request with data {}", request);
    storeroomCreator.execute(StoreroomCreateCommand.builder()
            .withId(request.getId())
            .withName(request.getName())
            .build()
    );

    return ResponseEntity.created(URI.create("/storerooms/" + request.getId())).build();
  }
}
