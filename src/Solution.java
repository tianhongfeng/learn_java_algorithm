import java.util.*;

public class Solution {
    /* Write Code Here */
//    public int solution(int n, String str) {
//        int res = 0;
//        for (int i = 0; i < str.length(); i++) {
//            for (int j = i + 2; j < str.length(); j++) {
//                if (help(str.substring(i, j + 1))) res++;
//            }
//        }
//        return res;
//    }
//
//    // S[i]=S[2n−i]=S[2n+i− 2]
//    public boolean help(String str) {
//        char[] ss = str.toCharArray();
//        int l = ss.length;
//        int n = (l + 2) / 3;
//        for (int i = 1; i <= n; i++) {
//            if (ss[i] != ss[2 * n - i] || ss[i] != ss[2 * n + i - 2]) return false;
//        }
//        return true;
//    }

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> vv = new ArrayList<>();

    public void track(int[] d, int idx, int sum) {
//        if (idx == d.length) {
//            if(sum % 3 == 0 && !path.isEmpty()){
//                ArrayList<Integer> tmp = new ArrayList<>(path);
//                Collections.sort(tmp, ((o1, o2) -> o2 - o1));
//                vv.add(tmp);
//            }
//            return;
//        }
        track(d, idx + 1, sum);
        path.add(d[idx]);
        sum += d[idx];
        track(d, idx + 1, sum);
        path.remove(path.size() - 1);
        sum -= d[idx];
    }

    public void helpsort(List<List<Integer>> vv){
        Collections.sort(vv, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if(o1.size() < o2.size()){
                    return 1;
                }else if(o1.size() == o2.size()){
                    for(int i = 0; i < o1.size(); i++){
                        if(o1.get(i) < o2.get(i)) {
                            return 1;
                        }
                        else if(o1.get(i) > o2.get(i)){
                            return -1;
                        }
                    }
                    return 0;
                }else{
                    return -1;
                }

            }
        });

    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.track(new int[]{3,6,7,5,9}, 0, 0);
        solution.helpsort(solution.vv);
        System.out.println();
    }

}

//static class Main {
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        int res;
//
//        int n;
//        n = Integer.parseInt(in.nextLine().trim());
//
//        String str;
//        try {
//            str = in.nextLine();
//        } catch (Exception e) {
//            str = null;
//        }
//
//        res = new Solution().solution(n, str);
//        System.out.println(String.valueOf(res));
//
//    }
//}