package together.study;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.web.bind.annotation.PostMapping;

public class sendMessage {
    final DefaultMessageService messageService;

    public sendMessage() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCSWSNLMCSMCDD5Q", "VKAJQYTF7PFS6KZOMPVM7UD9LG8XBIKQ", "https://api.coolsms.co.kr");
    }
    public int random(){
        int randnum = (int)(Math.random() * 8999) + 1000;
        return randnum;
    }
    public SingleMessageSentResponse sendOne(String phonenum, int randnum) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01020872477");
        message.setTo(phonenum);
        message.setText("[Together] 인증번호는 " + randnum + "입니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));

        return response;
    }
}
