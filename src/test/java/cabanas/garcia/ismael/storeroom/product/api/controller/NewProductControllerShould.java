package cabanas.garcia.ismael.storeroom.product.api.controller;


import cabanas.garcia.ismael.storeroom.product.api.request.NewProductRequest;
import cabanas.garcia.ismael.storeroom.product.api.response.ProductCreatedResponse;
import cabanas.garcia.ismael.storeroom.product.application.NewProductApplicationService;
import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(NewProductController.class)
public class NewProductControllerShould {

  private static final String SOME_PRODUCT_NAME = "some product name";
  private static final String SOME_UUID = "some-uuid";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private NewProductApplicationService newProductApplicationService;

  private JacksonTester<NewProductRequest> jsonResult;
  private JacksonTester<ProductCreatedResponse> jsonResponse;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
  }

  @Test
  public void postNewProductWithAllInformation() throws Exception {
    NewProductRequest newProductRequest = NewProductRequest.newProductRequest()
            .withName(SOME_PRODUCT_NAME)
            .build();

    // given
    given(newProductApplicationService.execute(ProductName.productName()
            .withName(SOME_PRODUCT_NAME)
            .build()))
            .willReturn(Product.product()
                    .withName(ProductName.productName().withName(SOME_PRODUCT_NAME).build())
                    .withId(ProductId.productId().withId(SOME_UUID).build())
                    .build());

    // when
    MockHttpServletResponse response = mvc.perform(
            post("/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonResult.write(newProductRequest).getJson()))
            .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.getHeaderValue(HttpHeaders.LOCATION)).isEqualTo("/products/" + SOME_UUID);
    assertThat(response.getContentAsString()).isEqualTo(
            jsonResponse.write(ProductCreatedResponse.productCreatedResponse()
                    .withName(ProductName.productName().withName(SOME_PRODUCT_NAME).build())
                    .build()).getJson());
  }
}