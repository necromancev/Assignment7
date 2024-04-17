import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import pojos.ProductPojo;
import utils.Specifications;

import static io.restassured.RestAssured.given;

public class WaresApiTest {

    @Test
    void getAllReturns200(){
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080"), Specifications.responseSpecification(200));
            given().
            basePath("/api/food").
            when().
            get().
            then().
            assertThat().
            body(Matchers.not(Matchers.empty()));
    }

    @Test
    void addingProductReturns200() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductPojo svekla = new ProductPojo("свекла", "VEGETABLE", false);
        String sveklaJsonString = objectMapper.writeValueAsString(svekla);
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080"), Specifications.responseSpecification(200));
            given().
            basePath("/api/food").
            when().
            contentType(ContentType.JSON).
            body(sveklaJsonString).
            post();
    }
}
