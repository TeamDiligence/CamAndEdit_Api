package camandedit.server.cam.controller;

import camandedit.server.cam.infra.dto.WorkSpacePublisherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class WorkSpaceSubscriber implements MessageListener {

  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;
  private final SimpMessageSendingOperations messageTemplate;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String strMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

    try {
      WorkSpacePublisherDto dto = objectMapper.readValue(strMessage,
          WorkSpacePublisherDto.class);
      log.info(
          "[REDIS SUBSCRIBE] WORKSPACE_ID = " + dto.getWorkspaceId() + " 데이터 = " + dto.getValue()
              .toString());
      ;
      messageTemplate.convertAndSend("/topic/workspace/" + dto.getWorkspaceId(),
          dto);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
