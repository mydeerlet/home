package com.mydeerlet.home.bean;

import java.util.List;

public class BaseListEntity<T> {

    private int currentPage;
    private int totalPage;
    private List<T> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean hasNextPage(){
        return currentPage < totalPage;
    }
}
