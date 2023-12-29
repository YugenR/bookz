package com.vinai.bookz.common.pagination;

import com.vinai.bookz.dtos.PageConverterDTO;
import org.springframework.data.domain.Page;

public class PageConverter<T> {
    public static <T> PageConverterDTO<T> toDTOPage(Page<T> page) {
        return new PageConverterDTO<>(page.getContent(), page.getTotalElements());
    }
}

