package shop.mtcoding.blog._core.erorr.ex;

public class ExceptionApi400 extends RuntimeException {
    public ExceptionApi400(String message) {
        // ajax 요청
        super(message);
    }
}
