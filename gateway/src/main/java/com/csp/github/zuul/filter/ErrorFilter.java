package com.csp.github.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.csp.github.base.common.entity.Result;
import com.csp.github.base.common.exception.ServiceException;
import com.netflix.zuul.context.RequestContext;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * @author 陈少平
 * @date 2020-01-19 18:53
 */
@Slf4j
@Component
public class ErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        Throwable cause = throwable.getCause();
        writerError(cause);
        return null;
    }

    private void writerError(Throwable ex) {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletResponse response = ctx.getResponse();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            if (ex instanceof ServiceException) {
                ServiceException serviceException = (ServiceException) ex;
                writer.write(JSONObject.toJSONString(Result.fail(serviceException)));
            } else {
                writer.write(JSONObject.toJSONString(Result.fail(ex.getMessage())));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
