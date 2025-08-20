package com.atc.gateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Extremely simple in-memory rate limiting filter. It is meant only for demo
 * purposes and should be replaced with a distributed solution such as Redis in
 * production deployments.
 */
@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private static final int MAX_REQUESTS_PER_MINUTE = 60;

    private final Map<String, RequestCounter> counters = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String clientIp = request.getRemoteAddr();
        long now = System.currentTimeMillis();
        RequestCounter counter = counters.computeIfAbsent(clientIp, k -> new RequestCounter());
        synchronized (counter) {
            if (now - counter.timestamp > 60_000) {
                counter.timestamp = now;
                counter.count = 0;
            }
            counter.count++;
            if (counter.count > MAX_REQUESTS_PER_MINUTE) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private static class RequestCounter {
        long timestamp = System.currentTimeMillis();
        int count = 0;
    }
}

