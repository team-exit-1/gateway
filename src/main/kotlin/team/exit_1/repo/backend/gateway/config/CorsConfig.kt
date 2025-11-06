package team.exit_1.repo.backend.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration().apply {
            allowCredentials = true
            allowedOrigins = listOf(
                "http://localhost:3000",
                "http://localhost:3001",
                "http://localhost:5173",
                "http://localhost:5174",
                "http://localhost:8080",
                "http://localhost:8081",
                "https://refo-core-hackerton.dsmhs.kr",
                "https://refo-hackerton.dsmhs.kr",
                "https://refo-rag-hackerton.dsmhs.kr",
                "https://refo-llm-hackerton.dsmhs.kr"
            )
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
            allowedHeaders = listOf("*")
            maxAge = 3600L
        }
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}