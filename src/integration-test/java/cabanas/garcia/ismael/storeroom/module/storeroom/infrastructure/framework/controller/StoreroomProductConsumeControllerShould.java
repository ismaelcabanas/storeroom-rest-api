package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.consume.ConsumeProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.consume.ConsumeProductCommand;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductNotInStoreroomException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomNotFoundException;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.ConsumeProductRequestBody;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response.ErrorDetailsResponseBody;
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
@WebMvcTest(StoreroomProductConsumeController.class)
@ActiveProfiles("integration-test")
public class StoreroomProductConsumeControllerShould {
  private static final String SOME_STOREROOM_ID = "some_storeroom_id";
  private static final String SOME_PRODUCT_ID = "some_product_id";
  private static final Integer SOME_QUANTITY = 5;

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ConsumeProduct consumeProduct;

  private JacksonTester<ConsumeProductRequestBody> jsonRequest;
  private JacksonTester<ErrorDetailsResponseBody> jsonErrorResponse;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
  }

  @Test
  public void post_consume_product_in_storeroom() throws Exception {
    // given
    ConsumeProductRequestBody consumeProductRequestBody = ConsumeProductRequestBody.builder()
            .withQuantity(SOME_QUANTITY)
            .build();

    // when
    MockHttpServletResponse response = mvc.perform(
            post("/storerooms/" + SOME_STOREROOM_ID + "/products/" + SOME_PRODUCT_ID + "/consume")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest.write(consumeProductRequestBody).getJson()))
            .andReturn().getResponse();

    // then
    verify(consumeProduct).execute(ConsumeProductCommand.builder()
            .withStoreroomId(SOME_STOREROOM_ID)
            .withProductId(SOME_PRODUCT_ID)
            .withQuantity(SOME_QUANTITY)
            .build());
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  public void post_consume_product_in_storeroom_return_404_status_code_when_product_not_in_storeroom() throws Exception {
    // given
    ConsumeProductRequestBody consumeProductRequestBody = ConsumeProductRequestBody.builder()
            .withQuantity(SOME_QUANTITY)
            .build();
    willThrow(new ProductNotInStoreroomException(new ProductId(SOME_PRODUCT_ID)))
            .given(consumeProduct)
            .execute(Mockito.any(ConsumeProductCommand.class));

    // when
    MockHttpServletResponse response = mvc.perform(
            post("/storerooms/" + SOME_STOREROOM_ID + "/products/" + SOME_PRODUCT_ID + "/consume")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest.write(consumeProductRequestBody).getJson()))
            .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    ErrorDetailsResponseBody errorDetailsResponseBody = jsonErrorResponse.parseObject(response.getContentAsString());
    assertThat(errorDetailsResponseBody.getMessage())
            .isEqualTo(String.format("Product with identifier %s does not exist in the storeroom", SOME_PRODUCT_ID));
  }

  @Test
  public void post_consume_product_in_storeroom_return_404_status_code_when_does_not_exist_storeroom()
          throws Exception {
    // given
    ConsumeProductRequestBody consumeProductRequestBody = ConsumeProductRequestBody.builder()
            .withQuantity(SOME_QUANTITY)
            .build();
    willThrow(new StoreroomNotFoundException(new StoreroomId(SOME_STOREROOM_ID)))
            .given(consumeProduct)
            .execute(Mockito.any(ConsumeProductCommand.class));

    // when
    MockHttpServletResponse response = mvc.perform(
            post("/storerooms/" + SOME_STOREROOM_ID + "/products/" + SOME_PRODUCT_ID + "/consume")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest.write(consumeProductRequestBody).getJson()))
            .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    ErrorDetailsResponseBody errorDetailsResponseBody = jsonErrorResponse.parseObject(response.getContentAsString());
    assertThat(errorDetailsResponseBody.getMessage())
            .isEqualTo(String.format("Storeroom %s not founded", SOME_STOREROOM_ID));
  }
}
