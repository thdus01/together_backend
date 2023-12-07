package together.study.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import together.study.API1365;
import together.study.mapper.UserMapper;
import together.study.model.User;
import together.study.model.vms1365APIVO;
import together.study.vmsAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional

@Service("UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;


    @SneakyThrows
    @Override
    public boolean register(User user) {
        try {
            
            userMapper.register(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public User login(String userEmail) {
        log.info("{}의 login 호출 : {}", this.getClass().getName(), userEmail);

        return userMapper.login(userEmail);
    }

    @Override
    public List<vms1365APIVO> vms1365API(int pageNum)  {
        try {
            List list = new ArrayList();
            vmsAPI vmsAPI = new vmsAPI();
            API1365 api1365 = new API1365();
//            for(int i = 0; i < vmsAPI.readVMSApi().size(); i++){
//                list.add(userMapper.vms1365API(vmsAPI.readVMSApi().get(i)));
//                }
            for(int i = 0; i < api1365.read1365Api(pageNum).size(); i++){
                list.add(api1365.read1365Api(pageNum).get(i));
            }
            return list;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<vms1365APIVO> getVms1365API() {
        return userMapper.getVms1365API();
    }

    @Override
    public void deleteVms1365API() {
        userMapper.deleteVms1365API();
    }

    @Override
    public void logout() {
        log.info("== logout ==");
    }

    @Override
    public boolean update(User user) {
        log.info("{}의 update 호출 : user = {}", this.getClass().getName(), user);
        try {

            userMapper.update(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUserDef(int userId, String userDef) {
        try {

            userMapper.updateUserDef(userId, userDef);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int userId) {
        try {

            userMapper.delete(userId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public String selectByUserNickname(String userNickname) {
        return userMapper.selectByUserNickname(userNickname);
    }
    @Override
    public String selectByUserPhonenumber(String userPhonenumber) {
        return userMapper.selectByUserPhonenumber(userPhonenumber);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

}
