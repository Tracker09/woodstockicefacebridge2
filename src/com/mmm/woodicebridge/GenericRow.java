package com.mmm.woodicebridge;

import java.io.Serializable;

public class GenericRow implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean selected = false;
	
	public void setSelected(boolean selected)
	{
		this.selected=selected;
	}
	public boolean isSelected()
	{
		return selected;
	}

}
