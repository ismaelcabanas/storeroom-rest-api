package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.addproduct.AddProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request.NewProductRequest;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response.ProductCreatedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(StoreroomProductCreateController.class)
@ActiveProfiles("integration-test")
public class StoreroomProductCreateControllerShould {
  private static final String SOME_PRODUCT_NAME = "some product name";
  private static final String SOME_STOREROOM_UUID = "some-storeroom-uuid";
  private static final String SOME_UUID = "some-uuid";
  private static final int ZERO = 0;

  @Autowired
  private MockMvc mvc;

  @MockBean
  @Qualifier("storeroomProductCreator")
  private AddProduct productCreator;

  private JacksonTester<NewProductRequest> jsonRequest;
  private JacksonTester<ProductCreatedResponse> jsonResponse;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
  }

  @Test
  public void post_new_product_to_storeroom_with_all_information_return_response_with_all_information() throws Exception {
    // given
    NewProductRequest newProductRequest = NewProductRequest.builder()
            .withId(SOME_UUID)
            .withName(SOME_PRODUCT_NAME)
            .build();
    Product productAdded = Product.builder()
            .withId(new ProductId(SOME_UUID))
            .withName(new ProductName(SOME_PRODUCT_NAME))
            .build();
    given(productCreator.execute(Mockito.any())).willReturn(productAdded);

    // when
    MockHttpServletResponse response = mvc.perform(
            post("/storerooms/" + SOME_STOREROOM_UUID + "/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest.write(newProductRequest).getJson()))
            .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getHeaderValue(HttpHeaders.LOCATION))
            .isEqualTo("/storerooms/" + SOME_STOREROOM_UUID + "/products/" + SOME_UUID);
    ProductCreatedResponse productCreatedResponse = jsonResponse.parseObject(response.getContentAsString());
    assertThat(productCreatedResponse.getName()).isEqualTo(SOME_PRODUCT_NAME);
  }

  @Test
  public void post_new_product_to_storeroom_with_all_information_return_response_with_product_stock_zero() throws Exception {
    // given
    NewProductRequest newProductRequest = NewProductRequest.builder()
            .withId(SOME_UUID)
            .withName(SOME_PRODUCT_NAME)
            .build();
    Product productAdded = Product.builder()
            .withId(new ProductId(SOME_UUID))
            .withName(new ProductName(SOME_PRODUCT_NAME))
            .build();
    given(productCreator.execute(Mockito.any())).willReturn(productAdded);

    // when
    MockHttpServletResponse response = mvc.perform(
            post("/storerooms/" + SOME_STOREROOM_UUID + "/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest.write(newProductRequest).getJson()))
            .andReturn().getResponse();

    // then
    ProductCreatedResponse productCreatedResponse = jsonResponse.parseObject(response.getContentAsString());
    assertThat(productCreatedResponse.getStock()).isEqualTo(ZERO);
  }
}
