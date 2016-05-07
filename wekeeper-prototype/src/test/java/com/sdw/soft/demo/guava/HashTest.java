/**
 * @author shangyd
 * @Date 2015年12月5日 下午3:29:25
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;

public class HashTest {

	@Test
	public void test01(){
		HashFunction hf = Hashing.md5();
		HashCode hashCode = hf.newHasher()
				.putLong(100)
				.putString("hello", Charsets.UTF_8)
				.putObject(new Person(1,"Tom"), new Funnel<Person>() {
					
					private static final long serialVersionUID = 7459887575455656188L;

					@Override
					public void funnel(Person from, PrimitiveSink into) {
						into.putInt(from.id)
						.putString(from.name, Charsets.UTF_8);
					}
					
				}).hash();
		System.out.println(hashCode.toString());
		
	}
	
	/**
	 * BloomFilter
	 */
	@Test
	public void test02(){
		BloomFilter<Person> bloomFilter = BloomFilter.create(new Funnel<Person>() {
			@Override
			public void funnel(Person from, PrimitiveSink into) {
				into.putInt(from.id)
				.putString(from.name, Charsets.UTF_8);
			}
		}, 100, 0.01);
		Person person = new Person(2,"Rose");
		bloomFilter.put(person);
		if(bloomFilter.mightContain(person)){
			System.out.println("bloomfilter contains person:" + person.getName());
		}
	}
	
	private class Person{
		private int id;
		private String name;
		
		public Person() {
			super();
		}
		public Person(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
