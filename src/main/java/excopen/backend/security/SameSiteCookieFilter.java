package excopen.backend.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SameSiteCookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(res) {
            @Override
            public void addHeader(String name, String value) {
                if ("Set-Cookie".equalsIgnoreCase(name) && value.startsWith("JSESSIONID")) {
                    value = value + "; SameSite=None; Secure";
                }
                super.addHeader(name, value);
            }

            @Override
            public void setHeader(String name, String value) {
                if ("Set-Cookie".equalsIgnoreCase(name) && value.startsWith("JSESSIONID")) {
                    value = value + "; SameSite=None; Secure";
                }
                super.setHeader(name, value);
            }
        };

        chain.doFilter(request, responseWrapper);
    }
}
