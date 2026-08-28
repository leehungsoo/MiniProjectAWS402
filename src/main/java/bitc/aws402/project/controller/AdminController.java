package bitc.aws402.project.controller;

import bitc.aws402.project.dto.MemberDTO;
import bitc.aws402.project.dto.ResourceDTO;
import bitc.aws402.project.dto.RoomDTO;
import bitc.aws402.project.mapper.BasicMapper;
import bitc.aws402.project.service.BasicService;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

  private final BasicService basicService;

  @GetMapping({"", "/"})
  public String index() {
    return "admin/index";
  }

  @GetMapping("/member")
  public String adminMember(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model) {
    PageInfo<MemberDTO> pageMemberList = new PageInfo<>(basicService.getMemberList(pageNum), 5);
    model.addAttribute("pageMemberList", pageMemberList);
    return "admin/member";
  }

  @GetMapping("/member/{memberIdx}")
  public String adminMemberDetail(@PathVariable("memberIdx") int memberIdx, Model model) {
    MemberDTO member = basicService.getMemberInfo(memberIdx);
    model.addAttribute("member", member);
    return "admin/memberDetail";
  }

  @PostMapping("/member")
  public String adminMemberEdit(@ModelAttribute MemberDTO member, Model model) {
    int result = basicService.editMember(member);
    if(result > 0) {
      return alert("회원 정보가 수정되었습니다.", "/admin/member", model);
    }else{
      return alert("회원 정보 수정에 실패 했습니다.", "/admin/member/"+member.getMemberIdx(), model);
    }
  }

  @GetMapping("/addMember")
  public String adminAddMember() {
    return "admin/addMember";
  }

  @PostMapping("/addMember")
  public String adminAddMember(@ModelAttribute MemberDTO member, Model model) {
    int result = basicService.addMember(member);
    if(result > 0) {
      return alert("회원 추가가 성공 했습니다.", "/admin/member", model);
    }else{
      return alert("회원 추가에 실패 했습니다.", "/admin/addMember", model);
    }
  }

  @GetMapping("/room")
  public String adminRoom(Model model) {
    List<RoomDTO> roomList = basicService.getRoomList();
    model.addAttribute("roomList", roomList);
    return "admin/room";
  }

  @GetMapping("/room/{roomIdx}")
  public String adminRoomDetail(@PathVariable("roomIdx") int roomIdx, Model model) {
    RoomDTO room = basicService.getRoomInfo(roomIdx);
    model.addAttribute("room", room);
    List<ResourceDTO> resourceList = basicService.getResourceList(roomIdx);
    model.addAttribute("resourceList", resourceList);
    return "admin/roomDetail";
  }

  @PostMapping("/room")
  public String adminRoomEdit(@ModelAttribute RoomDTO room, Model model) {
    int result = basicService.editRoom(room);
    if(result > 0) {
      return alert("객실 정보가 수정되었습니다.", "/admin/room", model);
    }else{
      return alert("객실 정보 수정에 실패 했습니다.", "/admin/room/"+room.getRoomIdx(), model);
    }
  }

  @ResponseStatus
  @DeleteMapping("/resource")
  public String adminResourceDelete(@RequestParam("deleteList") List<String> deleteList){
    for(String deleteId : deleteList){
      System.out.println(deleteId);
    }
    return "redirect:/admin/room";
  }

  private String alert(String msg, String url, Model model){
    model.addAttribute("msg", msg);
    model.addAttribute("url", url);
    return "common/alert";
  }
}
