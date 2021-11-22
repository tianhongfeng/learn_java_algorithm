

import java.io.*;
import java.util.*;

public class testString {

    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }


    public int evalRPN(String[] tokens) {
        int sum = 0;
        Stack<String> st_b = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].equals("/") && !tokens[i].equals("+") && !tokens[i].equals("*") && !tokens[i].equals("-")) {
                st_b.push(tokens[i]);
            } else {
                int b = Integer.valueOf(st_b.pop());
                int a = Integer.valueOf(st_b.pop());
                if (tokens[i].equals("/")) sum = a / b;
                if (tokens[i].equals("*")) sum = a * b;
                if (tokens[i].equals("-")) sum = a - b;
                if (tokens[i].equals("+")) sum = a + b;
                st_b.push(String.valueOf(sum));
            }
        }
        return sum;
    }

//    private int getNext(int n) {
//        int totalSum = 0;
//        while (n > 0) {
//            int d = n % 10;
//            n = n / 10;
//            totalSum += d * d;
//        }
//        return totalSum;
//    }

    //    public boolean isHappy(int n) {
//        Set<Integer> seen = new HashSet<>();
//        while (n != 1 && !seen.contains(n)) {
//            seen.add(n);
//            n = getNext(n);
//        }
//        return n == 1;
//    }
    public int helper(int n) {
        int sum = 0;
        while (n > 0) {
            int i = n % 10;
            sum += Math.pow(i, 2);
            n = n / 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        Set<Integer> hs = new HashSet<>();
        while (n != 1) {
            int sum = helper(n);
            if (!hs.add(sum)) return false;
            n = sum;
        }
        return true;
    }


    public int lengthOfLongestSubstring(String s) {

        int p = 0;
        int ll = Integer.MIN_VALUE;
        Set<Character> ss = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            while (p < s.length()) {
                if (!ss.contains(s.charAt(p))) {
                    ss.add(s.charAt(p));
                    p++;
                } else {
                    break;
                }
            }
            ll = Math.max(ll, ss.size());
            ss.remove(s.charAt(i));
        }
        return ll;
    }

    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }

    public String longestPalindrome(String s) {

        int maxlength = Integer.MIN_VALUE;
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (findPalindrome(i, j, s)) {
                    int ss = j - i + 1;
                    if (ss > maxlength) {
                        maxlength = ss;
                        max = s.substring(i, j + 1);
                    }
                }
            }
        }
        return max;
    }

    public boolean findPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * 1.如果要是回文字符串，那么两个边界也是相等，我们先判断边界值是否相等，然后再去除边界值判断内测。
     * 2.这样就可以用到状态转移
     * 3.dp[i][j] = (dp[i + 1][j - 1] && s[i] == s[j])
     * 4.如果为一个字符串则必然是回文的 dp[i][i] = true
     * 5.如果为两个字符串则只需要判断s[i] == s[j],dp[i][j] = s[i] == s[j]
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {

        int max = 1;
        int begin = 0;
        boolean[][] flag = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            flag[i][i] = true;
        }
        for (int l = 2; l <= s.length(); l++) {
            for (int i = 0; i < s.length(); i++) {
                int j = l + i - 1;
                if (j > s.length() - 1) break;
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        flag[i][j] = true;
                    } else {
                        flag[i][j] = flag[i + 1][j - 1];
                    }
                }
                if (flag[i][j] && l > max) {
                    begin = i;
                    max = l;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    public List<String> letterCombinations(String digits) {

        List<String> ll = new ArrayList<>();
        if (digits.length() <= 0) return ll;
        Map<Character, String> map = new HashMap<>();

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        ll.add("");
        for(int i = 0; i < digits.length(); i++) {
            String ss = map.get(digits.charAt(i));
            int size = ll.size();
            for (int j = 0; j < size; j++) {
                String tmp = ll.remove(0);
                for (int k = 0; k < ss.length(); k++) {
                    ll.add(tmp + ss.charAt(k));
                }
            }
        }
        return ll;
    }

//    public List<String> back_search(String digits, int index, List<String> ll, StringBuffer ss, Map<Character, String> map) {
//        if (index == digits.length()) {
//            ll.add(ss.toString());
//        } else {
//            String tmp = map.get(digits.charAt(index));
//            for (int i = 0; i < tmp.length(); i++) {
//                ss.append(tmp.charAt(i));
//                back_search(digits, index + 1, ll, ss, map);
//                ss.deleteCharAt(index);
//            }
//        }
//        return ll;
//    }

    public int countSubstrings(String s) {
        int count = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int l = 0; l < s.length(); l++){
            for(int i = 0;i < s.length(); i++){
                int q = i + l;
                if(q >= s.length()) break;
                if(s.charAt(i) == s.charAt(q) && q - i <= 1){
                    dp[i][q] = true;
                    count++;
                }else{
                    if(q - i >= 2) dp[i][q] = (s.charAt(i) == s.charAt(q)) && dp[i + 1][q - 1];
                    if(dp[i][q]) count++;
                }
            }
        }
        return count;
    }

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                sb.append('%');
                sb.append(20);
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public char firstUniqChar(String s) {

        int[] ss = new int[128];
        char[] rr = s.toCharArray();
        for(int i = 0; i < rr.length; i++){
            ss[rr[i]]++;
        }

        for(int i = 0; i < rr.length; i++){
            if(ss[rr[i]] == 1) return rr[i];
        }
        return ' ';
    }

    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int n = s.length();
        boolean flag = true;

        // 1. 判断空格
        while(i < s.length() && chars[i] == ' '){
            i++;
        }

        // 2、如果已经遍历完成（针对极端用例 "      "）
        if (i == n) {
            return 0;
        }


        // 3.判断正数或者负数
        if(chars[i] == '-' || chars[i] == '+'){
            flag = chars[i] != '-';
            i++;
        }

        if(!Character.isDigit(chars[i])) return 0;


        //4.构建数字
        int ans = 0;
        while(i < n && Character.isDigit(chars[i])){

            // 字符转数字
            int digit = chars[i] - '0';
            // 判断是否越界
            if(ans > (Integer.MAX_VALUE - digit) / 10){
                return flag ? Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            ans = ans * 10 + digit;
            i++;
        }
        return flag ? ans: -ans;
    }

    public long helper(StringBuilder ss){
        long sum = 0;
        int j = 0;
        for(int i = ss.length() - 1; i >= 0; i--){
            long tmp = ss.charAt(i) - '0';
            sum += Math.pow(10, j) * tmp;
            j++;
        }
        return sum;
    }

    public int lengthOfLongestSubstring1(String s) {

//        int p = 0;
//        int ll = Integer.MIN_VALUE;
//        Set<Character> ss = new HashSet<>();
//        for (int i = 0; i < s.length(); i++) {
//            while (p < s.length()) {
//                    if (!ss.contains(s.charAt(p))) {
//                    ss.add(s.charAt(p));
//                    p++;
//                } else {
//                    break;
//                }
//            }
//            ll = Math.max(ll, ss.size());
//            ss.remove(s.charAt(i));
//        }
//        return ll;
        int left = 0;
        if(s.length() == 0) return s.length();
        int right = 0;
        int max_length = Integer.MIN_VALUE;
        Set<Character> set = new HashSet<>();
        while(right < s.length()){
            char c = s.charAt(right++);
            while(set.contains(c)){
                set.remove(s.charAt(left++));
            }
            set.add(c);
            max_length = Math.max(set.size(), max_length);
        }
        return max_length;
    }

    int length;
    public int translateNum(int num) {
        String s = String.valueOf(num);
        this.length = s.length();
        int[] dp = new int[s.length() + 1];
        dp[length] = 1;
        dp[length - 1] = 1;
        return backTrack(s, 0, dp);
    }

    public int translateNum1(int num) {
        String s = String.valueOf(num);
//        int[] dp = new int[s.length() + 1];
//        dp[0] = 1;
//        dp[1] = 1;
        int a = 1;
        int b = 1;
        for(int i = 2; i < s.length() + 1; i++){

            if(s.substring(i - 2, i).compareTo("10") >= 0 && s.substring(i - 2, i).compareTo("25") <= 0){
                int c = a + b;
                b = a;
                a = c;
            }else{
                b = a;
            }
        }
        return a;
    }



    public int backTrack(String s, int index, int[] dp){
        if(dp[index] != 0) return dp[index];
        if(s.substring(index, index + 2).compareTo("10") >= 0 && s.substring(index, index + 2).compareTo("25") <= 0){
            dp[index] = backTrack(s, index + 1, dp) + backTrack(s, index + 2, dp);
        }else{
            dp[index] =  backTrack(s, index + 1, dp);
        }
        return dp[index];
    }


    boolean[] flag;
    int length_s;
    StringBuilder path = new StringBuilder();
    List<String> ll = new ArrayList<>();
    List<String> ls = new ArrayList<>();
    char[] ss ;
    public String[] permutation(String s) {
        this.flag = new boolean[s.length()];
        this.length_s = s.length();
        this.ss = s.toCharArray();
        Arrays.sort(ss);
        bachTrack(0, s);
        return ll.toArray(new String[ll.size()]);
    }

    public void bachTrack(int index, String s){
        if(index == length_s){
//            String temp = join(ls);
//            ll.add(temp);
            ll.add(path.toString());
            return;
        }
        for(int i = 0; i < s.length(); i++){
            // 剪枝
            if(flag[i]) continue;
            if(i >= 1 && ss[i - 1] == ss[i] && !flag[i - 1]) continue;
            flag[i] = true;
            String temp = String.valueOf(ss[i]);
            path.append(temp);
//            ls.add(temp);
            bachTrack(index + 1, s);
            flag[i] = false;
//            ls.remove(index);

            // 回退到上一步
            path.deleteCharAt(path.length() - 1);
        }
    }

    public String join(Collection var0) {
        StringBuffer var2 = new StringBuffer();
        Iterator var3 = var0.iterator();
        while(var3.hasNext()) {
            var2.append((String)var3.next());
        }
        return var2.toString();
    }


    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation1(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        int res = -1;
        for (int i = 1; i <= n - 1; i++) {
            res = Math.max(res, Math.max(i * (n - i), i * integerBreak(n - i)));
        }
        return res;
    }

//    int[] memory;
//    public int integerBreak1(int n) {
//        memory = new int[n + 1];
//        return integerBreakHelper(n);
//    }

//    public int integerBreakHelper(int n) {
//        if (n == 2) {
//            return 1;
//        }
//        // memory的初始值为0，如果它不为0，说明已经计算过了，直接返回即可
//        if (memory[n] != 0) {
//            return memory[n];
//        }
//        int res = -1;
//        for (int i = 1; i <= n - 1; i++) {
//            res = Math.max(res, Math.max(i * integerBreakHelper(n - i), i * (n - i)));
//        }
//        // 将每次计算的结果保存到备忘录数组中
//        memory[n] = res;
//        return res;
//    }
    public int integerBreak1(int n) {
        int[] dp = new int[n + 1];
        return helper(n, dp);
    }

    public int helper(int n, int[] dp){
        if(n == 2) return 1;
        if(dp[n] != 0) return dp[n];
        int res = -1;
        for(int i = 1; i <= n - 1; i++){
            res = Math.max(res, Math.max(i * (n - i), i * helper(n - i, dp)));
        }
        dp[n] = res;
        return dp[n];
    }

    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<>();
        char[] cc = astr.toCharArray();
        for(char c: cc){
            if(set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }

    public String function(String s){
        if(s.length() <= 1) return "NO";
        char[] ss = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char i:ss){
            if(i == '('){
                stack.push(i);
            }else if(i == ')'){
                stack.pop();
            }else{
                return "NO";
            }
        }
        return stack.isEmpty()?"YES": "NO";
    }

    public String unique_string (String s) {
//        if(s.length() <= 1) return s;
//        char[] ss = s.toCharArray();
//        StringBuilder sb = new StringBuilder();
//        Set<Character> set = new HashSet();
//        for(char i:ss){
//            if(set.contains(i)) continue;
//            set.add(i);
//            sb.append(i);
//        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            // s.indexof() 会返回该字符在字符串中第一位置得索引，以此来判断是否重复，从而保留第一次出现的字符
            if (s.indexOf(s.charAt(i)) == i) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
        // write code here
    }

//    public String replaceSpace(String s) {
//        if(s.length() == 0) return s;
//        int i = 0;
//        StringBuilder sb = new StringBuilder();
//        while(i < s.length()){
//            if(s.charAt(i) == ' '){
//                sb.append("%20");
//            }else{
//                sb.append(s.charAt(i));
//            }
//            i++;
//        }
//        return sb.toString();
//    }

/*    public String unique_string (String s) {
        // write code here
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == i) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }*/

//    double result = 1.0;
//    public double myPow(double x, int n) {
//        if(n == 0) return 1.0;
//        if(n < 0){
//            return 1 / help(x , n * (-1));
//        }
//        return help(x, n);
//    }
//    int[] result = 1.0;
//    public double myPow(double x, int n) {
//        if(n == 0) return 1.0;
//        long b = n;
//        if(n < 0){
//            x = 1 / x;
//            b = -n;
//        }
//        while(b > 0){
//            if((b & 1) == 1) result *= x;
//            x *= x;
//            b >>>= 1;
//        }
//        // 9 = 1001 = 1 * 1 + 0 * 2 + 0 * 4 + 0 * 8
//        // x的九次方 = x一次方 * x八次方
//        // x9 = x1 * x8
//        return result;
//    }

    List<Integer> ll1;
    int start;
    public int[] printNumbers(int n) {
        ll1 = new ArrayList<>();
        start = n - 1;
        helper(0, n, new StringBuilder(),0);
        return ll1.stream().mapToInt(Integer::valueOf).toArray();
    }


    // 记录9的个数来更新要分割的位置
    public void helper(int index, int n, StringBuilder s, int nine){

        if(index == n){
            String tme = s.substring(start);
            if(tme.equals("0")) return;
            ll1.add(Integer.valueOf(tme));
            if(n - start == nine) start--;
            return;
        }

        for(int i = 0; i < 10; i++){
            s.append(i);
            if(i == 9){
                nine++;
            }
            helper(index + 1, n, s, nine);
            s.deleteCharAt(s.length() - 1);
        }
        nine--;
    }

//    public double help(double x, int n){
//        if(n == 1 || n == 0) return x;
//        double y = help(x, n >> 1);
//
//        // 如果为奇数 需要再乘以 x
//        if((n & 1) == 1){
//            result = x * y * y;
//        }
//
//        //
//        result = y * y;
//
//        return result;
//    }


    public String[] permutation2(String s) {
        if(s.length() == 0) return new String[0];
        this.ll = new ArrayList<>();
        this.flag = new boolean[s.length()];
        help(0, new StringBuilder(), s);
        return  ll.toArray(new String[ll.size()]);
    }

    public void help(int index, StringBuilder ss, String s){
        if(index == s.length()){
            ll.add(new StringBuilder(ss).toString());
        }

        for(int i = 0; i < s.length(); i++){

            if(i >= 1 && s.charAt(i) != (s.charAt(i - 1)) && !flag[i - 1]) continue;
            if(!flag[i]){
                ss.append(s.charAt(i));
                flag[i] = true;
                help(index + 1, ss, s);
                flag[i] = false;
                ss.deleteCharAt(ss.length() - 1);
            }
        }
    }

    List<List<Integer>> ll2 = new ArrayList<>();
    public int[][] findContinuousSequence(int target) {

        for(int i = target/2 + 1; i >= 1; i--){
            int sum = i;
            for(int j = i - 1; j >= 1; j--){
                if(sum + j == target){
                    help(i, j);
                    break;
                }else{
                    sum += j;
                }
            }
        }
        int[][] result = new int[ll2.size()][];
        for(int i = 0; i < ll2.size(); i++){
            result[i] = ll2.get(i).stream().mapToInt(Integer :: valueOf).toArray();
        }

        return result;
    }

    public void help(int i, int j){
        List<Integer> ls = new ArrayList<>();
        while(j <= i){
            ls.add(j);
            j++;
        }
        ll2.add(ls);
    }

    public int[][] findContinuousSequence2(int target) {

        // 滑动窗口
        List<int[]> ll = new ArrayList<>();
        int i = 1;
        int j = 2;
        while(i < j){
            int sum = (i + j) * (j - i + 1)/2;
            if(sum == target){
                int[] arr = new int[j - i + 1];
                for(int index = 0; index < arr.length; index++){
                    arr[index] = index + i;
                }
                ll.add(arr);
                i++;
            }else if(sum > target){
                i++;
            }else{
                j++;
            }
        }

        return ll.toArray(new int[ll.size()][]);
    }

    public String reverseLeftWords(String s, int n) {
        if(s.length() == 0) return new String("");
        StringBuilder ss = new StringBuilder();
        for(int i = n; i < s.length(); i++){
            ss.append(s.charAt(i));
        }

        for(int i = 0; i < n; i++){
            ss.append(s.charAt(i));
        }
        return ss.toString();
    }

    public String reverseWords(String s) {
        char[] ss = s.toCharArray();
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(ss[i] == ' '){
                list.add(sb.toString());
                sb = new StringBuilder();
            }else{
                sb.append(ss[i]);
            }
        }

        list.add(sb.toString());
        StringBuilder sb1 = new StringBuilder("");
        for(int i = list.size() - 1; i >= 0; i--){
            if(list.get(i).equals("")) continue;
            if(sb1.length() == 0){
                sb1.append(list.get(i));
            }else{
                sb1.append(" ");
                sb1.append(list.get(i));
            }
        }
        return sb1.toString();
    }


    int[] nums;
    public String minNumber(int[] nums) {
        this.nums = nums;
        if(nums.length == 0) return "";
        quickSort(0, nums.length - 1, 0);
        StringBuilder sb = new StringBuilder();
        for(int i:nums){
            sb.append(i);
        }
        return sb.toString();
    }

    public void quickSort(int left, int right, int index){
        if(left >= right) return;
        int tmp = nums[index];
        int i = left;
        int j = right;
        while (i < j){
            while(i < j && compare(tmp, nums[j])) j--;
            nums[i] = nums[j];
            while(i < j && !compare(tmp, nums[i])) i++;
            nums[j] = nums[i];
        }
        nums[i] = tmp;
        quickSort(left, i - 1, left);
        quickSort(i + 1, right, i + 1);
    }

    // a1 < b1
    public boolean compare(int a, int b){
        StringBuilder a1 = new StringBuilder(String.valueOf(a));
        a1.append(b);
        StringBuilder b1 = new StringBuilder(String.valueOf(b));
        b1.append(a);
        if(a1.toString().compareTo(b1.toString()) < 0) return true;
        return false;
    }

    public int translateNum3(int num) {
//        int n = num;
//        List<Integer> ls = new ArrayList<>();
//        while(n != 0){
//            ls.add(n % 10);
//            n = n / 10;
//        }
//        if(ls.size() <= 1) return 1;
//        Collections.reverse(ls);
//        int[] dp = new int[ls.size()];
//        dp[0] = 1;
//        for(int i = 1; i < ls.size(); i++){
//            int tmp = ls.get(i - 1) * 10 + ls.get(i);
//            if(i == 1){
//                dp[i] = tmp > 25? dp[i - 1] : dp[i - 1] + 1;
//            }else{
//                dp[i] = tmp > 25? dp[i - 1] : dp[i - 1] + dp[i - 2];
//            }
//
//        }
//        return dp[ls.size() - 1];

        String s = String.valueOf(num);
        int n = num;
        List<Integer> ls = new ArrayList<>();
        while(n != 0){
            ls.add(n % 10);
            n = n / 10;
        }
        if(ls.size() <= 1) return 1;
        Collections.reverse(ls);
        // int[] dp = new int[ls.size()];
        int a = 1;
        int b = 1;
        // dp[0] = 1;
        for(int i = 1; i < ls.size(); i++){
            int tmp = ls.get(i - 1) * 10 + ls.get(i);
            int c = tmp <= 25 && tmp >= 10? a + b : a;
            b = a;
            a = c;
            // if(i == 1){
            //     dp[i] = tmp > 25 || tmp < 10? dp[i - 1] : dp[i - 1] + 1;

            // }else{
            //     dp[i] = tmp > 25 || tmp < 10? dp[i - 1] : dp[i - 1] + dp[i - 2];
            //     c = tmp <= 25 && tmp >= 10? a : a + b;
            // }
        }
        return a;
    }


//    List<String> ll3;
//    public List<String> letterCasePermutation(String s) {
//        ll3 = new ArrayList<>();
//        if(s.length() == 0) return ll3;
//        dfs(0, new StringBuffer(), s);
//        return ll3;
//    }
//
//    public void dfs(int index, StringBuffer sb, String ss){
//        if(index == ss.length()){
//            ll3.add(new String(sb));
//            return;
//        }
//        if(ss.charAt(index) <= '9' && ss.charAt(index) >= '0'){
//            dfs(index + 1, sb.append(ss.charAt(index)), ss);
//        }else{
//            // 小写分支
//            dfs(index + 1, sb.append(String.valueOf(ss.charAt(index)).toLowerCase()), ss);
//            sb.deleteCharAt(sb.length() - 1);
//            // 大写分支
//            dfs(index + 1, sb.append(String.valueOf(ss.charAt(index)).toUpperCase()), ss);
//        }
//        sb.deleteCharAt(sb.length() - 1);
//    }

//    List<String> ll3;
//    public List<String> letterCombinations2(String digits) {
//        ll3 = new ArrayList<>();
//        if(digits.length()== 0) return ll;
//        Map<Character, String> map = new HashMap<>();
//        map.put('2', "abc");
//        map.put('3', "def");
//        map.put('4', "ghi");
//        map.put('5', "jkl");
//        map.put('6', "mno");
//        map.put('7', "pqrs");
//        map.put('8', "tuv");
//        map.put('9', "wxyz");
//        tracksearch(0, new StringBuffer(), digits, map);
//
//        return ll;
//    }
//
//    public void tracksearch(int index, StringBuffer sb, String digits, Map<Character, String> map){
//        if(index == digits.length()){
//            ll.add(new String(sb));
//            return;
//        }
//        String tmp = map.get(digits.charAt(index));
//        for(int i = 0; i < 3; i++){
//           sb.append(tmp.charAt(i));
//           tracksearch(index + 1, sb, digits, map);
//           sb.deleteCharAt(sb.length() - 1);
//        }
//    }

//    List<List<String>> ll3;
//    public List<List<String>> partition(String s) {
//        ll3 = new ArrayList<>();
//        if(s.length() == 0) return ll3;
//        //
//        boolean[][] dp = new boolean[s.length()][s.length()];
//        for(int i = s.length() - 1; i >= 0; i--){
//            for(int j = s.length() - 1; j >=0; j--){
//                if(i >= j){
//                    dp[i][j] = true;
//                    continue;
//                }
//                boolean flag = s.substring(i, i + 1).equals(s.substring(j, j + 1));
//                if(j - i == 1){
//                    dp[i][j] = flag;
//                    continue;
//                }
//                dp[i][j] = dp[i + 1][j - 1] && flag;
//            }
//
//        }
//        tracksearch(s, new ArrayList<>(), dp, 0);
//
//        return ll3;
//    }
//
//    public void tracksearch(String s, List<String> list, boolean[][] dp, int i){
//
//        if(i == s.length()) {
//            ll3.add(new ArrayList<>(list));
//        }
//        for(int j = i; j < s.length(); j++){
//
//            if(!dp[i][j]) continue;
//            list.add(s.substring(i, j + 1));
//            tracksearch(s, list, dp, j + 1);
//            list.remove(list.size() - 1);
//        }
//    }

//    public boolean ispalindromic(String ss){
//        int left = 0;
//        int right = ss.length() - 1;
//        char[] cc = ss.toCharArray();
//        while(left < right){
//            if(cc[left] != cc[right]) return false;
//            left++;
//            right--;
//        }
//        return true;
//    }


    List<String> ll3;
    public List<String> restoreIpAddresses(String s) {
        ll3 = new ArrayList<>();
        backtrack(0, new ArrayList<>(), 0, s);
        return ll3;
    }

    public void backtrack(int countip, List<String> res, int start, String s){

        if(countip == 4){
            if(start == s.length()){
                StringBuffer sb = new StringBuffer();
                for(String ss:res){
                    if(sb.length() == 0){
                        sb.append(ss);
                    }else{
                        sb.append(".");
                        sb.append(ss);
                    }
                }
                ll3.add(sb.toString());
            }
            return;
        }

        for(int i = start; i < s.length(); i++){
            String tmp = s.substring(start, i + 1);
            // 去除前导0
            if(tmp.charAt(0) == '0' && tmp.length() > 1) return;
            // 去除大于255的数字
            if(Integer.valueOf(tmp).compareTo(255) > 0) return;
            res.add(tmp);
            backtrack(countip + 1, res, i + 1, s);
            res.remove(res.size() - 1);
        }
    }


    // 定义两个优先队列
    public int[] function(String s1, String s2){
        ArrayList<PriorityQueue<Integer>> queueList = new ArrayList<>();
        queueList.add(new PriorityQueue<>());
        queueList.add(new PriorityQueue<>());
        char[] t2 = s2.toCharArray();
        char[] t1 = s1.toCharArray();
        int[] res = new int[t2.length];
        // 往优先队列里面放入 分别为 0 和 1 的下标
        for(int i = 0; i < t1.length; i++){
            if(t1[i] - '0' < 2) queueList.get(t1[i] - '0').offer(i + 1);
        }
        int result = -1;
        for(int i = 0; i < t2.length; i++){
            // 如果为男性，则先检查人数为1的队列， 然后在检查人数为0的队列
            if(t2[i] == 'M'){
                if(!queueList.get(1).isEmpty()){
                    result = queueList.get(1).poll();
                }else{
                    if(!queueList.get(0).isEmpty()){
                        result = queueList.get(0).poll();
                        queueList.get(1).offer(result);
                    }
                }
            }else{ // 如果为女性，则先检查人数为0的队列， 然后在检查人数为1的队列
                if(!queueList.get(0).isEmpty()){
                    result = queueList.get(0).poll();
                    queueList.get(1).offer(result);
                }else{
                    if(!queueList.get(1).isEmpty()){
                        result = queueList.get(1).poll();
                    }
                }
            }
            res[i] = result;
        }
        return res;
    }

    public int findzero(char[] t1){
        for(int i = 0; i < t1.length; i++){
            if(t1[i] == '0') return i;
        }
        return -1;
    }

    public int findone(char[] t1){
        for(int i = 0; i < t1.length; i++){
            if(t1[i] == '1') return i;
        }
        return -1;
    }


    public void functionCake(String s1, String s2){
        String[] s1array = s1.trim().split(" ");
        String[] s2array = s2.trim().split(" ");
        int n = Integer.valueOf(s1array[0]);
        int m = Integer.valueOf(s1array[1]);
        int a = Integer.valueOf(s1array[2]);
        int b = Integer.valueOf(s1array[3]);
        if(a - b >= 0){
            int tmp = a;
            a = b;
            b = tmp;
        }
        int flag = 2;
        for(int i = 0; i < s2array.length; i++){
            int tmp = Integer.valueOf(s2array[i]);
            if(tmp < a || tmp > b) {
                System.out.println("NO");
                return;
            }
            if(tmp == a) flag--;
            if(tmp == b) flag--;
        }
        if(n - m < flag) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        return;
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] flag = new boolean[board.length][board[0].length];
        boolean result = false;
        for(int i = 0; i < flag.length; i++){
            for(int j = 0; j < flag[0].length; j++){
                result = help(flag, board, word, 0, i, j);
                if(result == true) return result;
            }
        }
        return result;
    }
    public boolean help(boolean[][] flag, char[][] board, String word, int index, int i, int j){
        if(index == word.length()) return true;
        if(i < 0 || i > board.length - 1 || j < 0 || j > flag[0].length - 1 || flag[i][j]) return false;
        if(board[i][j] == word.charAt(index)){
            flag[i][j] = true;
            boolean res = help(flag, board, word, index + 1, i + 1, j) || help(flag, board, word, index + 1, i - 1, j) || help(flag, board, word, index + 1, i, j + 1) || help(flag, board, word, index + 1, i, j - 1);
            flag[i][j] = false;
            return res;
        }
        return false;
    }




    public static void main(String[] args) throws IOException {

        testString ss = new testString();
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = re.readLine().trim().split(" ");
        int m = Integer.parseInt(s1[0]);
        int n = Integer.parseInt(s1[1]);
        int q = Integer.parseInt(s1[2]);
        Map<Integer, Integer> map = new HashMap<>();
        boolean[] mflag = new boolean[m + 1];
        boolean[] nlock = new boolean[n + 1];
        while(q > 0){
            String[] tmp = re.readLine().trim().split(" ");
            int flag = Integer.parseInt(tmp[0]);
            if(flag == 1){
                int x = Integer.parseInt(tmp[1]);
                int y = Integer.parseInt(tmp[2]);
                if(mflag[x] || nlock[y] || (map.containsKey(x) && nlock[map.get(x)])) continue;
                map.put(x, y);
            }else if(flag == 2){
                nlock[Integer.parseInt(tmp[1])] = true;
            }else if(flag == 3){
                nlock[Integer.parseInt(tmp[1])] = false;
            }else if(flag == 4){
                int x = Integer.parseInt(tmp[1]);
                // 不在小美手上 或者 在小美手上但是上了锁
                if(map.get(x) == null || nlock[map.get(x)]) {
                    System.out.println(-1);
                }else{
                    mflag[x] = true;
                    System.out.println(map.get(x));
                    map.remove(x);
                }
            }else{
                int x = Integer.parseInt(tmp[1]);
                if(mflag[x]){
                    mflag[x] = false;
                }
            }
            q--;
        }
        System.out.println(ss.exist(new char[][]{{'X','Y','Z','E'}, {'S','F','Z','S'}, {'X','D','E','E'}}, "XYZZED"));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//        try{
//            int n = Integer.parseInt(reader.readLine());
//            for(int i = 0; i < n; i++){
//                int N = Integer.parseInt(reader.readLine());;
//                String s1 = reader.readLine();
//                int M = Integer.parseInt(reader.readLine());
//                String s2 = reader.readLine();
//                int[] res = ss.function(s1, s2);
//                for (int r : res) {
//                    writer.write(Integer.toString(r));
//                    writer.newLine();
//                }
//            }
//            writer.flush();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }


//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNextLine()){
//            String s1 = sc.nextLine();
//            String s2 = sc.nextLine();
//            ss.functionCake(s1, s2);
//        }

//        Scanner sc = new Scanner(System.in);
//        String s1 = sc.nextLine();
//        String s2 = sc.nextLine();
//        String[] s1Array = s1.trim().split(" ");
//        String[] s2Array = s2.trim().split(" ");
//        int n = Integer.valueOf(s1Array[0]);
//        int m = Integer.valueOf(s1Array[1]);
//        int[] score = new int[n];
//        for(int i = 0; i < n; i++){
//            score[i] = Integer.valueOf(s2Array[i]);
//        }
//        Arrays.sort(score);
//        int quitNum = n - m;
//
//        if(score[quitNum] == 0){
//            for(int i = quitNum; i < score.length; i++){
//                if(score[i] == 0){
//                    m--;
//                }else{
//                    break;
//                }
//            }
//        }else{
//            for(int i = quitNum; i > 0; i--){
//                if(score[i] == score[quitNum]){
//                    m++;
//                }else{
//                    break;
//                }
//            }
//        }
//        System.out.println(m);


//        System.out.println(3 < 3);

//        testString ss = new testString();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String name1 = br.readLine();
//
//        System.out.println("请输入数据");
//        String name = scanner.nextLine();
//        String[] dd = ss.permutation2("abc");
        List<String> ll  = ss.restoreIpAddresses("25525511135");
        ll.forEach(System.out :: println);

//        System.out.println(ss.myPow(2.0, -10));
//        String s = "abc";
////        String[] ll = new String[]{"2", "1", "+", "3", "*"};
//        System.out.println(ss.countSubstrings(s));


        List<String> list = new ArrayList<>();
        Collections.reverse(list);
//        list.add("1");
//        list.add("2");
//        for(String s:list){
//            if("2".equals(s)){
//                list.remove("2");
//            }
//        }
//
//        for(String s:list){
//            System.out.println(s);
//        }
//        System.out.println(ss.function(name));
    }
}


