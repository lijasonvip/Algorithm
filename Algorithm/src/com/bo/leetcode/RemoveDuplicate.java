package com.bo.leetcode;

//http://www.acmerblog.com/remove-duplicate-character-5906.html
public class RemoveDuplicate {

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
		String string = "aaabcdafa";
		Remove4(string);
	}
}
