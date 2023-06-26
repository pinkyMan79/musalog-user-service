package one.terenin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.terenin.dto.common.OrderProductInfo;
import one.terenin.dto.common.OrderStatus;
import org.springframework.data.domain.PageRequest;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderRequest {
    private UUID orderOwner;
    private Set<OrderProductInfo> products;
    private OrderStatus status;
}
