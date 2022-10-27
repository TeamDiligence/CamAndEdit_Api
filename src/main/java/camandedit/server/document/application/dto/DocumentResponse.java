package camandedit.server.document.application.dto;

import camandedit.server.document.domain.Document;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentResponse {

  private Long documentId;
  private String documentTitle;
  private String description;
  private String contents;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static DocumentResponse from(Document document) {
    return DocumentResponse.builder()
        .documentId(document.getId())
        .documentTitle(document.getTitle())
        .description(document.getDescription())
        .contents(document.getContents())
        .createdAt(document.getCreatedAt())
        .updatedAt(document.getUpdatedAt())
        .build();
  }
}
