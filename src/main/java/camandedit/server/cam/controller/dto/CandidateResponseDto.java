package camandedit.server.cam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseDto {

  private Long senderId;
  private Long roomId;
  private Object candidate;

}
