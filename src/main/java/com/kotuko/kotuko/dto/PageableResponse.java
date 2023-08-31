package com.kotuko.kotuko.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageableResponse<T> {
    private int totalPage;
    private long totalElements;
    private boolean last;
    private int size;
    private List<T> content;

    public PageableResponse(Page<T> page) {
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.last = page.isLast();
        this.size = page.getSize();
        this.content = page.getContent();
    }

}
