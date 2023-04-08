package com.mediclick.util.pager;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {
    private String url;
    private Page<T> page;
    private Integer totalPages;
    private Integer elementsPerPage;
    private Integer currentPage;
    private List<PageItem> pages;


    public PageRender(String url, Page<T> page) {
        super();
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();

        elementsPerPage = 3;
        totalPages = page.getTotalPages();
        currentPage = page.getNumber() + 1;

        Integer from, to;
        if (totalPages <= elementsPerPage) {
            from = 1;
            to = totalPages;
        } else {
            if (currentPage <= elementsPerPage / 2) {
                from = 1;
                to = elementsPerPage;
            } else if (currentPage >= totalPages - elementsPerPage / 2) {
                from = totalPages - elementsPerPage + 1;
                to = elementsPerPage;
            } else {
                from = currentPage - elementsPerPage / 2;
                to = elementsPerPage;
            }
        }

        for (int i = 0; i < to; i++) {
            pages.add(new PageItem(from + i, currentPage == from + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public void setPages(List<PageItem> pages) {
        this.pages = pages;
    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevious(){
        return page.hasPrevious();
    }
}
