/**
 * 
 */
package com.bigdata.mapReduce.author;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.WritableComparable;

/**
 * @author yuanpeng.song Create on 2018年10月13日
 */
public class Model implements WritableComparable<Model> {
	private String name;
	private String country;
	private String brands;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the brands
	 */
	public String getBrands() {
		return brands;
	}

	/**
	 * @param brands
	 *            the brands to set
	 */
	public void setBrands(String brands) {
		this.brands = brands;
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		this.name = input.readUTF();
		this.country = input.readUTF();
		this.brands = input.readUTF();

	}

	@Override
	public void write(DataOutput output) throws IOException {
		output.writeUTF(name);
		output.writeUTF(country);
		output.writeUTF(brands);
	}

	@Override
	public int compareTo(Model that) {
		int c1 = this.name.equals(that.name)?0:1;
		if (c1 ==0) {
			//判断国籍是否相同
			String[] thisCountry = this.country.split(" ## ");
			String[] thatCountry = that.country.split(" ## ");
			boolean same = false;
			for(String s:thatCountry){
				if(Arrays.asList(thatCountry).contains(s)){
					same = true;
				}
			}
			int c2 = same?0:1;
			if (c2 == 0) {
				String[] thisBrands = this.brands.split(" ## ");
				boolean contains = false;
				String []thatBrands = that.brands.split(" ## ");
				//判断机构是否一致
				for(String s:thisBrands){
					if (Arrays.asList(thatBrands).contains(s)) {
						contains = true;
					}
				}
				return contains?0:1;
			}
			return c2;
		}
		return c1;
	}

}
