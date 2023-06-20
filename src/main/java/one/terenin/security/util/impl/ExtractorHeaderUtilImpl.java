package one.terenin.security.util.impl;

import jakarta.servlet.http.HttpServletRequest;
import one.terenin.security.util.ExtractorHeaderUtil;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExtractorHeaderUtilImpl implements ExtractorHeaderUtil {

    private final String PREFIX  = "Bearer ";
    private final String HEADER_NAME  = "AUTHORIZATION";

    @Override
    public boolean isHeaderPresent(HttpServletRequest request) {
        return request.getHeader(HEADER_NAME) != null
                && request.getHeader(HEADER_NAME).startsWith(PREFIX);
    }

    @Override
    public Optional<String> extractToken(HttpServletRequest request) {
        if (isHeaderPresent(request)){
            return Optional.of(request.getHeader(HEADER_NAME).substring(PREFIX.length()));
        }else {
            return null;
        }
    }
}
