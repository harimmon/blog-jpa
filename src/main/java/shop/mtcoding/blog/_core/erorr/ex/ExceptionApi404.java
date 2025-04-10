package shop.mtcoding.blog._core.erorr.ex;

public class ExceptionApi404 extends RuntimeException {
    public ExceptionApi404(String message) {
        // ajax 요청
        super(message);
    }
}
