package camandedit.server.chat.application;

import camandedit.server.chat.application.command.CreateWorkSpaceChatCommand;
import camandedit.server.chat.application.dto.WorkSpaceChatResponse;
import camandedit.server.chat.domain.WorkSpaceChat;
import camandedit.server.chat.domain.repository.WorkSpaceChatRepository;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatUseCase {

    private final WorkSpaceChatRepository workSpaceChatRepository;
    private final UserRepository userRepository;
    private final WorkSpaceRepository workSpaceRepository;

    @Transactional
    public WorkSpaceChat createChat(CreateWorkSpaceChatCommand command) {
        WorkSpaceMember member = workSpaceRepository.findMember(command.getWorkSpaceId(),
            command.getUserId());
        WorkSpaceChat workSpaceChat = new WorkSpaceChat(command.getMessage(), member);
        workSpaceChatRepository.save(workSpaceChat);
        return workSpaceChat;
    }

    @Transactional(readOnly = true)
    public List<WorkSpaceChatResponse> findWorkSpaceChat(Long workSpaceId, Long lastId, int count) {
        List<WorkSpaceChat> chats = workSpaceChatRepository.findByWorkSpaceId(workSpaceId,
            lastId, count);
        return chats.stream().map(WorkSpaceChatResponse::of).collect(Collectors.toList());
    }
}
