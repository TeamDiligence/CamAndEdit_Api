package camandedit.server.chat.controller.dto;

import camandedit.server.chat.domain.WorkSpaceChat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class WorkSpaceChatSocketResponse {

    private Long chatId;
    private Long senderId;
    private String senderNickname;
    private String senderImage;
    private String message;
    private LocalDateTime createAt;

    public static WorkSpaceChatSocketResponse of(WorkSpaceChat chat) {
        return WorkSpaceChatSocketResponse.builder()
            .chatId(chat.getId())
            .senderId(chat.getMember().getUser().getId())
            .senderNickname(chat.getMember().getNickname())
            .senderImage(chat.getMember().getUser().getUserImage())
            .message(chat.getMessage())
            .createAt(chat.getCreatedAt())
            .build();
    }
}
