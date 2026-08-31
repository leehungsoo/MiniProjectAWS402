package bitc.aws402.miniproject.dto;

import lombok.Data;

@Data
public class MemberDTO {
  private int memberIdx;
  private String memberId;
  private String memberPwd;
  private String memberName;
  private String memberPhone;
  private String memberEmail;
  private String memberGender;
  private String memberJoinDate;
  private int memberLevel;
  private int memberStatus;
}
