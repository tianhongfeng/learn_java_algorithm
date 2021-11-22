import com.sun.xml.internal.bind.v2.TODO;

import javax.xml.transform.Source;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 静态导包
 */
import static java.lang.System.out;

public class testArray {

    public void setZeroes(int[][] matrix) {
//        int[][] matrix_tmp = new int[matrix.length][matrix[0].length];
//        for(int i = 0;i < matrix.length;i++){
//            matrix_tmp[i] = matrix[i].clone();
//        }
//        for(int i = 0; i < matrix.length; i++){
//            for(int j = 0; j < matrix[0].length; j++){
//                if(matrix_tmp[i][j] == 0){
//                    int m = matrix.length - 1;
//                    int n = matrix[0].length - 1;
//                    while(m >= 0){
//                        matrix[m][j] = 0;
//                        m--;
//                    }
//                    while(n >= 0){
//                        matrix[i][n] = 0;
//                        n--;
//                    }
//                }
//            }
//        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0) row[i] = col[j] = true;
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(row[i] || col[j]) matrix[i][j] = 0;
            }
        }
    }

    public int pivotIndex(int[] nums) {
        int[] sum = new int[nums.length + 1];
        for(int i = 1; i < sum.length; i++){
            for(int j = 0; j <= i - 1; j++){
                sum[i] += nums[j];
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(i == 0 || i == nums.length - 1){
                if(0 == sum[sum.length - 1] - nums[i]) return i;
            }else{
                if(sum[i] == sum[sum.length - 1] - sum[i] - nums[i]) return i;
            }
        }
        return -1;
    }

    public int reverseBits(int n) {

        int sum = 0;
        for(int i = 0; i < 32; i++){
            sum = (sum << 1) | (n & 1);
            n >>>=  1;
        }
        return sum;
    }


    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111


    // 分治思想 想要保留哪位就把哪位取 1，然后与原数相与
    public int reverseBits1(int n) {
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    int l = 0;
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public int searchInsert1(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int left = 0;
        // 因为有可能数组的最后一个元素的位置的下一个是我们要找的，故右边界是 len
        int right = len;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 小于 target 的元素一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索的区间是 [mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索的区间是 [left, mid]
                right = mid;
            }
        }
        return left;
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 按照给定的Comparator排序, 排完顺序位升序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m*n - 1;
        for(int i = 0; i < m*n; i++){
            while(left <= right){
                int mid = ((right - left) >> 1) + left;
                if(matrix[mid / n][mid % n] < target){
                    left = mid + 1;
                }else if(matrix[mid / n][mid % n] == target){
                    return true;
                }else{
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ll = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < Math.pow(2, n); i++){
            List<Integer> l = new ArrayList<>();
            for(int j = 0; j < n; j++){
                if((i & (1 << j)) != 0){
                    l.add(nums[j]);
                }
            }
            ll.add(l);
        }
        return ll;




    }

    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1, -1};
        int[] ss = new int[2];
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        if(nums[left] == target){
            ss[0] = left;
            int left1 = 0;
            int right1 = nums.length - 1;
            while (left1 < right1){
                int mid = left1 + (right1 - left1)/2;
                if(nums[mid] > target){
                    right1 = mid;
                }else{
                    left1 = mid + 1;
                }
            }
            if(nums[left1] == target){
                ss[1] = left1;
            }else{
                ss[1] = left1 - 1;
            }
            return ss;
        }else{
            return new int[]{-1, -1};
        }
    }


    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0) return new int[][]{{newInterval[0], newInterval[1]}};
        List<int[]> ll = new ArrayList<>();
        int[][] newintervals = Arrays.copyOf(intervals, intervals.length + 1);
        newintervals[intervals.length] = newInterval;
        Arrays.sort(newintervals, new Comparator<int[]>() {
            public int compare(int[] newintervals1, int[] newintervals2) {
                return newintervals1[0] - newintervals2[0];
            }
        });
        for(int i = 0; i < newintervals.length; i++){
            if(i == 0){
                ll.add(newintervals[0]);
            }else{
                if(ll.get(ll.size() - 1)[1] <= newintervals[i][0]){
                    ll.add(newintervals[i]);
                }else{
                    ll.get(ll.size() - 1)[1] = Math.max(newintervals[i - 1][1], newintervals[i][1]);
                }
            }
        }
        return ll.toArray(new int[ll.size()][]);
    }

    public int[][] insert1(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        return ansList.toArray(new int[ansList.size()][]);
    }

    public boolean search1(int[] nums, int target) {
        if(nums.length == 0) return false;
        if(nums.length == 1) return nums[0] == target? true:false;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return true;
            // 如何 左右 边界值相等，则把左边界加一，直至左右边界不等
            if(nums[left] == nums[mid]){
                left ++;
                continue;
            }
            if(nums[mid] == target){
                return true;
            }else if(nums[left] < nums[mid]){
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(nums[right] >= target && nums[mid] < target){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 判断是否为不旋转的数组
        if(nums[right] > nums[0]) return nums[0];
        while(left < right){
            int mid = left + (right - left)/2;
            // 判断
            if(nums[mid] > nums[mid + 1]) return nums[mid + 1];

            if(nums[left] > nums[mid]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return nums[left];
    }

    public int findMin1(int[] nums) {
        if(nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        if(nums[right] > nums[left]) return nums[0];
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] > nums[mid + 1]) return nums[mid+1];

//            if(nums[mid] == nums[left]){
//                left++;
//                continue;
//            }
            if(nums[left] > nums[mid]){
                right = mid;
            }else if(nums[left] > nums[mid]){
                left = mid + 1;
            }else{
                left++;
            }
        }
        return nums[left];
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 双指针 在左边界
        int p = 0;
        int q = 0;
        int[] ss = new int[m+n];
        int i = 0;
        while(p < m && q < n){
            if(nums1[p] <= nums2[q]){
                ss[i++] = nums1[p];
                p++;
            }else{
                ss[i++] = nums2[q];
                q++;
            }
        }
        while (p < m) ss[i++] = nums1[p++];
        while (q < m) ss[i++] = nums1[q++];
        for(int j = 0; j < m+n; j++){
            nums1[j] = ss[j];
        }
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        // 双指针 在右边界
        int p = nums1.length - 1;
        int q = nums2.length - 1;
        int s = m - 1;
        while (p >= 0 && q >= 0){
            if(s == -1){
                nums1[p] = nums2[q];
                p--;
                q--;
            }else{
                if(nums1[s] <= nums2[q]){
                    nums1[p] = nums2[q];
                    p--;
                    q--;
                }else{
                    nums1[p] = nums1[s];
                    p--;
                    s--;
                }
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 双指针 在右边界
        int p = m - 1;
        int q = n - 1;
        int s = m + n - 1;
        while (p >= 0 && q >= 0){
            if(nums1[p] <= nums2[q]){
                nums1[s--] = nums2[q--];
            }else{
                nums1[s--] = nums1[p--];
            }
        }
        while (q >= 0) nums1[s--] = nums2[q--];
    }

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    public int removeDuplicates1(int[] nums) {
        if(nums.length == 1) return 1;
        int i = 0;
        int s = 0;
        for(int j = 1; j < nums.length; j++){
            if(nums[j] != nums[i]){
                s = 0;
                nums[++i] = nums[j];
            }else{
                if(s < 1){
                    i++;
                }
                s++;
            }
        }
        return i+1;
    }


//    public int mySqrt(int x) {
//        if(x == 1) return 1;
//        int left = 1;
//        int right = x - 1;
//        while(left < right){
//            int mid = left + (right - left)/2;
//            if(Math.pow(mid, 2) == x){
//                return mid;
//            } else if(Math.pow(mid, 2) > x){
//                right = mid;
//            }else{
//                left = mid + 1;
//            }
//        }
//        return left - 1;
//    }

    public int mySqrt1(int x) {
        int left = 0;
        int right = x;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(Math.pow(mid, 2) == x){
                return mid;
            } else if(Math.pow(mid, 2) > x){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left - 1;
    }

    public boolean isPerfectSquare(int num) {
        if(num < 2) return true;
        long left = 2, right = num / 2, mid;
        while(left <= right){
            mid = left + (right - left)/2;
            if(mid * mid == num){
                return true;
            }else if(mid * mid > num){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return false;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length;
        while(left < right){
            int mid = left + (right - left)/2;
            if(letters[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
//        if(left == letters.length) return letters[0];
        return letters[left % letters.length];
    }

    public double myPow(double x, int n) {
        return n >= 0 ? mul(x,  n):1.0 / mul(x, n);
    }

    public double mul(double x, int n){
        if(n == 0) return 1.0;
        double s = mul(x, n/2);
        if(n % 2 == 0){
            return s * s;
        }else{
            return x * s * s;
        }
    }


    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    public int[] intersection1(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (int target : nums1) {
            if (binarySearch(nums2, target)) {
                set.add(target);
            }
        }
        int index = 0;
        int[] res = new int[set.size()];
        for (int num : set) {
            res[index++] = num;
        }
        return res;
    }
    public boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return false;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect2(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int mid = 0;
        int right = arr.length - 1;
        // 1.先找到第一个大于等于x的值
        while(left < right){
            mid = left + (right - left)/2;
            if(arr[mid] < x){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        int p = left - 1;
        int q = left;
        int nums = 0;
        // 2.然后判断 p, q 分别与到x的距离
        while(p >= 0 && q < arr.length && nums < k){
            if(x - arr[p] <= arr[q] - x){
                p--;
            }else{
                q++;
            }
            nums++;
        }

        // 用于判断 p, q 是否越界
        boolean fp = false;
        boolean fq = false;
        if(p < 0) fp = true;
        if(p > arr.length - 1) fq = true;

        // 3.如果p或q,某一个越界，而num<k,则需要移动不越界的那个指针直至num>K为止
        while(p >= 0 && nums < k){
            p--;
            nums++;
        }

        while(q < arr.length && nums < k){
            q++;
            nums++;
        }

        List<Integer> ll = new ArrayList<>();

        if(fq == true){// 如果q越界，则减一
            q--;
        }else if(fp == true){// 如果p越界，则加一
            p++;
        }else{
            p++;
        }
        for(int i = p; i < q; i++){
            ll.add(arr[i]);
        }
        return ll;
    }

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int lengthOfLIS(int[] nums) {
        /**
         * 1.定义ss[i]为考虑前i个元素，以第i个数字结尾的最长上升子序列的长度。
         * 2.我们从小到大计算ss数组的值，在计算 ss[i] 之前，我们已经计算出dp[0…i−1] 的值，则状态转移方程为：
         * ss[i] = max(ss[i], ss[j] + 1), 其中 0 <=j < i,且 num[j] < num[i]
         * 3.最后，整个数组的最长上升子序列即所有dp[i]中的最大值。
         *
         */

        int[] ss = new int[nums.length];
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] <= nums[i]){
                    ss[i] = Math.max(ss[i], ss[j] + 1);
                }
            }
            result = Math.max(ss[i], result);
        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        // 通过hashmap来判断每次target-nums[i]的值是否在存在表里
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public void moveZeroes(int[] nums) {
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0) nums[j++] = nums[i];
        }
        for(int m = j; m < nums.length; m++){
            nums[m] = 0;
        }
        return;
    }

    public int removeElement(int[] nums, int val) {
        int p = 0;
        int q = nums.length - 1;
        while(p < q){
            if(nums[p] == val){
                if(nums[q] != val) {
                    nums[p++] = nums[q--];
                }else {
                    q--;
                }
            }else{
                p++;
            }
        }
        return p + 1;
    }

    public boolean isPalindrome(ListNode head) {
//        List<Integer> ll = new ArrayList<>();
//        while(head != null){
//            ll.add(head.val);
//            head = head.next;
//        }
//        int l = 0;
//        int r = ll.size() - 1;
//        while(l < r){
//            if(ll.get(l) == ll.get(r)) {
//                l++;
//                r--;
//                continue;
//            }else{
//                return false;
//            }
//
//        }
//        return true;
        int sum = 0;
        while(head != null){
            if(head.val == head.next.val){

            }else{
                sum += head.val;
            }
            head = head.next;
        }
        if(sum != 0) return false;
        return true;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3) return new ArrayList<>();
        // 1.先按照升序排序
        Arrays.sort(nums);
        List<List<Integer>> ll = new ArrayList<>();
        //2.循环数组 确定 a 值
        for(int i = 0; i < nums.length; i++){
            // 重复的a值要跳过
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int target = -nums[i];
            // 3. 用双指针找 target = b + c = 0 - a, 就相当于两数之和
            // 定义两个指针 p,q
            int p = i + 1;
            int q = nums.length - 1;

            while(p < q ){
                if((nums[p] + nums[q]) > target){
                    q--;
                }else if((nums[p] + nums[q]) < target){
                    p++;
                }else{
                    ll.add(new ArrayList<>(Arrays.asList(nums[i], nums[p], nums[q])));
                    p++;
                    q--;
                    // 排除重复的值
                    while (p < q && nums[p - 1] == nums[p]) p++;
                    while (p < q && nums[q] == nums[q + 1]) q--;
                }
            }
        }
        return ll;
    }

    /**
     * 双指针
     * 就是这两句话：“若向内移动短板，水槽的短板 min(h[i], h[j])可能变大，因此水槽面积 S(i, j)可能增大。
     * 若向内移动长板，水槽的短板 min(h[i], h[j])不变或变小，下个水槽的面积一定小于当前水槽面积。“
     * 其实可以加一句，无论是移动短板或者长板，我们都只关注移动后的新短板会不会变长，而每次移动的木板都只有三种情况，
     * 比原短板短，比原短板长，与原短板相等；如向内移动长板，对于新的木板：1.比原短板短，则新短板更短。2.与原短板相等或者比原短板长，则新短板不变。
     * 所以，向内移动长板，一定不能使新短板变长。
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int area = 0;
        for(int i = 0; i < height.length; i++){
            for(int j = i + 1; j < height.length; j++){
                area = Math.max(area, (j - i + 1) * Math.min(height[i], height[j]));
            }
        }
        return area;
    }



    int minn=Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount){
        backtrack(amount,0,coins,0);
        return minn;
    }

    public void backtrack(int cost,int idx,int coins[],int num){
        if(cost==0) {
            minn=Integer.min(minn,num);
            return;
        }
        if(idx==coins.length)
            return;
        backtrack(cost,idx+1,coins,num);
        if(cost>=coins[idx])
            backtrack(cost-coins[idx],idx,coins,num+1);
        return;
    }


//    public int coinChange(int[] coins, int amount){
//        int result = backtrack(amount, coins);
//        return result == Integer.MAX_VALUE? -1:result;
//    }

//    public int backtrack(int amount, int coins[]){
//        int minn=Integer.MAX_VALUE;
//        if(amount == 0) return 0;
//        if(amount < 0) return -1;
//        for(int i:coins){
//            if(amount < i) continue;
//            int sub = backtrack(amount - i, coins);
//            if(sub == -1) continue;
//            minn = Math.min(sub + 1, minn);
//        }
//        return minn == Integer.MAX_VALUE? -1:minn;
//    }


//    /**
//     * dp[amount] = min(dp[amount - coins[0]] + dp[amount - coins[1]] + dp[amount - coins[2]])
//     * @param coins
//     * @param amount
//     * @return
//     */
//    public int coinChange(int[] coins, int amount){
//        // 用于存储已经算过的值
//        int[] dp = new int[amount + 1];
//        return backtrack(amount, coins, dp);
//    }
//
//    public int backtrack(int amount, int coins[], int[] dp){
//        int minn=Integer.MAX_VALUE;
//        if(amount == 0) return 0;
//        //如果当前amount已经算过了，则直接取出来
//        if(dp[amount] != 0) return dp[amount];
//        // 循环每个硬币的值，找到最小硬币数量
//        for(int i:coins){
//            // 当前值小于硬币的值，则跳过
//            if(amount < i) continue;
//            int sub = backtrack(amount - i, coins, dp);
//            if(sub == -1) continue;
//            minn = Math.min(sub + 1, minn);
//        }
//        // 返回当前amount的硬币数，如果minn的初值，则说明凑不成，返回-1
//        return dp[amount] = minn == Integer.MAX_VALUE? -1:minn;
//    }


    /**
     * dp[0] = 0
     * dp[i] = min(dp[i] + dp[amount - i] + 1)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < amount + 1; i++){
            for(int j:coins){
                if(amount < j) continue;
                dp[i] = Math.min(dp[i], 1 + dp[amount - j]);
            }
        }
        return dp[amount] == amount + 1? -1:dp[amount];
    }

    public List<List<Integer>> permute(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        List<List<Integer>> ll = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        boolean[] ss = new boolean[nums.length];
        back_track(nums, ll, l, ss);
        return ll;
    }

    public void back_track(int[] nums, List<List<Integer>> ll, List<Integer> l, boolean[] ss){
        if(l.size() == nums.length){
            // 错误写法 ll.add(l) 在 Java 中，参数传递是值传递，对象类型变量在传参的过程中，复制的是变量的地址，所以这里需要我们在做一次复制
            ll.add(new ArrayList<>(l));
            return ;
        }
        for(int i = 0; i < nums.length; i++){
            if(!ss[i]){
                l.add(nums[i]);
                ss[i] = true;
                back_track(nums, ll, l, ss);
                // 找到符合的解后，向上回退，回到前一个状态
                ss[i] = false;
                l.remove(l.size() - 1);
            }
        }
        return ;
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ll = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        int j = 0;
        // 运用剪枝需要保证数组有序
        Arrays.sort(candidates);
        back_search(ll, l, candidates, target, j);
        return ll;

    }


    public void back_search(List<List<Integer>> ll, List<Integer> l, int[] candidates, int target, int j){
        if(0 == target){
            ll.add(new ArrayList<>(l));
            return;
        }

        for(int i = j; i < candidates.length; i++){
            // 判断当前值减去候选数组中的值是否负
            if(target - candidates[i] < 0) break;
            l.add(candidates[i]);
            back_search(ll, l, candidates, target - candidates[i], i);
        }
    }


    public int singleNumber(int[] nums) {


        if(nums.length == 0) return 0;
        Map<Integer, Integer> ss = new HashMap<>();
        for(int i:nums){
            if(!ss.containsKey(i)){
                ss.put(i, ss.getOrDefault(i, 1));
            }else{
                ss.put(i, ss.get(i) + 1);
            }
        }
        for(int i:ss.keySet()){
            if(ss.get(i) == 1) return i;
        }

        return -1;
    }

    public void nextPermutation(int[] nums) {
        // 1.从后往前遍历找到第一个 a[i] < a[i+1] 的数对
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 2.如果可以找到这个数对，则再次寻找a[i+1] 往后的第一个比a[i]的数
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            //3.找到后把a[i] a[j] 互换位置
            swap(nums, i, j);
        }
        //4.重新排序a[i] 后面的数的顺序，以保证其最小
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void rotate(int[][] matrix){
        int n = matrix.length;
        // 水平翻转
        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < n; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }

        //对角线翻转
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> ss = new HashMap<>();
        for(int i:nums){
            if(ss.containsKey(i)){
                ss.put(i, ss.get(i) + 1);
            }else{
                ss.put(i, 1);
            }
        }
        int max = -1;
        int majority = -1;
        for(int i:ss.keySet()){
            if(ss.get(i) > max){
                max = ss.get(i);
                majority = i;
            }
        }
        return majority;
    }

    public int majorityElement1(int[] nums) {
        int cand_num = nums[0], count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (cand_num == nums[i])
                ++count;
            else if (--count == 0) {
                cand_num = nums[i];
                count = 1;
            }
        }
        return cand_num;
    }


    /**
     * 1. 以第i节点为结束节点来遍历数组
     * 2. 状态转移方程 f(i) = max(f(i - 1) + nums[i], nums[i])
     * 3. f(i)表示以i为结尾得最大子序和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // pre 表示
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    /**
     * 1.通过数组下标来确定哪个值是否出现
     * 2.
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //1.首先遍历数组中的值，把对应值作为数组得下标，改变这个下标所对应的数组中的值（乘以-1），说明当前遍历得值存在。
        for(int i:nums){
            if(nums[Math.abs(i) - 1] > 0){
                nums[Math.abs(i) - 1] = nums[Math.abs(i) - 1]*(-1);
            }
        }
        List<Integer> ll = new ArrayList<>();

        //2.再一次遍历数组，数组中大于0的值的下标则为缺少的值。
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) ll.add(i+1);
        }
        return ll;
    }

    public int lengthOfLIS1(int[] nums) {
        int[] ss = new int[nums.length];
        for(int i = 0; i < ss.length; i++){
            ss[i] = 1;
        }
        int maxlength = -1;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    ss[i] = Math.max(ss[i], ss[j] + 1);
                    maxlength = Math.max(maxlength, ss[i]);
                }
            }
        }
        return maxlength;
    }

    public int climbStairs(int n) {
        if(n == 1) return 1;
        int a1 = 1;
        int a2 = 1;
        for(int i = 2; i <= n; i++){
            int aa = a1 + a2;
            a1 = a2;
            a2 = aa;
        }
        return a2;
    }

    public static boolean canJump(int[] nums) {

        if (nums == null) {
            return false;
        }
        //前n-1个元素能够跳到的最远距离
        int k = 0;
        for (int i = 0; i <= k; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            k = Math.max(k, temp);
            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (k >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变,且没有到末尾元素
        return false;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[i].length - 1;
        while(i < matrix.length && j >= 0){
            if(matrix[i][j] > target){
                j--;
            }else if(matrix[i][j] < target){
                i++;
            }else{
                return true;
            }
        }
        return false;
    }



    public int jump(int[] nums) {
        int ans = 0;
        int start = 0;
        int end = 1;
        while (end < nums.length)
        {
            int maxPos = 0;
            for (int i = start; i < end; i++)
            {
                // 能跳到最远的距离
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            start = end;      // 下一次起跳点范围开始的格子
            end = maxPos + 1; // 下一次起跳点范围结束的格子
            ans++;            // 跳跃次数
        }
        return ans;
    }


    public int jump1(int[] nums) {
        int ans = 0;
        int max_far = 0;
        int end = 0;
        for(int i =0; i < nums.length - 1; i++){
            max_far = Math.max(max_far, i + nums[i]);;
            if(i == end){
                end = max_far;
                ans++;
            }
        }
        return ans;
    }


    public int shipWithinDays(int[] weights, int D) {
        // 1.求出运送所有货物所需的 最小和最大运载能力
        int left = 0;
        int right = 0;
        for(int i = 0; i < weights.length; i++){
            left = Math.max(left, weights[i]);
            right += weights[i];
        }

        //2.然后利用二分查找 找到满足所需天数的最小运载能力
        while(left < right){
            int mid = left + (right - left)/2;
            int cur = 0;
            int ans = 1;
            for(int i:weights){
                if(cur + i > mid){
                    ans++;
                    cur = 0;
                }
                cur += i;
            }

            if(ans <= D){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    char[][] board = null;
    String word = null;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                return help(0, i, j);
            }
        }
        return false;

    }

    public boolean help(int index, int i, int j){

        // 把超越边界的和不等于给定字符串值得效果剔除掉
        if(i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || board[i][j] != word.charAt(index)){
            return false;
        }

        // 如果index 为word的长度，说明以经找到了
        if(index == word.length() - 1) return true;

        // 把当前的值赋值给 访问标记，防止下一次在遍历到s
        board[i][j] = '.';
        index++;

        // 分别遍历当前位置的上下左右
        boolean res = help(index, i + 1, j) || help(index, i, j + 1) || help(index, i - 1, j) || help(index, i, j - 1);

        // 把原来的值还原回去
        board[i][j] = word.charAt(index - 1);

        return res;

    }

    public int fib(int n) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    public int minArray(int[] numbers) {
        int right = numbers.length;
        if(right == 0) return -1;
        if(right == 1) return numbers[0];
        if(numbers[0] < numbers[right - 1]) return numbers[0];
        int left = 0;
        right -= 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(numbers[mid] < numbers[right]){
                right = mid;
            }else if(numbers[mid] > numbers[right]){
                left = mid + 1;
            }else {
                right--;
            }
        }
        return numbers[left];
    }


    int m = 0;
    int n = 0;
    int k = 0;
    int count = 0;
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;

        boolean[][] visted = new boolean[m][n];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{0, 0});
        int count = 0;

        // 广度优先搜索
        while(!que.isEmpty()){
            int[] ss = que.poll();
            if(ss[0] >= m || ss[1] >= n || visted[ss[0]][ss[1]]) continue;
            int p = ss[0];
            int q = ss[1];
            int sum = 0;
            while(p != 0){
                sum += p % 10;
                p = p / 10;
            }
            while(q != 0){
                sum += q % 10;
                q = q / 10;
            }
            if(sum > k) continue;
            visted[ss[0]][ss[1]] = true;
            count ++;
            que.add(new int[]{ss[0] + 1, ss[1]});
            que.add(new int[]{ss[0], ss[1] + 1});
        }

        return count;
//
//
//
//        // 防止重复记录
//        int[][] ss = new int[m][n];
//        help1(0, 0, ss);
//        return count;
    }

    public void help1 (int i, int j, int[][] ss){

        int sum = 0;
        // 不符合边界
        if(i > m - 1 || j > n - 1 || ss[i][j] == 1) return;
        int p = i;
        int q = j;
        while(p != 0){
            sum += p % 10;
            p = p / 10;
        }
        while(q != 0){
            sum += q % 10;
            q = q / 10;
        }
        //
        if(sum > k) return;

        // 判断该格子是否访问过
        ss[i][j] = 1;
        count++;

        // 下，右
        help1(i + 1, j, ss);
        help1(i, j + 1, ss);
    }

    public int[] printNumbers(int n) {
        int[] ss = new int[(int)Math.pow(10, n) - 1];
        int count = 1;
        for(int i = 0; i < ss.length; i++){
            ss[i] = count;
            count++;
        }
        return ss;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0 || arr.length == 0) return new int[0];

        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num: arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for(int num: pq) {
            res[idx++] = num;
        }
        return res;
    }

    public int[] getLeastNumbers1(int[] arr, int k) {
        this.k = k;
        if(k == 0 || arr.length == 0) return new int[0];
        if(k >= arr.length) return arr;
        quickSelect(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    /**
     * 因为不要求返回的顺序 所以可以用快速选择
     * @param arr
     * @param l
     * @param r
     */
    private void quickSelect(int[] arr, int l, int r){

        int i = l;
        int j = r;

        if(k == i) return;
        // 循环
        while(i < j){
            // 右边小于基础数
            while (i < j && arr[j] >= arr[l]) j--;
            // 左边大于基础数
            while (i < j && arr[i] <= arr[l]) i++;
            swap1(arr, i, j);
        }
        swap1(arr, i, l);
        //
        if(k < i) quickSelect(arr, l, i-1);
        if(k > i) quickSelect(arr, i+1, r);
    }

    /**
     * 快速排序
     * @param arr
     * @param l
     * @param r
     */
    private void quickSort(int[] arr, int l, int r) {
        // 子数组长度为 1 时终止递归
        if (l >= r) return;
        // 哨兵划分操作（以 arr[l] 作为基准数）
        int i = l, j = r;
        while (i < j) {
            // 找到右指针指的数小于基准数
            while (i < j && arr[j] >= arr[l]) j--;
            // 找到左指针指的数大于基准数
            while (i < j && arr[i] <= arr[l]) i++;
            swap1(arr, i, j);
        }
        swap1(arr, i, l);
        // 递归左（右）子数组执行哨兵划分
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    private void swap1(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * 考虑到大整数越界问题
     *  回溯解法
     */
    StringBuilder res;
    // num 用于存储要输出的数，loop 用于循环
    char[] loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public void printNumbers1(int n) {
        this.n = n;
        res = new StringBuilder();
        dfs(0);
        return;
    }

    void dfs(int x) {
        // x == n 说明 num以经存储了符合给定位数的值了
        if(x == n) {
            if(res.length() == 0) return;
            out.println(res.toString());
            return;
        }
        for(char i : loop) {
            // 当字符串为空，并且i为0时，直接进入下一次循环
            if(res.length() == 0 && i == '0') {
                dfs(x + 1);
            }else{
                res.append(i);
                dfs(x + 1);
            }

            // 把字符串最后一位移除，回退到上一层
            if(res.length() != 0) res.deleteCharAt(res.length() - 1);
        }
    }

    public int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while(i < j){
            while(i < j && nums[i] % 2 != 0) i++;
            while(i < j && nums[j] % 2 == 0) j--;
            if(i == j) break;;
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }


    public int integerBreak(int n) {
        int[] ss = new int[n + 1];
        ss[0] = 0;
        ss[1] = 1;
        int i = 2;
        int curmax = 0;
        while(i <= n){
            for(int j = 1; j < i; j++){
                curmax = Math.max(j * (n - j), ss[n - i]);
            }
            ss[i] = curmax;
            i++;
        }
        return ss[n];
    }


    public double myPow1(double x, int n) {
//        double value = 1;
//        if(n < 0){
//            x = 1/x;
//            return help(x, -n, value);
//        }
//        return help(x, n, value);
        double res = 1.0;
        while(n != 0){
            if((n & 1) == 1) res *= x;
            x *= x;
            n >>>= 1;
        }
        return res;
    }

    public double help(double x, int n, double value){
        if(n > 0){
            value = x * help(x, n - 1, value);
        }
        return value;
    }

    public int maxSubArray2(int[] nums) {

        if(nums.length == 0) return -1;
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){

                max = Math.max(nums[i], max + nums[i]);

        }
        return max;
    }

    public int search(int[] nums, int target) {
        return helper(nums, target + 1) - helper(nums, target);
    }

    public int helper(int[] nums, int target){
        int left = 0;
        int right = nums.length;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] >= target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    public String minNumber(int[] nums) {
        String[]  ss = new String[nums.length];
        for(int i = 0; i < ss.length; i++){
            ss[i] = String.valueOf(nums[i]);
        }

        //
        Arrays.sort(ss, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                StringBuilder s1 = new StringBuilder(o1);
                s1.append(o2);
                StringBuilder s2 = new StringBuilder(o2);
                s2.append(o1);
                return s1.toString().compareTo(s2.toString());
            }
        });

        StringBuilder min = new StringBuilder();
        for(String i:ss){
            min.append(i);
        }
        return min.toString();
    }

    public int translateNum(int num) {
        String s = String.valueOf(num);

        // 0个或一个数字 为一
        int a1 = 1;
        int a2 = 1;
        for(int i = 2; i <= s.length(); i++){
            int temp = Integer.parseInt(s.substring(i - 2, i));
            if(temp >= 10 && temp <= 25){
                a1 = a1 + a2;
            }
            a2 = a1;
        }
        return a1;
    }


    public int[] singleNumbers(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i:nums){
            if(map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else {
                map.put(i, 1);
            }
        }
        // Iterating entries using a For Each loop
        int[] ss = new int[2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1){
                ss[i++] = entry.getKey();
            }
        }
        return ss;
    }


    public int maxValue(int[][] grid) {

        if(grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) continue;
                if(i == 0 && j != 0){
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                }else if(i != 0 && j == 0){
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                }else{
                    grid[i][j] = grid[i][j] + Math.max(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    public boolean isStraight(int[] nums) {
        int min = 14;
        int max = 0;
        Set<Integer> s = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) continue;
            if(s.contains(nums[i])) {
                return false;
            }else{
               s.add(nums[i]);
            }
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        return max - min < 5;
    }

    public String reverseWords(String s) {
        char[] ss = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        List<StringBuilder> list = new ArrayList<>();
        for(int i = 0; i < ss.length; i++){
            if(ss[i] == ' ') {
                if(!sb.toString().equals("")){
                    list.add(sb);
                    sb = new StringBuilder();
                }
            }else{
                sb.append(ss[i]);
            }
        }
        if(!sb.toString().equals("")) list.add(sb);

        StringBuilder result = new StringBuilder();
        for(int i = list.size() - 1; i >= 0; i--){
            result.append(list.get(i));
            if(i != 0){
                result.append(' ');
            }
        }
        return result.toString();
    }

//    public int nthUglyNumber(int n) {
//        int[] factors = {2, 3, 5};
//        Set<Long> seen = new HashSet<Long>();
//        PriorityQueue<Long> heap = new PriorityQueue<Long>();
//        seen.add(1L);
//        heap.offer(1L);
//        int ugly = 0;
//        for (int i = 0; i < n; i++) {
//            long curr = heap.poll();
//            ugly = (int) curr;
//            for (int factor : factors) {
//                if (seen.add(curr * factor)) {
//                    heap.offer(curr * factor);
//                }
//            }
//        }
//            return ugly;
//    }

    public double[] dicesProbability(int n) {

        // 二维数组 动态规划
/*        int[][] dp = new int[n + 1][6 * n + 1];
        int i = 1;
        int j = 1;
        while(j <= 6){
            dp[i][j] = 1;
            j++;
        }

        for(i = 2; i <= n; i++){
            for(j = i; j <= (6 * n); j++){
                for(int c = 1; c < 7; c++){
                    if(j - c < 0) break;
                    dp[i][j] += dp[i - 1][j - c];
                }
            }
        }


        double[] dd = new double[5 * n + 1];
        int c = 0;
        for(j = n; j <= 6 * n; j++, c++){
            dd[c] = dp[n][j] * 1.0 / Math.pow(6, n);
        }

        */

        // 一维数组动态规划
        int[] dp = new int[6 * n + 1];
        int i = 1;
        while(i <= 6){
            dp[i]= 1;
            i++;
        }
        
        for(i = 2; i <= n; i++){
            // 从后往前遍历
            for(int j = 6 * n; j >= i; j--){
                dp[j] = 0;
                for(int c = 1; c < 7; c++){
                    if(j - c < i - 1) break;
                    dp[j] += dp[j - c];
                }
            }
        }

        double[] dd = new double[5 * n + 1];
        int c = 0;
        for(i = n; i <= 6 * n; i++, c++){
            dd[c] = dp[i] * 1.0 / Math.pow(6, n);
        }
        return dd;
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        int m = matrix.length;
        if(m == 0) return new ArrayList();
        int n = matrix[0].length;
        List<Integer> ll = new ArrayList<>();
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        while(left <= right && top <= bottom){

            // 上
            for(int i = left; i <= right; i++){
                ll.add(matrix[top][i]);
            }

            // 右
            for(int i = top + 1; i <= bottom; i++){
                ll.add(matrix[i][right]);
            }

            // 保证 top与bottom相等时 不在继续访问
            if(top < bottom) {
                // 下
                for (int i = right - 1; i >= left; i--) {
                    ll.add(matrix[bottom][i]);
                }
                // 左
                for (int i = bottom - 1; i >= top + 1; i--) {
                    ll.add(matrix[i][left]);
                }
            }
            left += 1;
            top += 1;
            right -= 1;
            bottom -= 1;
        }
        return ll;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) return new int[]{};
        Deque<Integer> qq = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++){
            // 保证最大值始终在队首
            while(!qq.isEmpty() && nums[qq.peekLast()] < nums[i]){
                qq.pollLast();
            }
            qq.add(i);

            // 查看当前头部值是否超越 窗口
            if(qq.peek() <= i - k){
                qq.poll();
            }

            // 获取每个窗口的最大值
            if(i + 1 >= k){
                res[i + 1 - k] = nums[qq.peek()];
            }
        }
        return res;
    }


    int result = 0;
    public int reversePairs(int[] nums) {
        if(nums.length == 0) return result;
        dfs(nums, 0);
        return result;
    }

    public void dfs(int[] nums, int index){
        for(int i = index + 1; i < nums.length; i++){
            if(nums[i] < nums[index]){
                result++;
                dfs(nums, i);
            }
        }
    }

    int[] nums;
    int[] temp;
    int res1 = 0;
    public int[] divideAndConquerSort(int[] nums){
        this.nums = nums;
        this.temp = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        divide(left, right);
        out.println(res1);
        return nums;
    }

    // 分
    public void divide(int left, int right){
        if(left == right) return;
        int mid = left + (right - left)/2;
        divide(left, mid);
        divide(mid +1, right);
        merge(left, right, mid);
    }

    // 合并
    public void merge(int left, int right, int mid){

        // 左数组指针
        int p = left;
        // 右数组指针
        int q = mid + 1;

        // 临时数组指针
        int t = 0;

        // 复制到临时数组中
        while(p <= mid && q <= right){
            if(nums[p] <= nums[q]){
                temp[t++] = nums[p++];
            }else{
                temp[t++] = nums[q++];
                res1 += mid - p + 1;
            }
        }

        while(p <= mid){
            temp[t++] = nums[p++];
        }

        while(q <= right){
            temp[t++] = nums[q++];
        }

        t = 0;
        // 将临时数组中的元素复制回原数组
        while(left <= right){
            nums[left++] = temp[t++];
        }
    }

    public int findRepeatNumber(int[] nums) {
        if(nums.length <= 1) return -1;

        // 通过索引与值相对应，在数组原地修改
        int i = 0;
        while(i < nums.length){
            if(nums[i] == i){
                i++;
                continue;
            }

            // 如果 i索引处得值 与 nums[i]索引处得值相等，则说明已经重复了
            if(nums[nums[i]] == nums[i]) return nums[i];
            int temp = nums[nums[i]];
            nums[nums[i]] = nums[i];
            nums[i] = temp;
        }
        return -1;
//        Set<Integer> set = new HashSet<>();
//        for(int i:nums){
//            if(set.contains(i)) return i;
//            set.add(i);
//        }
    }


    public int minArray1(int[] numbers) {
        if(numbers.length == 0) return -1;
        int left = 0;
        int right = numbers.length - 1;
        if(left == right) return numbers[0];
        if(numbers[left] < numbers[right]) return numbers[left];

        while(left < right){
            int mid = left + (right - left)/2;
            if(numbers[mid] < numbers[right]){
                right = mid;
            }else if(numbers[mid] > numbers[right]){
                left = mid + 1;
            }else{
                left++;
            }
        }
        return numbers[left];
    }

    boolean result1 = false;
    int n1;
    int m1;
    char[][] board1;
    boolean[][] flag;
    String word1;
    public boolean exist1(char[][] board, String word) {
        if(word.length() == 0){
            return false;
        }
        this.n1 = board.length;
        this.m1 = board[0].length;
        this.board1 = board;
        this.flag = new boolean[n1][m1];
        this.word1 = word;
        help1(0, 0, 0);
        return result1;
    }

    public void help1(int index, int i, int j){

        // 排除不符合条件的因素
        if(i >= n1 || j >= m1 || i < 0 || j < 0 || flag[i][j] || board1[i][j] != word1.charAt(index)) return;

        if(index == word1.length() - 1){
            result1 = true;
            return;
        }

        index++;
        flag[i][j] = true;
        help1(index, i - 1, j);
        help1(index, i + 1, j);
        help1(index, i, j - 1);
        help1(index, i, j + 1);
        flag[i][j] = false;
    }


    public int[] spiralOrder1(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int[] result = new int[matrix.length * matrix[0].length];
        int index = 0;
        while(left <= right && top <= bottom){

            for(int i = left; i <= right; i++){
                result[index++] = matrix[left][i];
            }

            for(int i = top + 1; i <= bottom; i++){
                result[index++] = matrix[i][right];
            }

            if(top < bottom){
                for(int i = right - 1; i >= left; i--){
                    result[index++] = matrix[bottom][i];
                }
            }


            if(left < right){
                for(int i = bottom - 1; i >= top + 1; i--){
                    result[index++] = matrix[i][left];
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }


    public int[] SpiralMatrix (int[][] matrix) {
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int[] res = new int[matrix.length * matrix[0].length];
        int index = 0;
        while(top <= bottom && left <= right){

            // 上
            for(int i = left; i <= right; i++){
                res[index++] = matrix[left][i];

            }
            top += 1;
            // 右

            for(int i = top; i <= bottom; i++){
                res[index++] = matrix[i][right];
            }

            right -= 1;
            // 下

            for(int i = right; i >= left; i--){
                res[index++] = matrix[bottom][i];

            }

            // 左
            bottom -= 1;


            for(int i = bottom; i >= top; i--){
                res[index++] = matrix[i][left];
            }

            left += 1;
        }
        // write code here
        return res;
        // write code here
    }




//    public int majorityElement2(int[] nums) {
//        this.nums = nums;
////        sort(0, nums.length - 1, 0);
////        int mid = nums.length / 2;
//
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i:nums){
//            if(!map.containsKey(i)){
//                map.put(i, 1);
//            }else{
//                map.put(i, map.get(i) + 1);
//            }
//        }
//
//        int maxcount = Integer.MIN_VALUE;
//        int result = -1;
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if(entry.getValue() > maxcount){
//                maxcount = entry.getValue();
//                result = entry.getKey();
//            }
//        }
//
//        return result;
////        return nums[mid];
//
//    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        this.nums = arr;
        if(nums.length == 0) return new int[0];
        sort(0, nums.length - 1, 0);
        int[] result = new int[k];
        for(int i = 0; i < k; i++){
            result[i] = nums[i];
        }
        return result;

    }

    public void sort(int left, int right, int index){
        int i = left;
        int j = right;
        if(i >= j) return;
        int tmp = nums[index];
        while(i < j){
            while(i < j && nums[j] >= tmp){
                j--;
            }
            nums[i] = nums[j];

            while(i < j && nums[i] <= tmp){
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = tmp;
        sort(left, i - 1, left);
        sort(i + 1, right, i + 1);
    }

    public int max1SubArray2(int[] nums) {
        if(nums.length == 0) return 0;
        int result = nums[0];
        // int[] dp = new int[nums.length];
        // dp[0] = nums[0]

        for(int i = 1; i < nums.length; i++){
            nums[i] =  Math.max(nums[i - 1], 0) + nums[i];
            result = Math.max(result, nums[i]);
            // if(dp[i - 1] > 0){
            //     dp[i] = dp[i - 1] + nums[i];
            // }else{
            //     dp[i] = nums[i];
            // }
        }
        return result;
    }
    private int[] uglyNumber = {2,3,5};
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        int count = 0;
        queue.add(1L);
        while(!queue.isEmpty()){
            long i = queue.poll();
            if(++count >= n) return (int) i;
            for(int j: uglyNumber){
                if(!set.contains(i * j)) {
                    set.add(i * j);
                    queue.add(i * j);
                }
            }
        }
        return -1;
    }

    public int lengthOfLongestSubstring(String s) {
//        if(s.length() <= 1) return s.length();
//        char[] ss = s.toCharArray();
//        Set<Character> set = new HashSet<>();
//        int i = 0;
//        int j = 0;
//
//        int result = Integer.MIN_VALUE;
//        while(j < ss.length){
//            if(set.contains(ss[j])){
//                set.remove(ss[i++]);
//            }else{
//                set.add(ss[j++]);
//                result = Math.max(result, j - i);
//            }
//        }
//        return result;
        char[] ss = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        int i = -1;
        int result = Integer.MIN_VALUE;
        for(int m = 0; m < ss.length; m++){
            if(map.containsKey(ss[m])){
                i = Math.max(i, map.get(ss[m]));
            }
            map.put(ss[m], m);
            result = Math.max(result, m - i);

        }
        return result;
    }

//    public double[] twoSum(int n) {
//        //思路一：遍历 5*n+1种不重复结果，计算每一种不重复结果的组合个数
//        double[] res=new double[5*n+1];
//        int curSum=n;
//        for(int i=0; i<res.length; ++i){
//            res[i]=countSum(curSum, n)/Math.pow(6, n);
//            ++curSum;
//        }
//        return res;
//    }
//
//    int countSum(int curSum, int n){
//        if(n<0 || curSum<0){return 0;}
//        if(n==0&&curSum==0){return 1;}
//        int sum=0;
//        for(int i=1; i<7; ++i){
//            sum+=countSum(curSum-i, n-1);
//        }
//        return sum;
//    }

    public double[] dicesProbability1(int n) {
        int[][] dp = new int[n][6*n];

        // 初始化第一行点的个数
        for(int i = 0; i < 6; i++){
            dp[0][i] = 1;
        }

        // 骰子的个数
        for(int i = 2; i <= n; i++){
            // 骰子的总和数
            for(int s = i; s <= 6 * i; s++){
                //
                for(int j = 1; j <= 6; j++){
                    // 当总和数小于 可分的最小值 退出循环
                    if(s - j < i - 1) break;
                    dp[i - 1][s - 1] += dp[i - 2][s - j - 1];
                }
            }
        }

        double[] res = new double[5*n + 1];
        for(int i = 0; i < 5*n + 1; i++){
            res[i] = dp[n - 1][i + n - 1] / Math.pow(6, n);
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums.length == 0) return new int[0];
        int i = 0;
        int j = 0;
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int index = 0;
        while(i < nums.length - k + 1){
            if(j - i == k){
                res[index++] = deque.peek();
                if(nums[i] == deque.peek()) deque.poll();
                i++;
            }else{
                while(!deque.isEmpty() && deque.peekLast() < nums[j]){
                    deque.pollLast();
                }
                deque.addLast(nums[j]);
                j++;
            }
        }
        return res;
    }

//    List<List<Integer>> ll;
//    boolean[] flag1;
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        ll = new ArrayList<>();
//        flag1 = new boolean[nums.length];
//        if(nums.length == 0) return ll;
//        List<Integer> res = new ArrayList();
//        ll.add(res);
//        tracksearch(0, nums, res);
//        return ll;
//    }
//
//    public void tracksearch(int start, int[] nums, List<Integer> res){
//        if(start == nums.length) return;
//        for(int i = start; i < nums.length; i++){
//            if(i >= 1 && nums[i] == nums[i - 1] && !flag1[i - 1]) continue;
//            res.add(nums[i]);
//            flag1[i] = true;
//            ll.add(new ArrayList(res));
//            tracksearch(i+1, nums, res);
//            res.remove(res.size() - 1);
//            flag1[i] = false;
//        }
//    }

//    List<List<Integer>> ll;
//    boolean[] flag1;
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        ll = new ArrayList<>();
//        if(nums.length == 0) return ll;
//        flag1 = new boolean[nums.length];
//        Arrays.sort(nums);
//        tracksearch(nums, new ArrayList());
//        return ll;
//    }
//
//    public void tracksearch(int[] nums, List<Integer> res){
//        if(res.size() == nums.length) {
//            ll.add(new ArrayList(res));
//            return;
//        }
//
//        for(int i = 0; i < nums.length; i++){
//            if(flag1[i]) continue;
//            if(i >= 1 && nums[i] == nums[i - 1] && !flag1[i - 1]) continue;
//            flag1[i] = true;
//            res.add(nums[i]);
//            tracksearch(nums, res);
//            flag1[i] = false;
//            res.remove(res.size() - 1);
//        }
//    }

//    List<List<Integer>> ll = new ArrayList<>();
//    public List<List<Integer>> combine(int n, int k) {
//        if(k == 0 || n == 0) return ll;
//        tracksearch(1, n, k, new ArrayList<>());
//        return ll;
//    }
//
//    public void tracksearch(int start, int n, int k, List<Integer> res){
//        if(res.size() == k){
//            ll.add(new ArrayList(res));
//            return;
//        }
//        for(int i = start; i <= n; i++){
//            if((res.size() + n - i + 1) < k) return;
//            res.add(i);
//            tracksearch(i + 1, n, k, res);
//            res.remove(res.size() - 1);
//        }
//    }

//    List<List<Integer>> ll;
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        ll = new ArrayList<>();
//        if(candidates.length == 0 ||  target == 0) return ll;
//        tracksearch(candidates, target, new ArrayList(), 0);
//        return ll;
//    }
//
//    public void tracksearch(int[] candidates, int target, List<Integer> res, int start){
//        if(target == 0){
//            ll.add(new ArrayList<>(res));
//            return;
//        }
//        if(target < 0) return;
//        for(int i = start; i < candidates.length; i++){
//            res.add(candidates[i]);
//            tracksearch(candidates, target - candidates[i], res, i);
//            res.remove(res.size() - 1);
//        }
//    }


    List<List<Integer>> ll;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ll = new ArrayList<>();
        if(candidates.length == 0 || target == 0) return ll;
        Arrays.sort(candidates);
        tracksearch(candidates, target, new ArrayList<>(), 0);
        return ll;
    }

    public void tracksearch(int[] candidates, int target, List<Integer> res, int start){
        if(target == 0){
            ll.add(new ArrayList(res));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            if(i > 0 && candidates[i - 1] == candidates[i]) continue;
            if(target - candidates[i] < 0) return;
            res.add(candidates[i]);
            tracksearch(candidates, target - candidates[i], res, i + 1);
            res.remove(res.size() - 1);
        }

    }

    public static void function(String[] ss){
        int sum = 0;
        int[] result = new int[ss.length];
        for(int i = 0; i < ss.length; i++){
            result[i] = Integer.parseInt(ss[i]);
            sum += result[i];
        }
        // 为了降低时间复杂度，可以两种情况一起求
        int max = result[0];
        int min = result[0];
        int dpMax = result[0];
        int dpMin = result[0];
        for(int i = 1; i < ss.length; i++){
            dpMax = Math.max(dpMax + result[i], result[i]);
            max = Math.max(max, dpMax);
            dpMin = Math.min(dpMin + result[i],result[i]);
            min = Math.min(min, dpMin);
        }
        out.println(Math.max(sum - min, max));
    }

    // 改进冒泡排序 加一个标识符用于判断在当前比较轮次是否已经排好顺序
    public void bubbleSort(int[] ss){
        boolean flag = true;
        for(int i = 0; i < ss.length && flag; i++){
            flag = false;
            for(int j = ss.length - 1; j > i; j--){
                if(ss[j - 1] > ss[j]){
                    int tmp = ss[j - 1];
                    ss[j - 1] = ss[j];
                    ss[j] = tmp;
                    flag = true;
                }
            }
        }
        Arrays.stream(ss).forEach(out::println);
    }

    // 选择排序每次找到最小的值的下标 然后在交换
    public void simpleSelectSort(int[] ss){
        int min = -1;
        for(int i = 0; i < ss.length; i++){
            min = i;
            for(int j = i + 1; j < ss.length; j++){
                if(ss[j] < ss[min]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = ss[min];
                ss[min] = ss[i];
                ss[i] = tmp;
            }
        }
        Arrays.stream(ss).forEach(out::println);
    }

    // 插入排序
    public void insertSort(int[] ss){
        for(int i = 0; i < ss.length; i++){
            for(int j = i; j > 0; j--){
                if(ss[j] < ss[j - 1]){
                    int tmp = ss[j - 1];
                    ss[j - 1] = ss[j];
                    ss[j] = tmp;
                }else{
                    break;
                }
            }
        }
        Arrays.stream(ss).forEach(out::println);
    }

    // 希尔排序
    public void shellSort(int[] ss){

        int j;
        // 定义一个增量，终止循环条件为 增量为0
        for (int gap = ss.length / 2; gap >  0; gap /= 2) {
            // 从增量开始向后循环
            for (int i = gap; i < ss.length; i++) {
                int tmp = ss[i];
                // 每次与前一个跨过相等增量的值比较
                for (j = i; j >= gap && tmp - ss[j - gap] < 0; j -= gap) {
                    ss[j] = ss[j - gap];
                }
                ss[j] = tmp;
            }
        }
        Arrays.stream(ss).forEach(u-> out.println(u));
    }

    // 归并排序
    public void mergSort(int[] ss){
        int[] res = new int[ss.length];
        mergSortHelp(ss, 0, ss.length - 1, res);
        Arrays.stream(res).forEach(u-> out.println(u));
    }

    public void mergSortHelp(int[] ss, int left, int right, int[] res){
        if(left == right) return;
        int mid = left + (right - left)/2;
        mergSortHelp(ss, left, mid, res);
        mergSortHelp(ss, mid + 1, right, res);

        int index = 0;
        int p = left;
        int q = mid + 1;
        while(p <= mid && q <= right){
            // 保持想等数的顺序
            if(ss[p] <= ss[q]){
                res[index++] = ss[p++];
            }else{
                res[index++] = ss[q++];
            }
        }

        while(p <= mid){
            res[index++] = ss[p++];
        }

        while(q <= right){
            res[index++] = ss[q++];
        }

        // 把临时数组中的元素复制回原数组
        index = 0;
        while(left <= right){
            ss[left++] = res[index++];
        }
    }


    public void quickSort(int[] ss){
        quickSortHelp(ss, 0, ss.length - 1);
        Arrays.stream(ss).forEach(out :: println);
    }

    public void quickSortHelp(int[] ss, int left, int right){
        if(left >= right) return;
        int i = left;
        int j = right;
        int flag = ss[left];
        while(left < right){
            while (left < right && flag < ss[right]) right--;
            ss[left] = ss[right];
            while (left < right && ss[left] <= flag) left++;
            ss[right] = ss[left];
        }
        ss[left] = flag;
        quickSortHelp(ss, i, left - 1);
        quickSortHelp(ss, left + 1, j);
    }

    public int GetMaxConsecutiveOnes (int[] arr, int k) {
        int[] dp = new int[arr.length];
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0) index++;
            dp[i] = index;
        }

        int res = 0;
        for(int i = 0; i < dp.length; i++){
            for(int j = i + 1; j < dp.length; j++){
                if(dp[j] == 0 && dp[i] == 0){

                }
                if(dp[j] - dp[i] <= k){
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
        // write code here
    }

    public void mSystem(int n, int m){
        int res = 0;
        while(n != 0){
            res += n % m;
            n = n / m;
        }
        out.println(res);
    }

    public String char_and_num_return (String text_source) {
        String[] ss = text_source.split(" ");
        StringBuilder res = new StringBuilder();
        List<Long> s2 = new ArrayList<>();

        for(String tmp: ss){
            char[] t1 = tmp.toCharArray();
            if(t1[0] <= 'z' && t1[0] >= 'a') {
                res.append(tmp);
                res.append(" ");
            }else{
                s2.add(Long.parseLong(tmp));
            }
        }

        if(s2.size() == 0){
            res.deleteCharAt(res.length() - 1);
            return res.toString();
        }
        Collections.sort(s2);
        for(int i = 0; i < s2.size(); i++){
            if(i == 0){
                res.append(s2.get(i));
            }else{
                res.append(" ");
                res.append(s2.get(i));
            }
        }
        return res.toString();
        // write code here
    }



    public String Paired69 (String S) {
        char[] ss = S.toCharArray();
        StringBuilder res = new StringBuilder(S);
        Deque<Character> stack = new LinkedList<>();
        for(char c:ss){
            if(c == '6'){
                stack.push(c);
            }else{
                // 如果找到9这个字符,先判断stack里面是否有6,有的则说明匹配,否则在前面插入6
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    res.insert(0, 6);
                }
            }
        }

        // 判断stack里面是否还有6，如果有则在后面加入9
        while(!stack.isEmpty()){
            stack.pop();
            res.append(9);
        }
        return res.toString();
    }

    Long res3 = 0L;
    List<Integer> lres = new ArrayList<>();
    int target = 0;
    public Long trackLayer(int n, int m){
        if(target == n){
            res3 = (res3 + 1) % (long) (Math.pow(10, 9) + 7);
            return res3;
        }
        for(int i = 1; i <= m; i++){
            // 大于给定值n
            if(target + i > n) break;
            // 包含i，不能选择
            if(lres.contains(i)) continue;
            lres.add(i);
            int tmp = -1;
            if(lres.size() > 2){
                tmp = lres.remove(0);
            }
            target += i;
            trackLayer(n, m);
            target -= i;

            // 判断是否需要添加
            if(tmp != -1) lres.add(0, tmp);
            lres.remove(lres.size() - 1);
        }
        return res3;
    }



    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        int index = 1;
        Queue<Integer> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        q.add(root.val);
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> ll = new ArrayList<>();
            for(int i = 0; i < n; i++){
                ll.add(q.poll());
            }
            if(index % 2 != 0){
                Collections.reverse(ll);
            }
            res.add(ll);
            index++;
        }
        return res;
    }

    int res_sum = 0;
    public int sumNumbers (TreeNode root) {
        if(root == null) return 0;

        dfs(root, new StringBuilder());
        return res_sum;
        // write code here
    }

    public void dfs (TreeNode root, StringBuilder tmp) {
        if(root.left == null && root.right == null){
            tmp.append(root.val);
            res_sum += Integer.parseInt(tmp.toString());
            tmp.deleteCharAt(tmp.length() - 1);
            return;
        }
        if(root.left != null) dfs(root.left, tmp.append(root.val));
        tmp.deleteCharAt(tmp.length() - 1);
        if(root.right != null) dfs(root.right, tmp.append(root.val));
        tmp.deleteCharAt(tmp.length() - 1);
        // write code here
    }

    public int findKth(int[] a, int n, int K) {

        quickSort(a, 0, n -1, K);
        return a[K-1];       // write code here
    }

    public void quickSort(int[] a, int left, int right, int k){
        if(left >= right) return;
        int p = left;
        int q = right;
        int tmp = a[left];
        while(p < q){
            while(p < q && tmp < a[q]) q--;
            a[p] = a[q];
            while(p < q && tmp >= a[p]) p++;
            a[q] = a[p];
        }
        a[p] = tmp;
        if(p < k){
            quickSort(a, p + 1, right, k);
        }else{
            quickSort(a, left, p - 1, k);
        }
    }


    public static int findNum(int[] arr, int target){

        int left = 0;
        int right = arr.length;
        while(left < right){
            int mid = left + (right - left)/2;
            if(arr[mid] <= target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        // 都比目标值小
        if(left == 0) return -1;
        // 都比目标值大
        if(left == arr.length && arr[left - 1] != target) return -1;

        if(arr[left - 1] == target){
            return left - 1;
        }else{
            return -1;
        }
    }

    public static void findTopK(int[] arr){
        // 定义三个变量分别代表前三个最大值
        int b_0 = Integer.MIN_VALUE;
        int b_1 = Integer.MIN_VALUE;
        int b_2 = Integer.MIN_VALUE;

        for(int i : arr){
            /*
               定义一个中间变量用于存储 b_0 或 i
               1. 假如当前的 i > b_0 把 b_0 赋给 tmp , 与b_1比较
               2. 假如当前的 i <= b_0 就把 i 赋给 tmp , 与b_1 比较
            */
            int tmp = 0;
            if(i > b_0){
                tmp = b_0;
                b_0 = i;
            }else{
                tmp = i;
            }

            /*
               定义一个中间变量用于存储 b_1 或 tmp(一定小于等于 b_0)
               1. 假如tmp > b_1 把 b_1 赋给 tmp1 , 与b_2 比较
               2. 假如tmp <= b_1 就把 tmp 赋给 tmp1 , 与b_2 比较
            */
            int tmp1 = 0;
            if (tmp > b_1){
                tmp1 = b_1;
                b_1 = tmp;
            }else{
                tmp1 = tmp;
            }
             /*
               此时tmp1 一定小于等于 b_1
               1. 假如tmp1 > b_2 把 tmp1 赋给 b_2
               2. 假如tmp1 <= b_1, 说明tmp1 比最小的还小就需要舍弃它
            */
            if (tmp1 > b_2){
                b_2 = tmp1;
            }
        }
        out.print(b_0 + " " + b_1 + " " + b_2);
    }

    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 4, 1, 96, 96};
        // out.print(findNum(arr, 2));
        findTopK(arr);







        short i = 1;
        // 1. 因为先执行的是i+1,会先将i强转为int，之后将int赋值给short就会出现编译错误
        i = (short) (i + 1);

        // 2. 自增、自减运算符，默认会有强制转换。所有不报错
        i += 1;
        i -= 1;
        short j = 2;
        /*
        3. 两个short做运算后再进行赋值的话，也需要强转；
         因为在做运算操作时，编译器会自动将比int精度小的类型转换为int进行操作，所以运算之后是int类型，需要强转；
        */
        short k = (short)(i + j);

        int a = 1;
        int b = 2;

        // 4. int直接赋值给short时需要强转操作
        short c = (short) (a+b);

        // 5.两个short做运算操作会转成int类型来操作，所以这里的short不需要强转；
        short d = 1;
        short e = 2;
        int f = d + e;

        // 6.short可以直接赋值给int类型，不用强转
        short  q = 1;
        int p = q;

        //
        byte a1 = 1;
        byte b1 = 2;
        byte c1 = (byte) (a1 + b1);


        testArray ss = new testArray();
        out.println(ss.findKth(new int[]{1,3,5,2,2}, 5, 3));




        Map<Integer, Integer> map = new HashMap<>();
        StringBuilder st = new StringBuilder();

        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap();






        /**
         * Java在编译的时候会直接将代码 Integer s1 = 2 封装成Integer i1=Integer.valueOf(2);，从而使用常量池中的对象。
         * 常量池在 方法区中（non-heap）非堆
          */
        Integer s1 = 2;
        /**
         * 会创建新的对象，在堆中
          */
        Integer s2 = new Integer(2);
        out.println(s1 == s2); // s1 与 s2 不等，因为不在一个位置上


        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        out.println("i1=i2   " + (i1 == i2));
        out.println("i1=i2+i3   " + (i1 == i2 + i3));
        out.println("i1=i4   " + (i1 == i4));
        out.println("i4=i5   " + (i4 == i5));
        /**
         * i4 == i5 + i6，因为+这个操作符不适用于Integer对象，首先i5和i6进行自动拆箱操作，进行数值相加，即i4 == 40。
         * 然后Integer对象无法与数值进行直接比较，所以i4自动拆箱转为int值40，最终这条语句转为40 == 40进行数值比较。
         */
        out.println("i4=i5+i6   " + (i4 == i5 + i6));
        out.println("40=i5+i6   " + (40 == i5 + i6));

        /**
         * 这两种不同的创建方法是有差别的，第一种方式是在常量池中拿对象，第二种方式是直接在堆内存空间创建一个新的对象。
         * 只要使用new方法，便需要创建新的对象。
         */
        String str12 = "abcd";  String str22 = new String("abcd");
        out.println(str12 == str22);//false

        /**
         * 连接表达式 +
         * （1）只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新对象才会被加入字符串池中。
         * （2）对于所有包含new方式新建对象（包括null）的“+”连接表达式，它所产生的新对象都不会被加入字符串池中。
         */
        String str1 = "str";
        String str2 = "ing";
        // 会放入常量池
        String str3 = "str" + "ing";

        // 会返回一个新的对象，在堆中
        String str4 = str1 + str2;
        out.println(str3 == str4);//false
        String str5 = "string";
        out.println(str3 == str5);//true


        ss.char_and_num_return("1 xac 30 dz gjdsfaf 20 10 10000 100000000000");
////        out.println(ss.GetMaxConsecutiveOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
//
//        String s1 = "hello";
//        String s2 = "he" + new String("llo");
//        out.println(s1 == s2);

//        List<Integer> ll = new ArrayList<>();
//        ll.add(2);
//        String s1 = "123";
//        StringBuffer s2 = new StringBuffer();
//        StringBuilder s3 = new StringBuilder();
//
//
//        Map<Integer, Integer> map = new HashMap<>();
//
//        map.put(2, 1);
//        map.put(3, 1);
//        Map<Integer,Integer> map1 = new LinkedHashMap<>();
//        Map<Integer,Integer>  map3 = new Hashtable<>();
//        Map<Integer,Integer> map4 = new TreeMap<>();
//        map4.put(null, null);
//
//
//        map.remove(1);
//
//        Set<Integer> set = new HashSet<>();
//        Set<Integer> set1 = new LinkedHashSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        set.add(4);
//        set.add(5);
//        set.add(6);
//        set.add(7);
//        set.add(8);
//        set.add(9);
//        set.add(10);
//        set.add(11);
//        set.add(12);
//        set.add(13);
//        set.add(9);
//
//        List<Integer> ls = new LinkedList<>();
//        ls.add(2);
//        ls.add(2);
//        ls.add(2);
//        ls.add(2);
//        Iterator<Integer> iterator = ls.iterator();
//        while(iterator.hasNext()){
//            out.println(iterator.next());
//        }
//        ls.add(3);

//        Scanner sc = new Scanner(System.in);
//        ss.quickSort(new int[]{7,6,9,3,1,5,2,4});
//        int n = Integer.parseInt(sc.nextLine());
//        while(n > 0){
//            int m = Integer.parseInt(sc.nextLine());
//            String[] st = sc.nextLine().trim().split(" ");
//            function(st);
//            n--;
//        }

//        out.println(ss.maxSubArray(new int[]{3,-2,4,-1}));

//        List<List<Integer>> ll = ss.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
//        int[] ll = ss.maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3);
//        ll.forEach(out::println);

          int[] ll = ss.SpiralMatrix(new int[][]{{1,2}, {4,5}});
          Arrays.stream(ll).forEach(out::println);

//        int[] sp = ss.maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3);
//        Arrays.stream(sp).forEach(out :: println);
//        int[] sp = ss.divideAndConquerSort(new int[]{7,5,6,4});
//        out.println(ss.minArray1(new int[]{3,1,3}));
//        Arrays.stream(sp).forEach(out :: println);

//        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap();
//        map.put(2, 1);
//        map.put(3, 1);
//        int[][] ll = new int[][]{{1, 4, 7, 11},{2, 5, 8, 12},{3, 6,  9, 16},{10, 13, 14, 17}};
//        Arrays.stream(ss.spiralOrder1(ll)).forEach(out :: println);
//        Map<Integer, Integer> map1 = new HashMap<>();
//        map1.put(1, 0);
//        map1.put(2, 0);
//        Map<Integer, Integer> map2 = new HashMap<>(map1);
//        out.println();
//        ss.rotate(ll);
//        out.println(ss.lengthOfLIS1(new int[]{10,9,2,5,3,7,101,18}));
        out.println(ss.exist1(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"));
//        ss.printNumbers1(2);
//        String s = "  hello world  ";
//        int[][] ll = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
//        out.println(ss.dicesProbability(3));
//        List<int[]> res = new ArrayList<>();
//        res.toArray(new int[res.size()][]);
//        MaxQueue mq = new MaxQueue();
//        mq.push_back(1);
//        mq.push_back(2);
//        mq.push_back(-3);
//        out.println(mq.max_value());
//        out.println(mq.pop_front());
//        out.println(mq.max_value());
//        out.println(mq.pop_front());
//        out.println(mq.max_value());
//        out.println(mq.pop_front());
//        out.println(mq.max_value());
//        out.println(mq.pop_front());

    }

}


class MyQueue {

    /** Initialize your data structure here. */
    Deque<Integer> s1;
    Deque<Integer> s2;
    public MyQueue() {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!s2.isEmpty()){
            return s2.pollFirst();
        }else{
            if(s1.isEmpty()){
                return -1;
            }else{
                while(!s1.isEmpty()){
                    s2.push(s1.pollFirst());
                }
            }
            return s2.pollFirst();
        }
    }

    /** Get the front element. */
    public int peek() {
        if(!s2.isEmpty()){
            return s2.peekFirst();
        }else{
            if(s1.isEmpty()){
                return -1;
            }else{
                while(!s1.isEmpty()){
                    s2.add(s1.pollFirst());
                }
            }
            return s2.peekFirst();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s2.isEmpty() && s1.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue mq = new MyQueue();
        mq.push(1);
        out.println(mq.pop());
        out.println(mq.empty());

        Map<Character, Integer> map = new HashMap<>();
        for(Map.Entry s:map.entrySet()){

        }
    }
}



/*class MaxQueue {

    private Queue<Integer> queue1;

    // 双端队列
    private Deque<Integer> dqueue;
    public MaxQueue() {
        queue1 = new LinkedList<>();
        dqueue = new LinkedList<>();

    }

    public int max_value() {
        if(!dqueue.isEmpty()){
            return dqueue.peek();
        }
        return -1;
    }

    //
    public void push_back(int value) {
        queue1.add(value);
        while (!dqueue.isEmpty() && dqueue.peekLast() < value) {
            dqueue.pollLast();
        }
        dqueue.addLast(value);
    }

    public int pop_front() {
        if(!queue1.isEmpty()){
            int ll = queue1.poll();
            if(ll == dqueue.peek()) dqueue.remove();
            return ll;
        }
        return -1;
    }
}

class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DoubleList cache;
    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        // 先把新节点 x 做出来
        Node x = new Node(key, val);

        if (map.containsKey(key)) {
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
            cache.addFirst(x);
            // 更新 map 中对应的数据
            map.put(key, x);
        } else {
            if (cap == cache.size()) {
                // 删除链表最后一个数据
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            // 直接添加到头部
            cache.addFirst(x);
            map.put(key, x);
        }
    }
    public static void main(String[] args) {


    }
}

class Node {
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}

class DoubleList {

    private Node first;
    private Node last;
    private int size = 0;

    public DoubleList() {

    }

    // 在链表头部添加节点 x，时间 O(1)
    public void addFirst(Node x){
        Node f = first;
        x.next = f;
        first = x;
        if(f == null){
            last = x;
        }else{
            f.prev = x;
        }
        size++;
    }

    // 删除链表中的 x 节点（x 一定存在）
    // 由于是双链表且给的是目标 Node 节点，时间 O(1)
    public void remove(Node x){
        Node prev = x.prev;
        Node next = x.next;

        // 先考虑前驱
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        // 在考虑后继
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        size--;
    }

    // 删除链表中最后一个节点，并返回该节点，时间 O(1)
    public Node removeLast(){
        Node tmp = last;
        Node pre = last.prev;
        last = pre;
        if(pre == null){
            first = null;
        }else{
            pre.next = null;
        }
        size--;
        return tmp;
    }

    // 返回链表长度，时间 O(1)
    public int size(){
        return size;
    }
}*/


class MinStack1 {

    /** initialize your data structure here. */
    Stack<Integer> s1;
    Stack<Integer> s2;
    public MinStack1() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        s1.add(x);
        if(s2.isEmpty()){
            s2.add(x);
        }else{
            if(s2.peek() >= x){
                s2.add(x);
            }
        }
    }


    public void pop() {
        if(!s1.isEmpty()){
            if(s1.pop().equals(s2.peek())){
                s2.pop();
            }
        }
    }

    public int top() {
        if(!s1.isEmpty()){
            return s1.peek();
        }else{
            return -1;
        }
    }

    public int min() {
        if(!s2.isEmpty()){
            return s2.peek();
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        MinStack1 ss = new MinStack1();
        ss.push(512);
        ss.push(-1024);
        ss.push(-1024);
        ss.push(512);
        ss.pop();
        out.println(ss.min());
        ss.pop();
        out.println(ss.min());
        ss.pop();
        out.println(ss.min());
    }
}




class MaxQueue {

    Deque<Integer> q1;
    Deque<Integer> q2;
    public MaxQueue() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public int max_value() {
        if(q2.isEmpty()) return -1;
        return q2.peek();
    }

    public void push_back(int value) {

        while(!q2.isEmpty() && q2.peekLast() < value){
            q2.removeLast();
        }
        q1.push(value);
        q2.addLast(value);
    }

    public int pop_front() {
        if(q1.isEmpty()) return -1;
        int result = q1.poll();
        if(result == q2.peekFirst()) q2.poll();
        return result;
    }
}