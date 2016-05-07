/**
 * @author shangyd
 * @Date 2015年12月5日 下午3:27:35
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
public class BasicUtiliesTest {

	/**
	 * Basic Utilities
	 * Optional
	 * 静态方法:
	 * Optional.of(T) 获得一个Optional对象 其内部包含了一个非null的T类型实例,若T=null,即立刻报错
	 * Optional.absent() 获得一个Optional对象,其内部包含了空值
	 * Optional.fromNullable(T) 将一个T的实例转换为Optional对象 T可以不为空,也可以为空Optional.fromNullable(null)此时等价于Optional.absent()
	 * 
	 * 实例方法:
	 * boolean isPresent() 若Optional包含的T实例不为null,则返回true 若T实例为null 则返回false
	 * T get() 返回Optional实例中包含的T实例,该T实例必须不为空 否则为null调用了get() 则抛出IllegalStateException异常
	 * T or(T) 若Optional实例中包含了传入的T的相同实例,则返回Optional包含的该T实例,否则返回输入的T实例作为默认值
	 * T orNull() 返回Optional实例中包含的非空T实例,若Optional中包含的值是空值,返回null
	 * Set<T> asSet() 返回一个不可修改的Set,该Set中包含了Optional实例中包含的所有非空存在的T实例,且在该Set中,每个T实例都是单态,若Optional中没有非空存在的T实例,则返回空不可修改的Set
	 */
	@Test
	public void test01(){
		try {
			Optional optional = Optional.of(1);
			System.out.println(optional.or(2));
//			Optional nullObj = Optional.absent();
//			System.out.println(nullObj.or(123));
//			System.out.println(nullObj.isPresent());
//			System.out.println(optional.isPresent());
//			System.out.println(optional.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 前置条件  Preconditions
	 * 1、没有额外参数：抛出的异常中没有错误消息  checkArgument(expression)
	 * 2、有一个Object参数对象作为额外参数:抛出异常使用Object.toString()作为错误消息
	 * checkArgument(expression,errMesg)
	 * 3、有一个String对象作为额外参数,并且有一组任意数量的附加Object对象:这个变种处理异常消息的方式有点儿类似printf,但
	 * 考虑GWT的兼容性和效率,只支持%s指示符
	 * checkArgument(expression,"errmsgL: %s",errmsg)
	 * 
	 * 
	 * checkArgument(boolean) 检查boolean是否为true  用来检查传递给方法的参数  IllegalArgumentException
	 * checkNotNull(T) 检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull	 	NullPointerException
	 * checkState(boolean) 用来检查对象的某些状态 	IllegalStateException
	 * checkElementIndex(int index,int size) 检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size   IndexOutOfBoundsException
	 * checkPositionIndex(int index,int size) 检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size   IndexOutOfBoundsException
	 * checkPositionIndexes(int start,int end,int size) 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效  IndexOutOfBoundsException
	 */
	@Test
	public void test02(){
//		Preconditions.checkArgument(1<0);
//		Preconditions.checkArgument(1<0,"exception is not suitable... ");
//		Preconditions.checkArgument(1<0, "math error:%s", "1 not less 0");
		GuavaTest guava = null;
		Preconditions.checkNotNull(guava);
		Preconditions.checkState(1<0);
//		int index = Preconditions.checkElementIndex(2, 3);
		int index = Preconditions.checkPositionIndex(3, 3);
		System.out.println(index);
		Preconditions.checkPositionIndexes(0, 3, 3);
	}
	
	/**
	 * Objects 常见Object方法
	 * 
	 */
	@Test
	public void test03(){
		//equals  帮助执行null敏感的equals判断
		Objects.equal("a", "b");
		//hashCode  对传入的字段序列计算出合理的、顺序敏感的散列值
		System.out.println(Objects.hashCode("a","b"));
		//toString  
		System.out.println(Objects.toStringHelper("MyObject").add("attechment", "123").toString());
		System.out.println(Objects.toStringHelper(this).add("attechment", "123").toString());
		//compare/compareTo
		ComparisonChain.start().compare(1, 1).compare(2, 3).compare(5, 3,Ordering.natural().nullsLast()).result();
	}
	
	
	
	/**
	 * Fluent风格比较器
	 * Ordering实例就是一个特殊的Comparator实例。Ordering把很多基于Comparator的静态方法（如Collections.max）包装为自己的实例方法（非静态方法），
	 * 并且提供了链式调用方法，来定制和增强现有的比较器。
	 * 
	 * 常见的排序器可以由下面的静态方法创建:
	 * natural()  对可排序类型做自然排序，如数字按大小，日期按先后排序
	 * usingToString()  按对象的字符串形式做字典排序[lexicographical ordering]
	 * from(Comparator)  把给定的Comparator转化为排序器
	 * 
	 * 链式调用方法：通过链式调用，可以由给定的排序器衍生出其它排序器
	 * reverse()  获取语义相反的排序器
	 * nullsFirst()  使用当前排序器，但额外把null值排到最前面。
	 * nullsLast()	使用当前排序器，但额外把null值排到最后面。
	 * compound(Comparator)	合成另一个比较器，以处理当前排序器中的相等情况。
	 * lexicographical()	基于处理类型T的排序器，返回该类型的可迭代对象Iterable<T>的排序器。
	 * onResultOf(Function)	对集合中元素调用Function，再按返回值用当前排序器排序。
	 * 
	 * 运用排序器：Guava的排序器实现有若干操纵集合或元素值的方法
	 * greatestOf(Iterable iterable, int k)	获取可迭代对象中最大的k个元素。
	 * isOrdered(Iterable)	判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。
	 * sortedCopy(Iterable)	判断可迭代对象是否已严格按排序器排序：不允许排序值相等的元素。
	 * min(E, E)	返回两个参数中最小的那个。如果相等，则返回第一个参数。
	 * min(E, E, E, E...)	返回多个参数中最小的那个。如果有超过一个参数都最小，则返回第一个最小的参数。
	 * min(Iterable)	返回迭代器中最小的元素。如果可迭代对象中没有元素，则抛出NoSuchElementException。
	 */
	@Test
	public void test04(){
		//实现自定义的排序器时，除了用上面的from方法，也可以跳过实现Comparator，而直接继承Ordering：
		Ordering<String> byLengthOrdering = new Ordering<String>() {
			  public int compare(String left, String right) {
			    return Ints.compare(left.length(), right.length());
			  }
			};
			Ordering.natural().max(1, 2);
	}
	
	/**
	 * Throwables
	 * RuntimeException   propagate(Throwable)	如果Throwable是Error或RuntimeException，直接抛出；否则把Throwable包装成RuntimeException抛出。返回类型是RuntimeException，
	 * 					所以你可以像上面说的那样写成throw Throwables.propagate(t)，Java编译器会意识到这行代码保证抛出异常。
	 * void propagateIfInstanceOf( Throwable, Class<X extends   Exception>) throws X	Throwable类型为X才抛出
	 * void propagateIfPossible( Throwable)	Throwable类型为Error或RuntimeException才抛出
	 * void   propagateIfPossible( Throwable, Class<X extends Throwable>) throws X	Throwable类型为X, Error或RuntimeException才抛出
	 */
	@Test
	public void test05(){
		
	}
	
	
	
	
	
	
	
	
	
	
}
