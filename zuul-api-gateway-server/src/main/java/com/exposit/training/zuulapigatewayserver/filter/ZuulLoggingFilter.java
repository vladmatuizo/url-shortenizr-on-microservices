package com.exposit.training.zuulapigatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //filter for every request
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("Request -> {};\nRequest URI -> {};\nRequest HTTP method -> {};", request, request.getRequestURI(), request.getMethod());
        return null;
    }
}
