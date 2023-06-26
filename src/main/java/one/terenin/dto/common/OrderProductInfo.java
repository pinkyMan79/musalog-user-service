package one.terenin.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderProductInfo {
    // the url location in product
    private String productLocation;
    private String productName;
    private String productDescription;
    private String productQuality;
    private String productCount;
    private Boolean hasInStock;
}
