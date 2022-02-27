package day04;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class PairFinder {
//    public int findPairs(int[] arr){
//        Map<Integer,Integer> pairs = new TreeMap<>();
//        for (int i: arr){
//            if (pairs.containsKey(i)){
//                pairs.put(i,1);
//            }else{
//                pairs.put(i,pairs.get(i)+1);
//            }
//        }
//        return pairs.values().stream().mapToInt(i->i/2).sum();
//
//    }
    public int findPairs(int[] arr){
        int[] result= Arrays.copyOf(arr,arr.length);
        Arrays.sort(result);
        int count = 0;
        for (int i = 1; i<result.length; i++){
            if (result[i]==result[i-1]){
                count++;
                i++;
            }
        }
        return count;
    }
}
