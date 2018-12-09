package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.refill.RefillProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.refill.RefillProductCommand;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductNotInStoreroomException;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.RefillProductRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(StoreroomProductRefillController.class)
@ActiveProfiles("integration-test")
public class StoreroomProductRefillControllerShould {

  private static final String SOME_STOREROOM_ID = "some_storeroom_id";
  private static final String SOME_PRODUCT_ID = "some_product_id";
  private static final Integer SOME_QUANTITY = 5;

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RefillProduct refillProduct;

  private JacksonTester<RefillProductRequestBody> jsonRequest;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
  }

  @Test
  public void post_refill_product_in_storeroom() throws Exception {
    // given
    RefillProductRequestBody refillProductRequestBody = RefillProductRequestBody.builder()
            .withQuantity(SOME_QUANTITY)
            .build();

    // when
    MockHttpServletResponse response = mvc.perform(
            post("/storerooms/" + SOME_STOREROOM_ID + "/products/" + SOME_PRODUCT_ID + "/refill")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest.write(refillProductRequestBody).getJson()))
            .andReturn().getResponse();

    // then
    verify(refillProduct).execute(RefillProductCommand.builder()
            .withStoreroomId(SOME_STOREROOM_ID)
            .withProductId(SOME_PRODUCT_ID)
            .withQuantity(SOME_QUANTITY)
            .build());
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  public void post_refill_product_in_storeroom_return_404_status_code_when_product_not_in_storeroom() throws Exception {
    // given
    RefillProductRequestBody refillProductRequestBody = RefillProductRequestBody.builder()
            .withQuantity(SOME_QUANTITY)
            .build();
    willThrow(new ProductNotInStoreroomException(new ProductId(SOME_PRODUCT_ID)))
            .given(refillProduct)
            .execute(Mockito.any(RefillProductCommand.class));

    // when
    MockHttpServletResponse response = mvc.perform(
            post("/storerooms/" + SOME_STOREROOM_ID + "/products/" + SOME_PRODUCT_ID + "/refill")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest.write(refillProductRequestBody).getJson()))
            .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
  }
}
