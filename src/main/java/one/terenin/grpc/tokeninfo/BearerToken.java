package one.terenin.grpc.tokeninfo;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import one.terenin.security.constants.KeyConstant;

import java.util.concurrent.Executor;

/** @apiNote
 * This is logic for token injection useless for this service,
 * but I put it here realise in the api gateway
 */
public class BearerToken extends CallCredentials {

    private String value;

    public BearerToken(String value) {
        this.value = value;
    }

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor appExecutor, MetadataApplier applier) {
        appExecutor.execute(() -> {
            try {
                Metadata headers = new Metadata();
                headers.put(KeyConstant.AUTHORIZATION_METADATA_KEY,
                        String.format("%s %s", KeyConstant.TOKEN_PREFIX, value));
                applier.apply(headers);
            } catch (Throwable e) {
                applier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {

    }
}
