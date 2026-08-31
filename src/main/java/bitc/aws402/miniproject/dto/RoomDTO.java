package bitc.aws402.miniproject.dto;

import lombok.Data;

@Data
public class RoomDTO {
  private int roomIdx;
  private String roomName;
  private String roomSize;
  private int roomBasicPerson;
  private int roomMaxPerson;
  private int roomPriceWeekdays;
  private int roomPriceWeekend;
  private int roomPriceShoulderWeekdays;
  private int roomPriceShoulderWeekend;
  private int roomPricePeakWeekdays;
  private int roomPricePeakWeekend;
  private String roomService;
}
