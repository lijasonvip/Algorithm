package com.bo.leetcode;

public class ReverseSentence {
	public static void main(String[] args) {
		char[] c = { 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'r', 'a', 'c', 't', 'i',
				'c', 'e' };
		c = reverse(c);
		for (char i : c) {
			System.out.print(i + " ");
		}
	}

	public static char[] reverse(char[] input) {
		int len = input.length;

		char[] output = new char[len];

		int previousInputSavepoint = 0;
		int previousOutputSavepoint = len;
		boolean continueCopy = false;

		for (int idx = 0; idx < len; idx++) {
			if (Character.isWhitespace(input[idx]) || continueCopy) {
				int effectiveLength = idx - previousInputSavepoint;
				System.arraycopy(input, previousInputSavepoint, output, (previousOutputSavepoint - effectiveLength),
						effectiveLength);
				previousInputSavepoint = idx;
				previousOutputSavepoint -= effectiveLength;
				continueCopy = true;
			}

			if (Character.isLetter(input[idx]) && continueCopy)
				continueCopy = false;
		}

		System.arraycopy(input, previousInputSavepoint, output, 0, len - previousInputSavepoint);
		return output;
	}
}