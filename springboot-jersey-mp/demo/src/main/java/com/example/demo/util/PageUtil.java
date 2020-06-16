package com.example.demo.util;

import com.example.demo.model.EmployeeEntity;

import java.util.List;

public class PageUtil {

    private Integer page;
    private Integer size;
    private Long totalCount;
    private Long totalPages;
    private List<EmployeeEntity> list;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public List<EmployeeEntity> getList() {
        return list;
    }

    public void setList(List<EmployeeEntity> list) {
        this.list = list;
    }
}
