package camandedit.server.document.controller;

import camandedit.server.document.application.CreateDocumentUseCase;
import camandedit.server.document.application.GetDocumentUseCase;
import camandedit.server.document.application.dto.DocumentResponse;
import camandedit.server.document.controller.dto.CreateDocumentRequest;
import camandedit.server.global.config.web.JwtAuthUser;
import camandedit.server.global.config.web.LoginUser;
import camandedit.server.global.response.JsonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocumentController {

  private final CreateDocumentUseCase createDocumentUseCase;
  private final GetDocumentUseCase getDocumentUseCase;

  @GetMapping("/api/workspace/{id}/documents")
  public ResponseEntity<?> getDocumentList(@PathVariable("id") Long workspaceId) {
    List<DocumentResponse> result = getDocumentUseCase.findListByWorkspace(workspaceId);
    return JsonResponse.okWithData(HttpStatus.OK, "워크스페이스 문서 조회 성공", result);
  }

  @GetMapping("/api/document/{id}")
  public ResponseEntity<?> getDocument(@PathVariable("id") Long documentId) {
    DocumentResponse result = getDocumentUseCase.findOne(documentId);
    return JsonResponse.okWithData(HttpStatus.OK, "문서 조회 성공", result);
  }

  @PostMapping("/api/document")
  public ResponseEntity<?> createDocument(@Validated @RequestBody CreateDocumentRequest requestDto,
      @LoginUser JwtAuthUser user
  ) {
    createDocumentUseCase.create(requestDto.toCommand(user.getUserId()));
    return JsonResponse.ok(HttpStatus.OK, "문서 생성 성공");
  }
}
