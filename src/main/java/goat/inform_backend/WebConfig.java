package goat.inform_backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 💡 모든 API 경로에 대해 CORS 설정을 적용합니다.
                .allowedOrigins("*") // 💡 ngrok의 주소가 매번 바뀌므로 모든 출처(*)를 허용합니다. (테스트용)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 지정
                .allowedHeaders("*") // 모든 헤더를 허용
                .allowCredentials(false) // 자격 증명(쿠키 등)을 허용
                .maxAge(3600);
    }
}