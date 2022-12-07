package camandedit.server.cam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponseDto {

  private Long roomId;
  private Long senderId;
  private Object sdp;
}
