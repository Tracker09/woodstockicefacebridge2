/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmm.woodicebridge;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


;

/**
 * 
 * @author A0V2WZZ
 */
public class ObjectListDataProvider {

	private Field[] fields;
	private Method[] methods;
	private List tableDisplayList = new ArrayList();

    public ObjectListDataProvider()
    {
        init("");
    }

	/**
	 * @return the tableDisplayList
	 */
	public List getTableDisplayList() {
		if (!oldSort.equals(sortColumnName) || oldAscending != ascending) {
			sort();
			oldSort = sortColumnName;
			oldAscending = ascending;
		}
		return tableDisplayList;
	}

	public void setList(List tableDisplayList) {
		this.tableDisplayList = tableDisplayList;
		if (tableDisplayList.size() > 0) {
			Object o = tableDisplayList.get(0);
			Class c = o.getClass();
			fields = c.getDeclaredFields();
			methods = c.getMethods();
			oldSort = "";
		}
	}

	private String sortColumnName;
	private boolean ascending;
	private String oldSort;
	private boolean oldAscending;

	public boolean isDefaultAscending(String sortColumn) {
		return true;
	}

	public String getSortColumnName() {
		return sortColumnName;
	}

	public void setSortColumnName(String sortColumnName) {
		oldSort = this.sortColumnName;
		this.sortColumnName = sortColumnName;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		oldAscending = this.ascending;
		this.ascending = ascending;
	}

	public int getSelectedRowsCount() {
		int counter = 0;
		for (Object cdata : getTableDisplayList()) {
			try {
				Class c = cdata.getClass();
				Method method = c.getMethod("isSelected");
				boolean isSel = ((Boolean) method.invoke(cdata)).booleanValue();
				if (isSel) {
					counter++;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return counter;
	}

	public List getSelectedRows() {
		List selectedCodeList = new ArrayList();
		for (Object cdata : getTableDisplayList()) {
			try {
				Class c = cdata.getClass();
				Method method = c.getMethod("isSelected");
				boolean isSel = ((Boolean) method.invoke(cdata)).booleanValue();
				if (isSel) {
					selectedCodeList.add(cdata);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return selectedCodeList;
	}

	public void deSelectAll() {
		Class[] cl = {boolean.class};
		for (Object cdata : getTableDisplayList()) {
			try {
				Class c = cdata.getClass();
				Method method = c.getMethod("setSelected",cl);
				method.invoke(cdata, new Boolean(false));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void selectAll() {
		for (Object cdata : getTableDisplayList()) {
			Class[] cl = {boolean.class};
			try {
				Class c = cdata.getClass();
				Method method = c.getMethod("setSelected",cl);
				method.invoke(cdata, new Boolean(true));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void clearSort() {
        init("");
	}
	public void sort() {
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				if (sortColumnName == null) {
					return 0;
				}
				for (Field f : fields) {
					if (f.getName().equals(sortColumnName)) {
						if (f.getType().getName().equalsIgnoreCase(
								"java.lang.String")) {
							try {
								for (Method m : methods) {
									String attrName = f.getName();
									attrName = Character.toUpperCase(attrName
											.charAt(0))
											+ attrName.substring(1);

									if (m.getName().contains(attrName)
											&& m.getName().contains("get")) {
										String s1 = (String) m.invoke(o1);
										s1 = (String) m.invoke(o1);
										String s2 = (String) m.invoke(o2);
										s2 = (String) m.invoke(o2);
										return ascending ? s1.compareTo(s2)
												: s2.compareTo(s1);
									}
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						if (f.getType().getName().equalsIgnoreCase("double")) {
							try {
								for (Method m : methods) {
									String attrName = f.getName();
									attrName = Character.toUpperCase(attrName
											.charAt(0))
											+ attrName.substring(1);

									if (m.getName().contains(attrName)
											&& m.getName().contains("get")) {
										double s1 = ((Double) m.invoke(o1))
												.doubleValue();
										double s2 = ((Double) m.invoke(o2))
												.doubleValue();
										return ascending ? Double.valueOf(s1)
												.compareTo(Double.valueOf(s2))
												: Double.valueOf(s2).compareTo(
														Double.valueOf(s1));
									}
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						if (f.getType().getName().equalsIgnoreCase("float")) {
							try {
								for (Method m : methods) {
									String attrName = f.getName();
									attrName = Character.toUpperCase(attrName
											.charAt(0))
											+ attrName.substring(1);

									if (m.getName().contains(attrName)
											&& m.getName().contains("get")) {
										float s1 = ((Float) m.invoke(o1))
												.floatValue();
										float s2 = ((Float) m.invoke(o2))
												.floatValue();
										return ascending ? Float.valueOf(s1)
												.compareTo(Float.valueOf(s2))
												: Float.valueOf(s2).compareTo(
														Float.valueOf(s1));
									}
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						if (f.getType().getName().equalsIgnoreCase("int")) {
							try {
								for (Method m : methods) {
									String attrName = f.getName();
									attrName = Character.toUpperCase(attrName
											.charAt(0))
											+ attrName.substring(1);

									if (m.getName().contains(attrName)
											&& m.getName().contains("get")) {
										int s1 = ((Integer) m.invoke(o1))
												.intValue();
										int s2 = ((Integer) m.invoke(o2))
												.intValue();
										return ascending ? Integer.valueOf(s1)
												.compareTo(Integer.valueOf(s2))
												: Integer
														.valueOf(s2)
														.compareTo(
																Integer
																		.valueOf(s1));
									}
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						if (f.getType().getName().equalsIgnoreCase("char")) {
							try {
								for (Method m : methods) {
									String attrName = f.getName();
									attrName = Character.toUpperCase(attrName
											.charAt(0))
											+ attrName.substring(1);

									if (m.getName().contains(attrName)
											&& m.getName().contains("get")) {
										char s1 = ((Character) m.invoke(o1))
												.charValue();
										char s2 = ((Character) m.invoke(o2))
												.charValue();
										return ascending ? Character
												.valueOf(s1).compareTo(
														Character.valueOf(s2))
												: Character
														.valueOf(s2)
														.compareTo(
																Character
																		.valueOf(s1));
									}
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						if (f.getType().getName().equalsIgnoreCase(
								"java.util.Date")) {
							try {
								for (Method m : methods) {
									String attrName = f.getName();
									attrName = Character.toUpperCase(attrName
											.charAt(0))
											+ attrName.substring(1);

									if (m.getName().contains(attrName)
											&& m.getName().contains("get")) {
										double s1 = ((Date) m.invoke(o1))
												.getTime();
										double s2 = ((Date) m.invoke(o2))
												.getTime();
										return ascending ? Double.valueOf(s1)
												.compareTo(Double.valueOf(s2))
												: Double.valueOf(s2).compareTo(
														Double.valueOf(s1));
									}
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
				return 0;
			}
		};
		Collections.sort(tableDisplayList, comparator);
	}

	protected void init(String defaultSortColumn) {
		sortColumnName = defaultSortColumn;
		ascending = isDefaultAscending(defaultSortColumn);
		oldSort = sortColumnName;
		oldAscending = !ascending;
		this.setList(tableDisplayList);
		if (tableDisplayList.size() > 0) {
			Object o = tableDisplayList.get(0);
			Class c = o.getClass();
			Field[] fields = c.getFields();
		}
	}
}
