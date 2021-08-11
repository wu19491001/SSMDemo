package com.wu.tools;

/**
 * @author wu
 *
 */
public class Tools {
/**
 * 反转字符串
 * @param String
 * @return String
 * 
 */
public static String toolReverse(String inp) {
	  StringBuilder sb=new StringBuilder(inp);
      String out=sb.reverse().toString();
      return out;
  }
}
