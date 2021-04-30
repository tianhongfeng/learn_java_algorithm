import abstractClasses.Person;
import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
import sun.reflect.generics.tree.Tree;

import java.util.*;

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

    public double myPow1(double x, int n) {
        if(n == 0) return 1.0;
        double result = 1.0;
        int i = 0;
        while(n > 0){
            if((n & 1) == 1){

            }
        }
        return result;
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

    public static void main(String[] args) {
        testArray ss = new testArray();

        System.out.println(ss.singleNumber(new int[]{2,2,1}));


    }
}
