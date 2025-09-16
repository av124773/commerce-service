package com.gtelant.commerce_service.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
    private int userId;
    private String shippingAddress;
    private BigDecimal delivery; // 運費可能會根據地點變更，例如離島或偏遠地區，感覺這裡應該有更好的做法。
    private BigDecimal tax; // 稅金理論上跟訂單金額有關，感覺不適合直接用request送出。
    private List<ItemRequest> items;
}
