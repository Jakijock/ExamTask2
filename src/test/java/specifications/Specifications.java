package specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification responseSpecification200(int code){
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }
    public static void installSpec(RequestSpecification request , ResponseSpecification responce){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = responce;
    }
}
