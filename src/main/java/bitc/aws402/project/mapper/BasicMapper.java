package bitc.aws402.project.mapper;

import bitc.aws402.project.dto.MemberDTO;
import bitc.aws402.project.dto.ResourceDTO;
import bitc.aws402.project.dto.RoomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface BasicMapper {
  public MemberDTO getMemberInfo(@Param("memberIdx") int memberIdx);
  public List<MemberDTO> getMemberList();
  public int editMember(MemberDTO member);
  public int addMember(MemberDTO member);
  public List<RoomDTO> getRoomList();
  public RoomDTO getRoomInfo(@Param("roomIdx") int roomIdx);
  public int editRoom(RoomDTO room);
  public List<ResourceDTO> getResourceList(@Param("roomIdx") int roomIdx);
}
