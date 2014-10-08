package com.mmm.woodicebridge;

import com.icesoft.faces.component.datapaginator.DataPaginator;

public class AbstractPageBean extends
		com.sun.rave.web.ui.appbase.AbstractPageBean {

	private int pageNumberHolder=1;

	public int getPageNumberHolder() {
		return pageNumberHolder;
	}

	public void setPageNumberHolder(int pageNumberHolder) {
		this.pageNumberHolder = pageNumberHolder;
	}

	private DataPaginator dataPaginator;

	public DataPaginator getDataPaginator() {
		return dataPaginator;
	}

	public void setDataPaginator(DataPaginator dataPaginator) {
		this.dataPaginator = dataPaginator;
	}

	public int getPageNumber() {
		if (dataPaginator != null && dataPaginator.getUIData() != null) {
			return dataPaginator.getPageIndex();
		}
		return pageNumberHolder;
	}

	public void goToPage() {
		if (dataPaginator != null && dataPaginator.getUIData() != null) {
			int tempPageNumber = getPageNumberHolder();
			if (dataPaginator.getPageCount() <= 1) {
				return;
			}
			if (tempPageNumber < 1
					|| tempPageNumber > dataPaginator.getPageCount()) {
				return;
			}
			dataPaginator.gotoFirstPage();
			for (int i = 1; i <= dataPaginator.getPageCount(); i++) {
				if (i == tempPageNumber) {
					break;
				} else {
					dataPaginator.gotoNextPage();
				}
			}
		}
	}

}
