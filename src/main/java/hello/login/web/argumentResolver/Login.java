package hello.login.web.argumentResolver;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 파라미터에만 적용
@Target(ElementType.PARAMETER)
// 리플랙션 등을 활용할 수 있도록 런타임 까지 어노테이션 정보가 남아있음
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
}
