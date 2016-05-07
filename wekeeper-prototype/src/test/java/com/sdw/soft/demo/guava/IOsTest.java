/**
 * @author shangyd
 * @Date 2015年12月5日 下午3:29:19
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeTraverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.Resources;

public class IOsTest {

	@Test
	public void test01(){
		//copy file
		ByteSource bs = Files.asByteSource(new File("D:/CustomKeysSample.txt"));
		ByteSink bsink = Files.asByteSink(new File("D:/1.txt"), FileWriteMode.APPEND);
		try {
			bsink.openStream().write(bs.read());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test02(){
		try {
			//Read the lines of a UTF-8 text file
			ImmutableList<String> lines = Files.asCharSource(new File("D:/1.txt"), Charsets.UTF_8).readLines();
			for(String str : lines){
//				System.out.println(str);
			}
			//count distinct word occurrences in a file
			Multiset<String> word = HashMultiset.create(Splitter.on(CharMatcher.WHITESPACE)
					.trimResults()
					.omitEmptyStrings()
					.split(Files.asCharSource(new File("D:/1.txt"), Charsets.UTF_8).read()));
			for(String str1:word){
//				System.out.println(str1);
			}
			
			//SHA-1 a file
			HashCode hash = Files.asByteSource(new File("D:/1.txt")).hash(Hashing.sha1());
			System.out.println(hash.toString());
			//copy one url into a file
			long length = Resources.asByteSource(new URL("http://www.163.com")).copyTo(Files.asByteSink(new File("D:/2.txt"), FileWriteMode.APPEND));
			System.out.println("length=" + length);
			//create parent Dir
			Files.createParentDirs(new File("D:/123/1.txt"));
			//by url get file extension suffix
			String suffix = Files.getFileExtension("D:/1.txt");
			System.out.println(suffix);
			//get file name without suffix
			String withoutsuffix = Files.getNameWithoutExtension("D:/1.txt");
			System.out.println(withoutsuffix);
			//simplify path
			String result = Files.simplifyPath("a/../1.txt");
			System.out.println(result);
			//traverser file
			TreeTraverser<File> tt = Files.fileTreeTraverser();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
