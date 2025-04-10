package shop.mtcoding.blog._core.erorr;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.blog._core.erorr.ex.*;
import shop.mtcoding.blog._core.util.Resp;

@RestControllerAdvice // 이 어노테이션을 붙이면 모든 에러가 여기로 들어온다. 데이터를 리턴하는 애
public class GlobalExceptionHandler {

    // 400 : 잘못된 요청
    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e) {
        String html = """
                <script>
                alert('${msg}');
                history.back();
                </script>
                """.replace("${msg}", e.getMessage());
        return html;
    }

    @ExceptionHandler(ExceptionApi400.class)
    public Resp<?> exApi400(ExceptionApi400 e) {
        return Resp.fail(400, e.getMessage());
    }

    // 401 : 인증 안 됨
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e) {
        String html = """
                <script>
                alert('${msg}');
                location.href = "/login-form"
                </script>
                """.replace("${msg}", e.getMessage());
        return html;
    }

    @ExceptionHandler(ExceptionApi401.class)
    public Resp<?> exApi401(ExceptionApi401 e) {
        return Resp.fail(401, e.getMessage());
    }

    // 403 : 권한 없음
    @ExceptionHandler(Exception403.class)
    public String ex403(Exception403 e) {
        String html = """ 
                <script>
                alert('${msg}');
                </script>
                """.replace("${msg}", e.getMessage());
        return html;
    }

    @ExceptionHandler(ExceptionApi403.class)
    public Resp<?> exApi403(ExceptionApi403 e) {
        return Resp.fail(403, e.getMessage());
    }

    // 404 : 자원을 찾을 수 없음
    @ExceptionHandler(Exception404.class)
    public String ex404(Exception404 e) {
        String html = """
                <script>
                alert('${msg}');
                location.href = "/";
                </script>
                """.replace("${msg}", e.getMessage());
        return html;
    }

    @ExceptionHandler(ExceptionApi404.class)
    public Resp<?> exApi404(ExceptionApi404 e) {
        return Resp.fail(404, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public String exUnKnown(Exception e) {
        String html = """ 
                <script>
                alert('${msg}');
                history.back();
                </script>
                """.replace("${msg}", "관리자에게 문의해주세요.");
        System.out.println("관리자님 보세요 : " + e.getMessage());
        return html;
    }

}
