package com.dinhson.sunshop.appAdmin.categoryManagement;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;

@Builder
public record CategoryDTO(
        Integer id,
        String name,
        String img,
        Integer number,
        Integer numberRemain
) {
}
