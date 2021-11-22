package nowcder;

import java.util.Arrays;
import java.util.Scanner;

public class testAlgorithm {

    public static int function(int m, int x, int y, int[] num){
        int res = Integer.MAX_VALUE;
        for(int i = x; i <= y; i++){
            if(m - i > y) continue;
            res = Math.min(res, num[i - 1]);
        }
        return res == Integer.MAX_VALUE? -1:res;
    }

//    public static int[] function1(int x, String xx, int y, String yy){
//        int[] res = new int[y];
//        char[] xx_char = xx.toCharArray();
//        char[] yy_char = yy.toCharArray();
//
//        int index = 0;
//        for(int i = 0; i < yy_char.length; i++){
//            if(yy_char[i] == 'M'){
//                for(int j = 0; j < xx_char.length; j++){
//                    if(xx_char[j] == '0'){
//                        xx_char[j] = '1';
//                    }
//                }
//            }else{
//
//            }
//        }
//        return res;
//    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();

        int[] nums = new int[m];
        for(int i = 0; i < m; i ++) {
            nums[i] = sc.nextInt();  // 一个一个读取
        }
        Arrays.sort(nums);
        System.out.println(function(m, x, y, nums));

//        Scanner sc = new Scanner(System.in);
//        int total = sc.nextInt();
//        for(int i = 0; i < total; i++){
//            int x = sc.nextInt();
//            String xx = sc.nextLine();
//            int y = sc.nextInt();
//            String yy = sc.nextLine();
//            Arrays.stream(function1(x, xx, y, yy)).forEach(System.out :: println);
//        }
    }
}
