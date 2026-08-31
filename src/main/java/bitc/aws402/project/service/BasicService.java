package bitc.aws402.project.service;

import bitc.aws402.project.dto.MemberDTO;
import bitc.aws402.project.dto.ResourceDTO;
import bitc.aws402.project.dto.RoomDTO;
import bitc.aws402.project.mapper.BasicMapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BasicService {
  private final BasicMapper basicMapper;

  public int getMemberLogin(String memberId, String memberPwd) {
    String result = basicMapper.getMemberLogin(memberId, memberPwd);
    if(result == null) {
      return 0;
    }
    return Integer.parseInt(result);
  }

  public MemberDTO getMemberInfo(int memberIdx) {
    MemberDTO member = basicMapper.getMemberInfo(memberIdx);
    return member;
  }

  public List<MemberDTO> getMemberList(int pageNum) {
    PageHelper.startPage(pageNum,10);
    List<MemberDTO> memberList = basicMapper.getMemberList();
    return memberList;
  }

  public int editMember(MemberDTO member) {
    int result = basicMapper.editMember(member);
    return result;
  }

  public int addMember(MemberDTO member) {
    int result = basicMapper.addMember(member);
    return result;
  }

  public List<RoomDTO> getRoomList() {
    List<RoomDTO> roomList = basicMapper.getRoomList();
    return roomList;
  }

  public RoomDTO getRoomInfo(int roomIdx){
    RoomDTO room = basicMapper.getRoomInfo(roomIdx);
    return room;
  }

  public int editRoom(RoomDTO room) {
    int result = basicMapper.editRoom(room);
    return result;
  }

  public List<ResourceDTO> getResourceList(int roomIdx) {
    List<ResourceDTO> resourceList = basicMapper.getResourceList(roomIdx);
    return resourceList;
  }

  public int deleteResource(String resourceIds){
    int result = basicMapper.deleteResource(resourceIds);
    return result;
  }
}
