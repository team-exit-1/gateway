package team.exit_1.repo.backend.gateway.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter : OncePerRequestFilter() {

    private val allowedOrigins = listOf(
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

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val origin = request.getHeader("Origin")

        if (origin in allowedOrigins) {
            response.setHeader("Access-Control-Allow-Origin", origin)
            response.setHeader("Access-Control-Allow-Credentials", "true")
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH")
            response.setHeader("Access-Control-Allow-Headers", "*")
            response.setHeader("Access-Control-Max-Age", "3600")
        }

        if (request.method == "OPTIONS") {
            response.status = HttpServletResponse.SC_OK
            return
        }

        filterChain.doFilter(request, response)
    }
}