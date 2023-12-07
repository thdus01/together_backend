package together.study.Service;


import org.springframework.stereotype.Component;
import together.study.model.User;
import together.study.model.vms1365APIVO;

import java.io.IOException;
import java.util.List;


@Component
public interface UserService {
    boolean register(User user);

    List<vms1365APIVO> vms1365API(int pageNum);
    List<vms1365APIVO> getVms1365API();
    void deleteVms1365API();
    void logout();
    boolean update(User user);
    boolean updateUserDef(int userId, String userDef);
    boolean delete(int userId);
    String selectByUserNickname(String userNickname);
    String selectByUserPhonenumber(String userPhonenumber);
    List<User> selectAllUser();

    User login(String userEmail);

}
