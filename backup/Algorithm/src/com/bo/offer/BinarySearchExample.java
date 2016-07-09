package com.bo.offer;

import java.awt.RenderingHints.Key;

//http://www.cnblogs.com/ider/archive/2012/04/01/binary_search.html
/**
 * @author bo
 *
 */
public class BinarySearchExample {
	
	/**
	 * @param data
	 * @param low
	 * @param high
	 * @param key
	 * @return return the position of key. if non find return -1 or how many small than key
	 * divide and conquer, so we recursively solve. because it's also a tail recursion, so iteration without stack could help too
	 */
	public static int BinarySearch_Recursion(int[] data, int low, int high, int key){
		if(low > high)
			return low;
		else{
			int mid = (low + high) / 2;
			if(data[mid] < key)
				return BinarySearch_Recursion(data, mid+1, high, key);
			else if(data[mid] > key)
				return BinarySearch_Recursion(data, low, mid-1, key);
			else
				return mid;
		}
	}
	
	/**
	 * @param data
	 * @param key
	 * @return
	 * tail recursion problem don't need the prior information. so that no stack iteration can solve this problem
	 */
	public static int BinarySearch_Iteration(int[] data, int low, int high, int key){
		while(low <= high){
			int mid = (low + high) / 2;
			if (data[mid] > key) {
				high = mid - 1;
			}else if (data[mid] < key) {
				low = mid + 1;
			}else {
				return mid;
			}
		}
		return low;
	}

	//use BinarySearch to find upper bound which is the first one larger than the target
	
	/**
	 * @param data
	 * @param low
	 * @param high
	 * @param key
	 * @return
	 * update the low and mid not mid - 1 but mid because mid could be the target position
	 */
	public static int BinarySearch_UpperBound(int[] data, int low, int high, int key) {
		if(low > high || data[high] <= key)
			return -1;
		int mid = (low + high) / 2;
		while(low < high){
			
			if(data[mid] > key)
				high = mid;
			else
				low =  mid+1;
			mid = (low + high) / 2;
		}
		return mid;
	}
	
	public static int BinarySearch_LowerBound(int[] data, int low, int high, int key){
		if(low > high || data[low] > key)
			return -1;
		int mid = (low + high + 1) / 2;
		while(low < high){
			if(data[mid] < key){
				low = mid;
			}else{
				high = mid - 1;
			}
			mid = (low + high + 1) / 2;
		}
		return mid;
	}
	
	/**
	 * 之前我们使用二分查找法时，都是基于数组中的元素各不相同。假如存在重复数据，而数组依然有序，
	 * 那么我们还是可以用二分查找法判别目标数是否存在。不过，返回的index就只能是随机的重复数据中的某一个。
	 * 此时，我们会希望知道有多少个目标数存在。或者说我们希望数组的区域。
	 * 结合前面的界限查找，我们只要找到目标数的严格上届和严格下届，那么界限之间（不包括界限）的数据就是目标数的区域了
	 */
	public static int[] SearchRange(int[] data, int key){
		int[] range = {-1,-1};
		int lower = BinarySearch_LowerBound(data, 0, data.length-1, key);
		lower = lower + 1;
		if(data[lower] == key)
			range[0] = lower;
		else
			return range;
		int upper = BinarySearch_UpperBound(data, 0, data.length-1, key);
		upper = upper < 0 ? (data.length - 1) : (upper - 1);
		range[1] = upper;
		
		return range;
	}
	
	//rotate sorted array
	
	/**
	 * @param data
	 * @param low
	 * @param high
	 * @param target
	 * @return
	 * 之前我们说过二分法是要应用在有序的数组上，如果是无序的，那么比较和二分就没有意义了。
	 * 不过还有一种特殊的数组上也同样可以应用，那就是“轮转后的有序数组（Rotated Sorted Array）”。
	 * 它是有序数组，取期中某一个数为轴，将其之前的所有数都轮转到数组的末尾所得
	 * 比如{7, 11, 13, 17, 2, 3, 5}就是一个轮转后的有序数组。
	 * 非严格意义上讲，有序数组也属于轮转后的有序数组——我们取首元素作为轴进行轮转。
	 */
	public static int RotateSorted(int[] data, int low, int high, int target){
		while(low <= high){
			int mid = (low + high) / 2;
	        if (target < data[mid])
	            if (data[mid] < data[high])//the higher part is sorted
	                high = mid - 1; //the target would only be in lower part
	            else //the lower part is sorted
	                if(target < data[low])//the target is less than all elements in low part
	                    low = mid + 1;
	                else
	                    high = mid - 1;

	        else if(data[mid] < target)
	            if (data[low] < data[mid])// the lower part is sorted
	                low = mid + 1; //the target would only be in higher part
	            else //the higher part is sorted
	               if (data[high] < target)//the target is larger than all elements in higher part
	                    high = mid - 1;
	                else
	                    low = mid + 1;
	        else //if(array[mid] == target)
	            return mid;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] data = {2,3,4,5,15,19,26,27,36,38,44,46,47,48,50};
		System.out.println(RotateSorted(data, 0, data.length-1, 5));
	}

}
