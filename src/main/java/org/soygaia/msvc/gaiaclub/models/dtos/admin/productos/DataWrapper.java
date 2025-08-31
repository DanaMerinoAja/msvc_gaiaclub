package org.soygaia.msvc.gaiaclub.models.dtos.admin.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataWrapper {
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalItems;
    private java.util.List<ListItem> listItems;

    // getters y setters
    public Integer getCurrentPage() { return currentPage; }
    public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }
    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
    public Integer getTotalPages() { return totalPages; }
    public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    public java.util.List<ListItem> getListItems() { return listItems; }
    public void setListItems(java.util.List<ListItem> listItems) { this.listItems = listItems; }
}