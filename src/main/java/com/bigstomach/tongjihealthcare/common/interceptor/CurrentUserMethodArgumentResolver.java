package com.bigstomach.tongjihealthcare.common.interceptor;


import com.bigstomach.tongjihealthcare.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(String.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String userId = (String) webRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
        if (userId != null) {
            return userId;
        }
        throw new MissingServletRequestPartException("currentUser");
    }
}