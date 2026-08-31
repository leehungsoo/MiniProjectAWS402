package bitc.aws402.project.controller;

import bitc.aws402.project.dto.MemberDTO;
import bitc.aws402.project.dto.ResourceDTO;
import bitc.aws402.project.dto.RoomDTO;
import bitc.aws402.project.service.BasicService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpSession;
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

  @PostMapping("/login")
  public String adminLogin(@RequestParam("memberId") String memberId, @RequestParam("memberPwd") String memberPwd,  HttpSession session,  Model model) {
    int result = basicService.getMemberLogin(memberId, memberPwd);
    if (result > 0) {
      MemberDTO member = basicService.getMemberInfo(result);
      if(member.getMemberLevel()!=99){
        return alert("관리자만 접근 가능 합니다.", "/", model);
      }else{
        session.setAttribute("memberIdx", member.getMemberIdx());
        session.setAttribute("memberId", member.getMemberId());
        session.setAttribute("memberName", member.getMemberName());
        session.setAttribute("memberPhone", member.getMemberPhone());
        session.setAttribute("memberEmail", member.getMemberEmail());
        session.setAttribute("memberLevel", member.getMemberLevel());
        session.setAttribute("memberGender", member.getMemberGender());
        session.setAttribute("memberStatus", member.getMemberStatus());
        session.setMaxInactiveInterval(60*60*1);
      }
    }
    return "redirect:/admin";
  }

  @GetMapping("/logout")
  public String adminLogout(HttpSession session) {
    session.removeAttribute("memberIdx");
    session.removeAttribute("memberId");
    session.removeAttribute("memberName");
    session.removeAttribute("memberPhone");
    session.removeAttribute("memberEmail");
    session.removeAttribute("memberLevel");
    session.removeAttribute("memberGender");
    session.removeAttribute("memberStatus");
    session.invalidate();
    return "redirect:/admin";
  }

  @GetMapping("member")
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

  @DeleteMapping("/resource")
  public String adminResourceDelete(@RequestParam("deleteList") List<String> deleteList, @RequestParam("roomIdx") int roomIdx){
    if(!deleteList.isEmpty()) {
      basicService.deleteResource(String.join(",", deleteList));
    }
    return "redirect:/admin/room/"+roomIdx;
  }

  private String alert(String msg, String url, Model model){
    model.addAttribute("msg", msg);
    model.addAttribute("url", url);
    return "common/alert";
  }
}
