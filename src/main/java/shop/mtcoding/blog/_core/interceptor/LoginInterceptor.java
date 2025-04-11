package shop.mtcoding.blog._core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog._core.erorr.ex.Exception401;
import shop.mtcoding.blog._core.erorr.ex.ExceptionApi401;
import shop.mtcoding.blog.user.User;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("uri: " + uri);

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            if (uri.contains("/api")) {
                throw new ExceptionApi401("인증이 필요합니다");
            } else {
                throw new Exception401("인증이 필요합니다");
            }
        }

        return true;
    }
}
