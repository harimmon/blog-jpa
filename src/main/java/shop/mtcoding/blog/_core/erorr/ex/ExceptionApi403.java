package shop.mtcoding.blog._core.erorr.ex;

public class ExceptionApi403 extends RuntimeException {
    public ExceptionApi403(String message) {
        // ajax 요청
        super(message);
    }
}
