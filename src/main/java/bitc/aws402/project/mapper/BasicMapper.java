package bitc.aws402.project.mapper;

import bitc.aws402.project.dto.MemberDTO;
import bitc.aws402.project.dto.ResourceDTO;
import bitc.aws402.project.dto.RoomDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BasicMapper {
  String getMemberLogin(@Param("memberId")  String memberId, @Param("memberPwd") String memberPwd);
  MemberDTO getMemberInfo(@Param("memberIdx") int memberIdx);
  List<MemberDTO> getMemberList();
  int editMember(MemberDTO member);
  int addMember(MemberDTO member);
  List<RoomDTO> getRoomList();
  RoomDTO getRoomInfo(@Param("roomIdx") int roomIdx);
  int editRoom(RoomDTO room);
  List<ResourceDTO> getResourceList(@Param("roomIdx") int roomIdx);
  int deleteResource(@Param("resourceIds") String resourceIds);
}
