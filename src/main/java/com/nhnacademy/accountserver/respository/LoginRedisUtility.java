package com.nhnacademy.accountserver.respository;

import com.nhnacademy.accountserver.dtos.LoginResponseDto;
import com.nhnacademy.accountserver.entity.Account;
import com.nhnacademy.accountserver.entity.Member;
import jakarta.servlet.http.Cookie;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
public class LoginRedisUtility {

    private final RedisTemplate<String, Object> redisTemplate;

    // sessionId 생성
    private final String sessionId = UUID.randomUUID().toString();
    private final String key = "LOGIN_SESSION:";

    public LoginRedisUtility(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // login -> redis session 저장
    public LoginResponseDto loginRedisSave(long memberId) {
        // memberId(long) redis에 저장
        redisTemplate.opsForHash().put(key, sessionId, memberId);
        redisTemplate.expire(key, Duration.ofHours(1));  // 1시간 후 자동 만료

        return new LoginResponseDto(sessionId);
    }

    // logout -> redis session 삭제
//    public void logoutDeleteRedis() {
//        redisTemplate.opsForHash().delete(key, sessionId);
//    }
}