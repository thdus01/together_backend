package together.study.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String userEmail;
    private String userName;
    private String userPhonenumber;
    private String userNickname;
    private int userGender;

    private Date userBirthdate;
    private String userDef;
    private String userType;


    public User(String userEmail, String userName, String userPhonenumber, String userNickname, int userGender, Date userBirthdate, String userProfileImage, String userDef, String userType) {
    }

    public User(String userEmail) {
        this.userEmail = userEmail;
    }
}
