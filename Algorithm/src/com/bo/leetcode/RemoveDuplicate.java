package com.bo.leetcode;

import java.util.Arrays;

import com.bo.sort.Count;

//http://www.acmerblog.com/leetcode-solution-remove-duplicates-from-sorted-array-6309.html
//http://www.acmerblog.com/leetcode-solution-remove-duplicates-from-sorted-array-ii-6238.html
//http://www.acmerblog.com/remove-duplicate-character-5906.html
public class RemoveDuplicate {

	
	/**
	 * 删除排序数组中的重复数字
	 * 调整排序数组中元素位置 使不重复的换到前面来
	 * 返回调整后的不重复数组, 或者不重复元素的长度然后自取
	 */
	public static int RemoveFromArray(int[] data){
		if (data.length < 1) {
			return -1;
		}
		int index = 0;
		for (int i = 1; i < data.length; i++) {
			if (data[i] != data[index]) {
				index ++;
				data[index] = data[i];
			}
		}
		
		return index+1;
	}
	
	/**
	 * 允许最多出现两次，多于两次的去掉
	 * 如果没有排序 需要用hashmap
	 */
	public static int RemoveMoreThanTwice(int[] data){
		//长度为2允许
		if (data.length < 3) {
			return data.length;
		}
		int index = 0;
		int count_index = 0;
		for (int i = 1; i < data.length; i++) {
			if(data[i] == data[i-1]){
				count_index ++;
			}else
				count_index = 1;
			data[index] = data[i];
			if (count_index <= 2) {
				index++;
			}
		}
		return index;
	}
	
	public static int RemoveMoreThanTwice2(int[] data){
		if (data.length <= 2) return data.length;

        int index = 2;
        for (int i = 2; i < data.length; i++){
            if (data[i] != data[index - 2])
                data[index++] = data[i];
        }

        return index;
	}
	
	public static int RemoveMoreThanTwice3(int[] data){
		if (data.length <= 2) return data.length;
		int index = 0;
        for (int i = 0; i < data.length; ++i) {
            if (i > 0 && i < data.length - 1 && data[i] == data[i - 1] && data[i] == data[i + 1])
                continue;

            data[index++] = data[i];
        }
        return index;
	}
	
	/**
	 * 删除重复字符 O(n^2)
	 */
	public static void Remove(String str){
		char[] ch = str.toCharArray();
		int j = 0;
		//j用作标志符
		for (int i = 0; i < ch.length; i++) {
			if (ch[j] != 0) {
				ch[j++] = ch[i];
				for (int k = i+1; k < ch.length; k++) {
					if (ch[k] == ch[i]) {
						ch[k] = 0;
					}
				}
			}
		}
		ch[j] = 0;
		System.out.println(String.valueOf(ch));
	}
	
	
	/**
	 * 和上面一样 但是差在哪里还要分析分析
	 */
	public static void Remove2(String str){
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char temp = ch[i];
			for (int j = i+1; j < ch.length; j++) {
				if(ch[j] == ch[i] && ch[j] != 0){
					ch[j] = 0;
				}
			}
		}
		System.out.println(String.valueOf(ch));
	}
	
	/**
	 * 哈希记录出现 遍历到的字符出现过则说明重复
	 */
	public static void Remove3(String str){
		char[] ch = str.toCharArray();
		boolean[] h = new boolean[256];
		for (int i = 0; i < ch.length; i++) {
			if (h[(int) ch[i]] == true) {
				ch[i] = 0;
			}else
				h[(int) ch[i]] = true;
		}
		System.out.println(String.valueOf(ch));
	}
	
	/**
	 * 如果确定了字符集是a-z, 那么用一个int变量的每一位共有32位可以表示26个字母的出现
	 * 可以在O(n)时间内移除切不用额外空间
	 */
	public static void Remove4(String str){
		char[] ch = str.toCharArray();
		int inthash = 0;
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] != 0) {
				int bit = 1 << (int) (ch[i] - 'a');
				if ((inthash & bit) == 0) {
					//first time show up
					inthash = inthash | bit;
				}else
					ch[i] = 0;
			}
		}
		System.out.println(String.valueOf(ch));
		
	}
	
	public static void main(String[] args) {
//		String string = "aaabcdafa";
//		Remove4(string);
		
//		int[] arr = {1,1,2,2,3,4,4,5,7,7,9};
//		RemoveFromArray(arr);
		
		int[] arr = {1,1,1,1,2,2,3,4,4,4,5,5,7,7,7,9};
		RemoveMoreThanTwice(arr);
	}
}
