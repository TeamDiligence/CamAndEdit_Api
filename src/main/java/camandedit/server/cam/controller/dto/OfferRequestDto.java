package camandedit.server.cam.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequestDto {

  private Object sdp;
  private Long receiveId;
}
