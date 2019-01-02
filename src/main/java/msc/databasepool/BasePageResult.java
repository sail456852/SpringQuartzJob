package msc.databasepool;

@SuppressWarnings("unused")
public abstract class BasePageResult {

	private int page = 1; // 实际页号

	private int pageSize = 10; // 每页记录数

	private int total = 0; // 总记录数

	private int pageCount = 1; // 总页数

	private String where; // 条件

	@Override
	public String toString() {
		return "page:" + page + "," + "pageSize:" + pageSize + ","
				+ "pageCount:" + pageCount + "," + "total:" + total;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			page = 1;
		}
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		pageCount = total % pageSize == 0 ? total / pageSize : total / pageSize
				+ 1;
	}
	
	public int getLimit() {
		return pageSize;
	}

	public int getStart() {
		return (page - 1) * pageSize;
	}
}
