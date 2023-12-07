package together.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import together.study.model.User;
import together.study.model.vms1365APIVO;

import java.util.List;

@Mapper
public interface UserMapper {
    User login(String userEmail);
    boolean register(User user);
    String selectByUserNickname(String userNickname);
    String selectByUserPhonenumber(String userPhonenumber);
    boolean update(User user);
    boolean updateUserDef(int userId, String userDef);
    boolean delete(int userId);
    boolean vms1365API(vms1365APIVO vms1365APIVO);
    void deleteVms1365API();
    List<User> selectAllUser();

    List<vms1365APIVO> getVms1365API();
}
