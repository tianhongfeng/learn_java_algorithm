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



    public static void main(String[] args) {
        testString ss = new testString();
        String s = "23";
//        String[] ll = new String[]{"2", "1", "+", "3", "*"};

        System.out.println(ss.letterCombinations(s));

    }
}


