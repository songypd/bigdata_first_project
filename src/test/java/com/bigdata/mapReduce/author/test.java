/**
 * 
 */
package com.bigdata.mapReduce.author;

/**
 * @author yuanpeng.song
 * Create on 2018年10月13日     
 */
public class test {
	public static void main(String[] args) {
		String aString = "1`2`3`";
		String bString = "123";
//		for(String string : aString.split("`")){
//			System.out.println(string);
//		}
//		for(String string : bString.split("`")){
//			System.out.println(string);
//		}
//		String demo = "23031488.0$$$$Daniel Souery$$$$ 19804.0$$$$Université Libre de Bruxelles ## Psy Pluriel Centre Européen de Psychologie Médicale$$$$Belgium ## Belgium$$$$药学、制药工业$$$$no elements!$$$$(pubyear-2016)-Pharmacological treatment strategies in unipolar depression in European tertiary psychiatric treatment centers – A pharmacoepidemiological cross-sectional multicenter study$$$$European Neuropsychopharmacology$$$$Elsevier$$$$Bruxelles ## Bruxelles$$$$no elements!$$$$no elements!$$$$no elements!$$$$no elements!$$$$no elements!$$$$ 4889513.0";
//		for(String string : demo.split("\\u0024\\u0024\\u0024\\u0024")){
//			System.out.println(string);
//		}
		
		 System.out.println("Mohamed H. Fayed".split(" ")[0].hashCode() % 3);
		 System.out.println("Mohamed".hashCode() % 3);
	}
}
