package com.bo.offer;

public class Reverse {

	//offer 42
	public static void Reverse(char[] ch, int start, int end){
		if (ch == null || start > end || start < 0) {
			return;
		}
		while(start < end){
			char temp = ch[start];
			ch[start] = ch[end];
			ch[end] = temp;
			
			start ++;
			end --;
		}
	}
	
	/**
	 * @param sentence
	 * @return
	 * 翻转单词的顺序 但是不翻转单词内部字母的顺序
	 * 两次翻转实现
	 */
	public static String ReverseSentence(String sentence){
		char[] ch = sentence.toCharArray();
		
		Reverse(ch,0,ch.length-1);
		int start = 0, end = 0;
		while(start < ch.length){
			if(ch[start] == ' '){
				start++;
				end ++;
			}else if (ch[end] == ' ' || end == ch.length-1) {
				Reverse(ch,start,end-1);
				start = end;
				//注意这里要跳出
				if(end == ch.length - 1)
					break;
			}else {
				end++;
			}
			
		}
		
		return String.valueOf(ch);
		
	}
	
	// offer 42 扩展
	
	public static String LeftRotateString(String data, int loc){
		if(data == null || loc < 0 || loc > data.length()-1)
			return null;
		char[] ch = data.toCharArray();
		Reverse(ch,0,loc-1);
		Reverse(ch,loc,ch.length-1);
		Reverse(ch,0,ch.length-1);
		return String.valueOf(ch);
	}
	
	public static void main(String[] args) {
		System.out.println(ReverseSentence("I am a student"));
		System.out.println(LeftRotateString("abcdefg", 2));
	}
}
