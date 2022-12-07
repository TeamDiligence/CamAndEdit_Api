package camandedit.server.cam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequestDto {

  private Object candidate;
  private Long receiverId;

}
