package com.portfolio.quice;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
class LoggingFilter implements Filter {

  private final Log log = LogFactory.getLog(getClass());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain)
      throws IOException, ServletException {

    Assert.isTrue(request instanceof HttpServletRequest, "this assumes you have an HTTP request");

    HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(request);
    String uri = httpServletRequest.getRequestURI();
    this.log.info("new request for " + uri);

    long time = System.currentTimeMillis();

    chain.doFilter(request, response);

    long delta = System.currentTimeMillis() - time;
    this.log.info("request for " + uri + " took " + delta + "ms");

  }

  @Override
  public void destroy() {

  }
}