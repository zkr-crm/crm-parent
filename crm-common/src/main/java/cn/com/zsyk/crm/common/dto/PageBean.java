package cn.com.zsyk.crm.common.dto;

import java.io.Serializable;
import java.util.List;
import com.github.pagehelper.Page;

/**
 * 分页Bean
 * @author
 * @param <T>
 */
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 8656597559014685635L;
    private int pageNum;     // 第几页
    private int pageSize;    // 每页记录数
    private int size;        // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
    private int pages;       // 总页数
    private long total;      // 总记录数
    private List<T> list;    // 结果集
    
    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，而出现一些问题。
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageBean(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + pageNum;
		result = prime * result + pageSize;
		result = prime * result + pages;
		result = prime * result + size;
		result = prime * result + (int) (total ^ (total >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageBean<?> other = (PageBean<?>) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (pageNum != other.pageNum)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (pages != other.pages)
			return false;
		if (size != other.size)
			return false;
		if (total != other.total)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PageBean [pageNum=" + pageNum + ", pageSize=" + pageSize + ", size=" + size + ", pages=" + pages
				+ ", total=" + total + ", list=" + list + "]";
	}
}