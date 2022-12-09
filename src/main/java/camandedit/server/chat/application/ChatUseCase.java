package camandedit.server.chat.application;

import camandedit.server.chat.application.command.CreateWorkSpaceChatCommand;
import camandedit.server.chat.domain.WorkSpaceChat;
import camandedit.server.chat.domain.repository.WorkSpaceChatRepository;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import lombok.RequiredArgsConstructor;
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
        User user = userRepository.findById(command.getUserId());
        WorkSpace workSpace = workSpaceRepository.findById(command.getWorkSpaceId());

        WorkSpaceChat workSpaceChat = new WorkSpaceChat(command.getMessage(), workSpace, user);
        workSpaceChatRepository.save(workSpaceChat);
        return workSpaceChat;
    }

}
