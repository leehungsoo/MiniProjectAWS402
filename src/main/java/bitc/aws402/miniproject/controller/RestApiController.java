package bitc.aws402.miniproject.controller;

import bitc.aws402.miniproject.dto.MemberDTO;
import bitc.aws402.miniproject.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class RestApiController {

  private final BasicService basicService;

  @GetMapping("/member/{memberIdx}")
  public MemberDTO getMember(@PathVariable("memberIdx") int memberIdx) {
    MemberDTO member = basicService.getMemberInfo(memberIdx);
    return member;
  }
}
