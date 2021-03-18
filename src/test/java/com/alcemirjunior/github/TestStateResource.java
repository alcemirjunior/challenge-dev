package com.alcemirjunior.github;


import com.alcemirjunior.github.DTO.StateDTO;
import com.alcemirjunior.github.resources.DatabaseLifecycle;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(DatabaseLifecycle.class)
public class TestStateResource {

    @Test
    public void insertShouldReturnCreatedWhenValidData(){
        StateDTO dto = new StateDTO();
        dto.setName("MA");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/states")
                .then()
                .statusCode(201)
                .body(Matchers.anything());
    }

    @Test
    public void insertShouldReturnBadRequestWhenNotValidData(){
        StateDTO dto = new StateDTO();
        dto.setName("M");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/states")
                .then()
                .statusCode(400)
                .body(Matchers.anything());
    }


    @Test
    public void updateShouldReturnStateDTOWhenValidDataAndIdExists(){
        StateDTO dto = new StateDTO();
        dto.setName("MA");

        given()
                .pathParam("id", 2)
                .contentType(ContentType.JSON)
                .body(dto)
                .when().put("/states/{id}")
                .then()
                .statusCode(200)
                .body("name", is("MA"));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists(){
        StateDTO dto = new StateDTO();
        dto.setName("MA");

        given()
                .pathParam("id", 10)
                .contentType(ContentType.JSON)
                .body(dto)
                .when().put("/states/{id}")
                .then()
                .statusCode(404);
    }


    @Test
    public void deleteShouldReturnNoContentWhenIdExists(){

        given()
                .pathParam("id", 1)
                .when().delete("/states/{id}")
                .then()
                .statusCode(204)
                .body(Matchers.anything());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists(){

        given()
                .pathParam("id", 10)
                .when().delete("/states/{id}")
                .then()
                .statusCode(404)
                .body(Matchers.anything());
    }

}

