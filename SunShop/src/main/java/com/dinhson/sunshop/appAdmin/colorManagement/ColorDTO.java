package com.dinhson.sunshop.appAdmin.colorManagement;

import lombok.Builder;

@Builder
public record ColorDTO(
        Integer id,
        String name,
        Integer numberRemain
) {
}
