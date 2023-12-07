package together.study.User;


import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import together.study.RestAssuredTest;
import together.study.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTest extends RestAssuredTest {
    @Test
    void requestToken(){
        User request = requestLoginUser();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("together/login")
                .then()
                .log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private static User requestLoginUser() {
        final String user_email = "test@gmail.com";

        return new User(user_email);
    }
}

