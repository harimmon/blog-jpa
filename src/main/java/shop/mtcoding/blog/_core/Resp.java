package shop.mtcoding.blog._core;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    // new 될 때 타입이 결정난다.
    public static <B> Resp<?> ok(B body) {
        return new Resp<B>(200, "성공", body);
    }

    public static Resp<?> fail(Integer status, String msg) {
        return new Resp<>(status, msg, null);
    }
}