package camandedit.server.chat.application.dto;

import camandedit.server.chat.domain.WorkSpaceChat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class WorkSpaceChatResponse {

    private Long chatId;
    private Long senderId;
    private String senderNickname;
    private String senderImage;
    private String message;
    private LocalDateTime createAt;

    public static WorkSpaceChatResponse of(WorkSpaceChat chat) {
        return WorkSpaceChatResponse.builder()
            .chatId(chat.getId())
            .senderId(chat.getMember().getUser().getId())
            .senderNickname(chat.getMember().getNickname())
            .senderImage(chat.getMember().getUser().getUserImage())
            .message(chat.getMessage())
            .createAt(chat.getCreatedAt())
            .build();
    }
}
