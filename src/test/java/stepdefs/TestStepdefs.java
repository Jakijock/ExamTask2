package stepdefs;

import configuration.Configuration;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import models.Registr;
import models.Successful;
import models.UserDTO;
import specifications.Specifications;

import java.util.List;

import static io.restassured.RestAssured.given;
import static specifications.Specifications.requestSpecification;

public class TestStepdefs extends Configuration {

    @Когда("передаем тело запроса")
    public void checkRequestBody(List<String> arg) {
        Specifications.installSpec(requestSpecification(getConfigurationFile("regresInUrl")), Specifications.responseSpecification200(201));
        Registr user = new Registr(arg.get(0), arg.get(1));
        Successful successful = given()
                .filter(new AllureRestAssured())
                .body(user)
                .when()
                .post("api/users")
                .then().log().all()
                .extract().as(Successful.class);
        Assert.assertEquals(arg.get(0), successful.getName());
        Assert.assertEquals(arg.get(1), successful.getJob());
    }


    @Тогда("проверяем, что ответ имеет валидные значения")
    public void checkingValidResponseValues() {
        Specifications.installSpec(requestSpecification(getConfigurationFile("regresInUrl")), Specifications.responseSpecification200(201));
        RestAssured.given()
                .filter(new AllureRestAssured())
                .spec(requestSpecification(getConfigurationFile("regresInUrl")))
                .body(new UserDTO(getConfigurationFile("name"), getConfigurationFile("job")))
                .post("/api/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("name", Matchers.is("Tomato"))
                .body("job", Matchers.is("Eat market"));
    }

    @Когда("открыт сайт")
    public void openURL() {
        Specifications.installSpec(requestSpecification(getConfigurationFile("regresInUrl")), Specifications.responseSpecification200(200));
    }

    @Тогда("появляется статус код {int}")
    public void checkStatusCod(int statusCode) {
        RestAssured.given()
                .filter(new AllureRestAssured())
                .get("/api/users/2")
                .then()
                .statusCode(statusCode);
    }
}


