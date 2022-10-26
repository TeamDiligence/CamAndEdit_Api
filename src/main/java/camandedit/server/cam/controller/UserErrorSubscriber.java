package camandedit.server.cam.controller;

import camandedit.server.global.response.SocketErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserErrorSubscriber implements MessageListener {

  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;
  private final SimpMessageSendingOperations messageTemplate;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String strMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
    try {
      SocketErrorResponse socketErrorResponse = objectMapper.readValue(strMessage,
          SocketErrorResponse.class);
      log.info(socketErrorResponse.toString());
      messageTemplate.convertAndSendToUser(socketErrorResponse.getSessionId(), "/queue/errors",
          socketErrorResponse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
