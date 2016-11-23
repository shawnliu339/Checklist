package com.ncut.wms.util.system;

/**
 * 工具类
 * @author 刘思远
 *
 */
public class Tools {
	
	/**
	 * 生成编号
	 * @param code
	 * @return
	 */
	public static String formatCode(String code){
		try {
			int length = code.length();
			Integer num = Integer.valueOf(code.substring(length-4,length))+1;
			String codenum = num.toString();
			int codelength = codenum.length();
			for (int i = 4; i > codelength; i--) {
				codenum = "0"+codenum;
			}
			return codenum;
		} catch (NumberFormatException e) {
			return "0100";
		}
	}
	
	
}
