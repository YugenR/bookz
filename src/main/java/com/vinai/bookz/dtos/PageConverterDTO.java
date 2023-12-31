package com.vinai.bookz.dtos;

import lombok.Getter;

import java.util.List;

@Getter
public class PageConverterDTO<T> {
    private final List<T> list;
    private final long num;

    public PageConverterDTO(List<T> list, long num) {
        this.list = list;
        this.num = num;
    }

}
