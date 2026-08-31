package bitc.aws402.miniproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BasicController {
  @GetMapping({"", "/"})
  public String index() {
    return "index";
  }
}
