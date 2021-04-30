import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumArray {

    ArrayList<Integer> list = new ArrayList<Integer>();

    public NumArray(){}


    public NumArray(int[] nums){
        for (int i:nums){
            list.add(i);
        }
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int n = i; n <=j; n++){
            sum += list.get(n);
        }
        return sum;
    }

    public int[] countBits(int num) {
        int[] sum = new int[num + 1];
        int n;
        for(int i = 1; i <= num; i++){
            n = i;
            while (n != 0){
                if (n%2 == 1) sum[i]++;
                n /= 2;
            }
        }
        return sum;
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int n = -1;
        int sum = 0;
        if (ruleKey == "type"){
            n = 0;
        } else if (ruleKey == "color") {
            n = 1;
        } else if (ruleKey == "name") {
            n = 2;
        } else {
            return sum;
        }
        for(List list:items){
            if(list.get(n).equals(ruleValue)) sum++;
        }
        return sum;
    }

    public static void main(String[] args){
        NumArray nn = new NumArray();
        List<List<String>> ll = new ArrayList<>();
        ll.add(new ArrayList<String>(){{add("phone");add("blue");add("pixel");}});
        ll.add(new ArrayList<String>(){{add("computer");add("silver");add("phone");}});
        ll.add(new ArrayList<String>(){{add("phone");add("gold");add("iphone");}});


        int n = nn.countMatches(ll, "color", "silver");
        System.out.println(n);
//        int[] nums = {-2, 0, 3, -5, 2, -1};
//        NumArray nn = new NumArray(nums);
//        System.out.println(nn.sumRange(0, 2));
//        System.out.println(nn.sumRange(2, 5));
    }
}
