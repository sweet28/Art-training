package com.arttraining.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil {
	/**
	 * 将Double对象保留2位小数
	 */
	public static Double formatDouble1(Double number) {
		double d=0.0;
		if(number!=null) {
			d=number.doubleValue();
		}
		return Double.parseDouble(new DecimalFormat("#.00").format(d));
	}
	public static double formatDouble2(double d) {
		return new BigDecimal(d).setScale(2, BigDecimal.ROUND_UP).doubleValue();
	}
	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.valueOf(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isDouble(String value) {
		try {
			Double.valueOf(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * 把16进制字符串转换成字节数组
	 */
	public static byte[] hexStringToByte(String hex) {   
	    int len = (hex.length() / 2);   
	    byte[] result = new byte[len];   
	    char[] achar = hex.toCharArray();   
	    for (int i = 0; i < len; i++) {   
	     int pos = i * 2;   
	     result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));   
	    }   
	    
	    System.out.println(result);
	    return result;   
	} 
	
	private static byte toByte(char c) {   
	    byte b = (byte) "0123456789ABCDEF".indexOf(c);   
	    return b;   
	}
	
	/** *//**  
	    * 把字节数组转换成16进制字符串  
	    * @param bArray  
	    * @return  
	    */   
	public static final String bytesToHexString(byte[] bArray) {   
	    StringBuffer sb = new StringBuffer(bArray.length);   
	    String sTemp;   
	    for (int i = 0; i < bArray.length; i++) {   
	     sTemp = Integer.toHexString(0xFF & bArray[i]);   
	     if (sTemp.length() < 2)   
	      sb.append(0);   
	     sb.append(sTemp.toUpperCase());   
	    }   
	    return sb.toString();   
	}  
	  
	/** *//**  
	    * 把字节数组转换为对象  
	    * @param bytes  
	    * @return  
	    * @throws IOException  
	    * @throws ClassNotFoundException  
	    */   
	public static final Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {   
	    ByteArrayInputStream in = new ByteArrayInputStream(bytes);   
	    ObjectInputStream oi = new ObjectInputStream(in);   
	    Object o = oi.readObject();   
	    oi.close();   
	    return o;   
	}  
	  
	/** *//**  
	    * 把可序列化对象转换成字节数组  
	    * @param s  
	    * @return  
	    * @throws IOException  
	    */   
	public static final byte[] objectToBytes(Serializable s) throws IOException {   
	    ByteArrayOutputStream out = new ByteArrayOutputStream();   
	    ObjectOutputStream ot = new ObjectOutputStream(out);   
	    ot.writeObject(s);   
	    ot.flush();   
	    ot.close();   
	    return out.toByteArray();   
	}  
	  
	public static final String objectToHexString(Serializable s) throws IOException{   
	    return bytesToHexString(objectToBytes(s));   
	}  
	  
	public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException{   
	    return bytesToObject(hexStringToByte(hex));   
	}  
	  
	/** *//**  
	    * @函数功能: BCD码转为10进制串(阿拉伯数据)  
	    * @输入参数: BCD码  
	    * @输出结果: 10进制串  
	    */   
	public static String bcd2Str(byte[] bytes){   
	    StringBuffer temp=new StringBuffer(bytes.length*2);  
	  
	    for(int i=0;i<bytes.length;i++){   
	     temp.append((byte)((bytes[i]& 0xf0)>>>4));   
	     temp.append((byte)(bytes[i]& 0x0f));   
	    }   
	    return temp.toString().substring(0,1).equalsIgnoreCase("0")?temp.toString().substring(1):temp.toString();   
	}  
	  
	/** *//**  
	    * @函数功能: 10进制串转为BCD码  
	    * @输入参数: 10进制串  
	    * @输出结果: BCD码  
	    */   
	public static byte[] str2Bcd(String asc) {   
	    int len = asc.length();   
	    int mod = len % 2;  
	  
	    if (mod != 0) {   
	     asc = "0" + asc;   
	     len = asc.length();   
	    }  
	  
	    byte abt[] = new byte[len];   
	    if (len >= 2) {   
	     len = len / 2;   
	    }  
	  
	    byte bbt[] = new byte[len];   
	    abt = asc.getBytes();   
	    int j, k;  
	  
	    for (int p = 0; p < asc.length()/2; p++) {   
	     if ( (abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {   
	      j = abt[2 * p] - '0';   
	     } else if ( (abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {   
	      j = abt[2 * p] - 'a' + 0x0a;   
	     } else {   
	      j = abt[2 * p] - 'A' + 0x0a;   
	     }  
	  
	     if ( (abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {   
	      k = abt[2 * p + 1] - '0';   
	     } else if ( (abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {   
	      k = abt[2 * p + 1] - 'a' + 0x0a;   
	     }else {   
	      k = abt[2 * p + 1] - 'A' + 0x0a;   
	     }  
	  
	     int a = (j << 4) + k;   
	     byte b = (byte) a;   
	     bbt[p] = b;   
	    }   
	    return bbt;   
	}   
}
