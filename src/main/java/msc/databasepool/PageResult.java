package msc.databasepool;

public class PageResult extends BasePageResult {
	public PageResult(){
    }

    public PageResult(int page, int pageSize) {
        this.setPage(page);
        this.setPageSize(pageSize);
    }
}
