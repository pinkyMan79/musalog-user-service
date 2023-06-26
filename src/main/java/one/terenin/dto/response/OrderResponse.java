package one.terenin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.terenin.dto.common.OrderStatus;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
    private UUID orderId;
    private UUID ownerId;
    private OrderStatus status;
    private String message;
}
