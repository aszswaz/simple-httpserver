package cn.aszswaz.simplehttpserver.interceptor;

import cn.aszswaz.simplehttpserver.entity.Options;
import java.util.Collection;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static cn.aszswaz.simplehttpserver.config.Container.options;

/**
 * 该拦截器用于输出请求和响应信息
 *
 * @author aszswaz
 * @createTime 2022-04-02 16:35:02
 * @ide IntelliJ IDEA
 */
@SuppressWarnings("JavaDoc")
@Component
public class InformationInterceptor implements HandlerInterceptor, WebMvcConfigurer {

    private final Options options;

    public InformationInterceptor() {
        this.options = options();
    }

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**");
    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        // 打印请求信息
        // 打印起始行
        System.out.printf(">>> %s %s %s%n", request.getMethod(), request.getServletPath(), request.getProtocol());

        if (this.options.isVerbose()) {
            // 打印请求头
            Enumeration<String> headers = request.getHeaderNames();
            while (headers.hasMoreElements()) {
                String header = headers.nextElement();
                String value = request.getHeader(header);
                System.out.printf(">>> %s: %s%n", header, value);
            }
        }

        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) {
        System.out.println("<<< " + HttpStatus.resolve(response.getStatus()));

        if (this.options.isVerbose()) {
            Collection<String> headers = response.getHeaderNames();
            for (String header : headers) {
                System.out.printf("<<< %s: %s%n", header, response.getHeader(header));
            }
            System.out.println("============================================== finish =====================================================");
        }
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
    }
}
