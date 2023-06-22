package one.terenin.grpc.interceptor;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import one.terenin.security.propertysource.JWTPropertySource;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import static one.terenin.security.constants.KeyConstant.AUTHORIZATION_METADATA_KEY;
import static one.terenin.security.constants.KeyConstant.CLIENT_ID_CONTEXT_KEY;
import static one.terenin.security.constants.KeyConstant.TOKEN_PREFIX;

@Component
public class AuthInterceptor implements ServerInterceptor {

    private final JWTPropertySource propertySource;

    public AuthInterceptor(JWTPropertySource propertySource) {
        this.propertySource = propertySource;
    }


    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        String exceptedToken = headers.get(AUTHORIZATION_METADATA_KEY);
        Status status;
        if (exceptedToken == null){
            status = Status.UNAUTHENTICATED.withDescription("Token is missing");
        }else if (!exceptedToken.startsWith(TOKEN_PREFIX)){
            status = Status.ABORTED.withDescription("Unknown token prefix type");
        }else {
            try {
                String token = exceptedToken.substring(TOKEN_PREFIX.length()).trim();
                Context context = verifyAndPut(token);
                // add status here?
                return Contexts.interceptCall(context, call, headers, next);
            } catch (Exception e) {
                status = Status.UNAUTHENTICATED.withDescription("Throw exception through intercept auth");
            }

        }
        return new ServerCall.Listener<ReqT>() {
            @Override
            public void onMessage(ReqT message) {
                super.onMessage(message);
            }
        };
    }

    private Claims extractClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(propertySource.getJwtSecret().getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    private Context verifyAndPut(String accessToken){
        Claims claims = extractClaimsFromToken(accessToken);
        Date exp = claims.getExpiration();
        if (exp.before(new Date())){
            throw new RuntimeException();
        }else {
            return Context.current().withValue(CLIENT_ID_CONTEXT_KEY, claims.getSubject());
        }
    }

    public JWTPropertySource getPropertySource() {
        return propertySource;
    }
}
