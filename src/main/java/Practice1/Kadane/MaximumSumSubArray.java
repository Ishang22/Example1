package Practice1.Kadane;

public class MaximumSumSubArray {

    public int maxSubArray(int[] a) {
        int n = a.length;
        int sum = a[0], answer = a[0];
        for(int i=1; i<a.length; i++){
            sum += a[i];
            if(sum < a[i]) sum = a[i];
            answer = Math.max(answer, sum);
        }
        return answer;
    }

}
