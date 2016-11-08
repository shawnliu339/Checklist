package com.ncut.wms.util.system;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ToExcel<T> {

	public void writeExcel(List<T> rsList, String filePath, String sheetName,
			Vector<String> columnName) {
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		
		int rowNum = 1; // 从第一行开始写入
		try {
			workbook = Workbook.createWorkbook(new File(filePath)); // 创建Excel文件
			sheet = workbook.createSheet(sheetName, 0); // 创建名为 sheetName 的工作簿

			// 首先将列名写入
			this.writeCol(sheet, columnName, 0); 
			// 将结果集写入
			for (T rs :rsList) {
				// 用以保存一行数据
				Vector<String> col = new Vector<String>();
				// 将一行内容保存在col中
				String [] fieldName = this.getFiledName(rs);
				for (int i = 0; i < fieldName.length; i++) {
					col.add(this.getFieldValueByName(fieldName[i], rs).toString());
				}
				// 写入Excel
				this.writeCol(sheet, col, rowNum++);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭
				workbook.write();
				workbook.close();
				rsList.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void writeCol(WritableSheet sheet, Vector<String> col, int rowNum)
			throws RowsExceededException, WriteException {
		int size = col.size(); // 获取集合大小

		for (int i = 0; i < size; i++) { // 写入每一列
			Label label = new Label(i, rowNum, col.get(i));
			sheet.addCell(label);
		}
	}

	/**
	 * 根据属性名获取属性值
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * */
	private Object getFieldValueByName(String fieldName, Object o)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + fieldName.substring(1);
		Method method = o.getClass().getMethod(getter, new Class[] {});
		Object value = method.invoke(o, new Object[] {});
		return value;
	}

	/**
	 * 获取属性名数组
	 * */
	private String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}
}
