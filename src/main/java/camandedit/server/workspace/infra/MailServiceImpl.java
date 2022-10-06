package camandedit.server.workspace.infra;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;
import camandedit.server.workspace.application.MailService;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final JavaMailSender javaMailSender;

  @Override
  public void sendMail(String email, Long workSpaceId) {
    String redirectUrl = "http://localhost:3000/?email=" + email + "&workSpace=" + workSpaceId;
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      helper.setSubject("CamAndEdit 초대 메일입니다.");
      helper.setTo(email);
      helper.setText(
          "<h2>CamAndEdit 초대 메일입니다.</h2>" +
              "<p> 해당 링크로 이동해주세요 </p>" +
              "<a href=" + redirectUrl + ">LINK</a>", true);
      javaMailSender.send(mimeMessage);
    } catch (Exception e) {
      throw new BusinessException("메일 전송 실패", ErrorType.INTERNAL_SERVER_ERROR);
    }
  }
}
