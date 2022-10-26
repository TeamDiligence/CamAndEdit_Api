package camandedit.server.global.config.socket;

import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.user.domain.service.TokenProvider;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthSocketHandler implements ChannelInterceptor {

  private final TokenProvider tokenProvider;
  private final UserRepository userRepository;

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,
        StompHeaderAccessor.class);
    StompCommand command = accessor.getCommand();
    if (command != null && command == StompCommand.CONNECT) {
      String token = accessor.getFirstNativeHeader("Authorization");
      Authentication authentication = tokenProvider.getAuthentication(token);
      Long userId = Long.valueOf(authentication.getPrincipal().toString());

      User findUser = userRepository.findById(userId);

      String sessionId = accessor.getSessionId();
      accessor.setUser(SocketUser.builder()
          .userId(findUser.getId())
          .sessionId(sessionId)
          .build());
    }
    return message;
  }

  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    ChannelInterceptor.super.postSend(message, channel, sent);
  }
}
