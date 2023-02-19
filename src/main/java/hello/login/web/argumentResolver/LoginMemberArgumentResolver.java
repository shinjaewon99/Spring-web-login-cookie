package hello.login.web.argumentResolver;

import hello.login.domain.member.Member;
import hello.login.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        log.info("supportsParameter 실행");

        // 파라미터에 어노테이션이 있는지 검증
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);

        // @Login (커스텀 어노테이션)을 사용하는 파라미터가 입력
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());


        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");

        // HttpServletRequest를  반환
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        // true로 하게되면 의미없는 세션이 생성됨
        HttpSession session = request.getSession(false);

        if(session == null){
            return null;
        }
        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
