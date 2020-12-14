package com.bigstomach.tongjihealthcare.common.interceptor;
import com.bigstomach.tongjihealthcare.annotation.AdminNeed;
import com.bigstomach.tongjihealthcare.annotation.JwtIgnore;
import com.bigstomach.tongjihealthcare.common.exception.Asserts;
import com.bigstomach.tongjihealthcare.common.response.ResultCode;
import com.bigstomach.tongjihealthcare.model.Audience;
import com.bigstomach.tongjihealthcare.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    private Audience audience;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }
        String requestPath = request.getRequestURI();
        if (requestPath.contains("/v2/api-docs") || requestPath.contains("/swagger") || requestPath.contains("/configuration/ui")) {
            return true;
        }
        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("## authHeader= {}", authHeader);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("### 用户未登录，请先登录 ###");
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        // 获取token
        final String token = authHeader.substring(7);
        if(audience == null){
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }
        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        JwtTokenUtil.parseJWT(token, audience.getBase64Secret());
        // 假设含有注解AdminNeed,只有admin才能访问接口
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AdminNeed adminNeed = handlerMethod.getMethodAnnotation(AdminNeed.class);
            if (adminNeed != null && !JwtTokenUtil.getUserRole(token, audience.getBase64Secret()).equals("admin")) {
                Asserts.fail(ResultCode.FORBIDDEN);
            }
        }
        request.setAttribute("currentUser", JwtTokenUtil.getUserId(token,audience.getBase64Secret()));
        return true;
    }
}