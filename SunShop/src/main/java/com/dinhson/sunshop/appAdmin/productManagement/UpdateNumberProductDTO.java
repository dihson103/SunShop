package com.dinhson.sunshop.appAdmin.productManagement;

import jakarta.validation.constraints.NotNull;

public record UpdateNumberProductDTO(
        @NotNull(message = "SizeId can not be empty!!")
        Integer sizeId,
        @NotNull(message = "ColorId can not be empty!!")
        Integer colorId,
        @NotNull(message = "ProductId can not be empty!!")
        Integer productId,
        @NotNull(message = "Number product can not be empty!!")
        Integer number
) {
}
