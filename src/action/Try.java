package action;

/**
 * Created by xu on 2017/3/5.
 */
public class Try {

    public static void main(String[] args) {
        int[] A = {2,5,1,1,1,1,4,1,7,3,7};
        System.out.println(resolve(A));
//        System.out.println(test(A,2,7,9));
    }

    static boolean resolve(int[] A) {
        if (A.length >= 7){
            for(int m1 = 1 ; m1 <= (A.length - 6); m1++){
                for(int m2 = m1+2;m2 <= (A.length - 4);m2++){
                    for (int m3 = m2+2;m3 <= (A.length - 2);m3++){
                        boolean test = test(A,m1,m2,m3);
                        if (test == true){
                            return true;
                        }
                    }
                }
            }
            return false;
        }else {
            return false;
        }
    }



    static boolean test(int[] A,int m1,int m2,int m3){
        boolean result = false;
        long sum1 = 0;
        long sum2 = 0;
        long sum3 = 0;
        long sum4 = 0;
        for(int i = 0 ; i < A.length ; i++){
            int test = A[i];
            if (i<m1){
                sum1 += test;
                continue;
            }else if (i < m2 && i != m1){
                sum2 += test;
                continue;
            }else if (i < m3 && i != m1 && i != m2){
                sum3 += test;
                continue;
            }else if (i < A.length && i != m3 && i != m2  && i != m1){
                sum4 += test;
                continue;
            }
        }
        if (sum1 == sum2 && sum1 == sum3 && sum1 == sum4){
            result = true;
        }else {
            result = false;
        }
        return result;

    }
}
