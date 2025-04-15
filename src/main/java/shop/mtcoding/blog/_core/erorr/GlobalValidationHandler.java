package shop.mtcoding.blog._core.erorr;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component // IoC에 띄움
@Aspect // 프록시로 간다
public class GlobalValidationHandler {

    @Before("@annotation(shop.mtcoding.blog._core.erorr.anno.MyBefore)") // 패키지 이름을 풀로 적어야 함, 매개 변수 정보까지 알 수 있다
    public void beforeAdvice(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("Before Advice : " + name);

    }

    @After("@annotation(shop.mtcoding.blog._core.erorr.anno.MyAfter)")
    public void afterAdvice(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("After Advice : " + name);
    }

    @Around("@annotation(shop.mtcoding.blog._core.erorr.anno.MyAround)")
    public Object aroundAdvice(ProceedingJoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("Around Advice 직전 : " + name);

        try {
            Object result = jp.proceed(); // 컨트롤러 함수가 호출됨
            System.out.println("Around Advice 직후 : " + name);
            System.out.println("result 값 : " + result);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
