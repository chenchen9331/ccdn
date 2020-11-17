package com.chenchen.common.entity;

import java.util.List;

/**
 * 分页返回结果集
 * @author chenchen
 */
public class PageResultEntity<T> {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 当前查询结果集
     */
    private List<T> rows;

    public PageResultEntity() {
    }

    public PageResultEntity(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageResultEntity{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
