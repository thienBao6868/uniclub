package com.Thienbao.uniclub.payload.request;

import com.Thienbao.uniclub.model.STATUS_ORDER;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderRequest {
    @NotNull(message = "Id order not null")
    private int idOrder;

    private STATUS_ORDER status;
}
