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

public class UserUpdateTest extends RestAssuredTest {
    @Test
    void requestToken(){
        User request = requestUpdateUser();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("together/update")
                .then()
                .log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private static User requestUpdateUser() {
        final String user_email = "test@gmail.com";
        final String user_name = "정다연1";
        final String user_nickname = "test1";
        final String userPhonenumber = "";
        final int user_gender = 4;
        final Date user_birthdate = new Date();
        final String user_profile_image = "test1";
        final String user_def = "test1";
        final String user_type = "test1";

        return new User(user_email, user_name, userPhonenumber, user_nickname,  user_gender, user_birthdate, user_profile_image, user_def, user_type);
    }
}

