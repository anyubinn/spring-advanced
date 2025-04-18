package org.example.expert.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminLoggingAspect {

    private final ObjectMapper objectMapper;

    @Around("@annotation(org.example.expert.domain.common.annotation.Admin)")
    public Object logAdminAccess(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Long userId = (Long) request.getAttribute("userId");
        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        String requestBodyJson = Arrays.stream(joinPoint.getArgs()).map(arg -> {
            try {
                return objectMapper.writeValueAsString(arg);
            } catch (JsonProcessingException e) {
                return "json으로 변환에 실패했습니다.";
            }
        }).collect(Collectors.joining(", "));

        log.info("[ADMIN REQUEST] UserId: {}, URL: {}, Time: {}, Request: {}", userId, requestUrl, requestTime,
                requestBodyJson);

        Object result = joinPoint.proceed();
        String responseBodyJson = objectMapper.writeValueAsString(result);
        LocalDateTime responseTime = LocalDateTime.now();

        log.info("[ADMIN RESPONSE] UserId: {}, URL: {}, Time: {}, Response: {}", userId, requestUrl, responseTime, responseBodyJson);

        return result;
    }
}
