package com.mediclick.util.pager;

public class PageItem {
    private Integer number;
    private Boolean current;

    public PageItem(Integer number, Boolean current) {
        super();
        this.number = number;
        this.current = current;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }
}
