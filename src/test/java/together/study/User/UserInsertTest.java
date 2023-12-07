package together.study.User;


import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import together.study.RestAssuredTest;
import together.study.model.User;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserInsertTest extends RestAssuredTest {
    @Test
    void requestToken(){
        User request = requestInserUser();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("together/register")
                .then()
                .log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static User requestInserUser() {
        final String user_email = "test@gmail.com";
        final String user_name = "정다연";
        final String userPhonenumber = "";
        final String user_nickname = "test123";
        final int user_gender = 4;
        final Date user_birthdate = new Date();
        final String user_def = "test1";
        final String user_type = "test1";

        return new User(user_email,user_name, userPhonenumber, user_nickname, user_gender, user_birthdate, user_def, user_type);
    }
}

