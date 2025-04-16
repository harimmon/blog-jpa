package shop.mtcoding.blog._core.erorr;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.mtcoding.blog._core.erorr.ex.Exception400;

import java.util.List;

// Aspect, PointCut, Advice
@Aspect // 관점 관리
@Component
public class GlobalValidationHandler {

    // 관심사를 분리시킴
    // 포인트컷 : PostMapping 혹은 PutMapping이 붙어있는 메소드를 실행하기 직전에 Advice를 호출한다.
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void badRequestAdvice(JoinPoint jp) { // jp는 실행될 실제 메소드의 모든 것을 투영하고 있음.
        Object[] args = jp.getArgs(); // 메소드의 매개 변수들
        for (Object arg : args) { // 매개 변수 개수만큼 반복 (어노테이션은 제외! 매개변수에 포함 안 됨)
            if (arg instanceof Errors) {
                System.out.println("에러 400 처리 필요함 !");
                Errors errors = (Errors) arg;

                // Errors 타입 매개변수에 존재하고 !
                if (errors.hasErrors()) {
                    List<FieldError> fErrors = errors.getFieldErrors();

                    // 에러가 존재한다면 !
                    for (FieldError fieldError : fErrors) {
                        throw new Exception400(fieldError.getField() + ":" + fieldError.getDefaultMessage()); // 필드명으로, 순서 보장 안 함
                    }
                }
            }
        }

    }
}
