package excopen.backend.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class SameSiteCookieFilter implements Filter {

    private static final String SET_COOKIE_HEADER = "Set-Cookie";
    private static final Pattern JSESSIONID_PATTERN =
            Pattern.compile("(?i)^(.*;\\s*)?JSESSIONID\\s*=");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(res) {

            private String processCookie(String value) {
                if (value != null && JSESSIONID_PATTERN.matcher(value).find()) {
                    String lowerValue = value.toLowerCase();
                    StringBuilder sb = new StringBuilder(value);

                    if (!lowerValue.contains("samesite")) {
                        sb.append("; SameSite=None");
                    }

                    if (!lowerValue.contains("secure")) {
                        sb.append("; Secure");
                    }

                    return sb.toString();
                }
                return value;
            }

            @Override
            public void addHeader(String name, String value) {
                if (SET_COOKIE_HEADER.equalsIgnoreCase(name)) {
                    super.addHeader(name, processCookie(value));
                } else {
                    super.addHeader(name, value);
                }
            }

            @Override
            public void setHeader(String name, String value) {
                if (SET_COOKIE_HEADER.equalsIgnoreCase(name)) {
                    super.setHeader(name, processCookie(value));
                } else {
                    super.setHeader(name, value);
                }
            }
        };

        chain.doFilter(request, responseWrapper);
    }
}