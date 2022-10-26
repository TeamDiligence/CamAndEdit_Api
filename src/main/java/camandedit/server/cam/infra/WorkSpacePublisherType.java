package camandedit.server.cam.infra;

public enum WorkSpacePublisherType {
  CHAT_INFO(Values.CHAT), ROOM_INFO(Values.ROOM);

  private String type;

  WorkSpacePublisherType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public static class Values {

    public static final String CHAT = "CHAT";
    public static final String ROOM = "ROOM";
  }
}
