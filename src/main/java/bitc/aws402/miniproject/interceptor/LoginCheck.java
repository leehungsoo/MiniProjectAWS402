package bitc.aws402.miniproject.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheck implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession(false);
    System.out.println("\n========== Interceptor 동작 ==========\n");

    if (session == null || session.getAttribute("memberId") == null) {
      System.out.println("비 로그인 상태");
      response.sendRedirect("/admin");
      return false;
    }
    else {
      System.out.println("로그인 상태");
      if(!session.getAttribute("memberLevel").toString().equals("99")){
        response.sendRedirect("/admin");
        System.out.println("관리자가 아님");
        return false;
      }
      System.out.println("관리자 확인");
      session.setMaxInactiveInterval(60 * 60 *1);
      return true;
    }
  }
}
