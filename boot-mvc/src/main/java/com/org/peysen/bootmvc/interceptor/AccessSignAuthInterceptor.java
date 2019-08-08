package com.org.peysen.bootmvc.interceptor;

import com.org.peysen.bootmvc.controller.ConverterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/8 11:29
 */
@Component
public class AccessSignAuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(AccessSignAuthInterceptor.class);
    /**
     * 只有返回true才会继续向下执行，返回false取消当前请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * @author honghh
     * @date 2018/8/13 13:58
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle:请求前调用");
        String url = request.getRequestURI();
        log.info("请求url:{}",url);
        //返回 false 则请求中断
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle:请求后调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");
    }
}
