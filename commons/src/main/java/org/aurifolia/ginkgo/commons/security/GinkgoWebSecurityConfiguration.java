package org.aurifolia.ginkgo.commons.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO
 *
 * @author VNElinpe
 * @since 1.0
 **/
@Configuration
@EnableWebSecurity
public class GinkgoWebSecurityConfiguration {
    /**
     * 过滤连
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationProperties authenticationProperties,
                                                   AuthorizationProperties authorizationProperties) throws Exception {
//        httpSecurity.authorizeHttpRequests(authorize ->
//                authorize.requestMatchers("/role/test003").permitAll()
//                        .anyRequest().permitAll());
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        httpSecurity.requestCache(RequestCacheConfigurer::disable);
//        httpSecurity.securityContext(securityContext ->
//                securityContext.securityContextRepository(securityContextRepository));
//        httpSecurity.addFilterAfter(new RequestBodyAuthenticationFilter(authenticationManager(userDetailsService),
//                securityContextRepository), LogoutFilter.class);
//        return httpSecurity.build();
        return null;
    }

    public static Map<Integer, Integer> map = new HashMap<>();
    static {
        for (int i = 0; i <= 0xff; i++) {
            map.put(i, Integer.bitCount(i));
        }
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[2504];
        long[] longs = new long[313];
        ThreadLocalRandom.current().nextBytes(bytes);
        for (int i = 0; i < 2000; i++) {
            bytes[i] = (byte) 0xff;
        }
        for (int i = 0, j = 0; i < longs.length; i++) {
            longs[i] = (((long) bytes[j++] & 0xff) << 56) | (((long) bytes[j++] & 0xff) << 48)
                    | (((long) bytes[j++] & 0xff) << 40) | (((long) bytes[j++] & 0xff) << 32)
                    | (((long) bytes[j++] & 0xff) << 24) | (((long) bytes[j++] & 0xff) << 16)
                    | (((long) bytes[j++] & 0xff) << 8) | ((long) bytes[j++] & 0xff);
        }
        int times = 20000;
        getSumWithCircle(bytes, times);
        getSumWithMap(bytes, times);
        getSumWithIntegerBitCount(bytes, times);
        getLongSum(longs, times);
        getLong1(bytes, times);

        System.out.println();
    }

    public static void getLong1(byte[] bytes, int times) {
        int j = 0;
        int sum = 0;
        Instant now = Instant.now();
        for (int i = 0; i < times; i++) {
            sum = 0;
            j = 0;
            while (j < bytes.length) {
                sum += Long.bitCount((((long) bytes[j++] & 0xff) << 56) | (((long) bytes[j++] & 0xff) << 48)
                        | (((long) bytes[j++] & 0xff) << 40) | (((long) bytes[j++] & 0xff) << 32)
                        | (((long) bytes[j++] & 0xff) << 24) | (((long) bytes[j++] & 0xff) << 16)
                        | (((long) bytes[j++] & 0xff) << 8) | ((long) bytes[j++] & 0xff));
            }
        }
        System.out.println("long1 circle sum: " + sum);
        System.out.println("long1 circle cost: " + Duration.between(now, Instant.now()).toMillis());
    }

    public static void getLongSum(long[] bytes, int times) {
        Instant now = Instant.now();
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum = 0;
            for (int j = bytes.length - 1; j >= 0; j--) {
                sum += Long.bitCount(bytes[j]);
            }
        }
        System.out.println("long circle sum: " + sum);
        System.out.println("long circle cost: " + Duration.between(now, Instant.now()).toMillis());
    }

    public static void getSumWithMap(byte[] bytes, int times) {
        Instant now = Instant.now();
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum = 0;
            for (int j = bytes.length - 1; j >= 0; j--) {
                sum += map.get(bytes[j] & 0xff);
            }
        }
        System.out.println("map sum: " + sum);
        System.out.println("map cost: " + Duration.between(now, Instant.now()).toMillis());
    }

    public static void getSumWithCircle(byte[] bytes, int times) {
        Instant now = Instant.now();
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum = 0;
            for (int j = bytes.length - 1; j >= 0; j--) {
                byte aByte = bytes[j];
                while (aByte != 0) {
                    aByte = (byte) (aByte & (aByte - 1));
                    sum++;
                }
            }
        }
        System.out.println("circle sum: " + sum);
        System.out.println("circle cost: " + Duration.between(now, Instant.now()).toMillis());
    }

    public static void getSumWithIntegerBitCount(byte[] bytes, int times) {
        Instant now = Instant.now();
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum = 0;
            for (int j = bytes.length - 1; j >= 0; j--) {
                sum += Integer.bitCount(bytes[j] & 0xff);
            }
        }
        System.out.println("Integer.bitCount sum: " + sum);
        System.out.println("Integer.bitCount cose: " + Duration.between(now, Instant.now()).toMillis());
    }

    public static class Test001 {
        public static void main(String[] args) {
            int times = 10000;
            byte[] bytes = new byte[2500];
            ThreadLocalRandom.current().nextBytes(bytes);
            String base64 = null;
            Instant now = Instant.now();
            for (int i = 0; i < times; i++) {
                base64 = Base64.getEncoder().encodeToString(bytes);
            }
            System.out.println(Duration.between(now, Instant.now()).toMillis());
            now = Instant.now();
            for (int i = 0; i < times; i++) {
                bytes = Base64.getDecoder().decode(base64);
            }
            System.out.println(Duration.between(now, Instant.now()).toMillis());

        }
    }
}
