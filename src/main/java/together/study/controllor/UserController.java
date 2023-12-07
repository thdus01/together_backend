package together.study.controllor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import together.study.API1365;
import together.study.model.User;
import together.study.Service.UserService;
import together.study.model.vms1365APIVO;
import together.study.selectAPI1365;
import together.study.sendMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/together", produces = "application/json; charset=UTF-8")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> request) {
        String userEmail = request.get("userEmail");
        log.info("{}의 로그인 호출 : {}", this.getClass().getName(), userEmail);

        if (userService.login(userEmail) != null) {
            return userService.login(userEmail);
        } else {
            return null;
        }
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user)  {
        log.info("{}의 회원가입 호출 : {}", this.getClass().getName(), user);
        if(userService.register(user)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody User user){
        if(userService.update(user)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/updateUserDef")
    public ResponseEntity updateUserDef(int userId, String userDef){
        if(userService.updateUserDef(userId, userDef)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/delete")
    public ResponseEntity delete(int userId){
        if(userService.delete(userId)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/selectByUserNickname")
    public ResponseEntity selectByUserNickname(String userNickname){
            if((userService.selectByUserNickname(userNickname)==null)){
                return ResponseEntity.status(HttpStatus.OK).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

    }
    @PostMapping("/selectAllUser")
    public List<User> selectAllUser(){
        log.info("{}의 selectAllUser 호출 : ", this.getClass().getName());
        return userService.selectAllUser();
    }
//    @Scheduled(cron = "0 5 * * * *", zone = "Asia/Seoul")
    @PostMapping("/readVMS1365Api")
    public List<vms1365APIVO> readVMS1365Api(int pageNum) throws IOException {
        //userService.deleteVms1365API();
        //System.out.println("== DB - API ==");
        API1365 api1365 = new API1365();
        return api1365.read1365Api(pageNum);

    }

    @PostMapping("/read1365selectApi")
    public List<vms1365APIVO> read1365selectApi(int pageNum, String keyword) throws IOException {
        selectAPI1365 apiSelect1365 = new selectAPI1365();
        return apiSelect1365.read1365selectApi(pageNum, keyword);

    }
    @PostMapping("/getVMS1365Api")
    public List<vms1365APIVO> getVMS1365Api() throws IOException {
        return userService.getVms1365API();
    }
    @PostMapping("/sendMessage")
    public int sendMessage(String userPhonenumber){
        sendMessage message = new sendMessage();
        int randNum = message.random();
        if(userService.selectByUserPhonenumber(userPhonenumber)==null){
            message.sendOne(userPhonenumber, randNum);
            return randNum;
        }else{
            return 1;
        }

    }
    @PostMapping("/isCurrectNum")
    public ResponseEntity isCurrectNum(int sendNum, int getNum){
        if(sendNum == getNum){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
