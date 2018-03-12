package middlepermutation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Collectors;

public class MiddlePermutation {
    
    public static String findMidPerm(String s) {
        
        s = s.chars().sorted()
                .mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
        BigInteger length = BigInteger.valueOf(s.length());
        BigInteger factorial = factorial(length);
        BigInteger position = factorial.divide(BigInteger.valueOf(2));
        StringBuilder midPerm = new StringBuilder();
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < s.length(); i++){
            BigInteger step = factorial.divide(length);
            int letPos = new BigDecimal(position).setScale(0)
                    .divide(new BigDecimal(step).setScale(0), 
                            BigDecimal.ROUND_UP).intValue();
            midPerm.append(sb.charAt(letPos - 1));
            sb.deleteCharAt(letPos - 1);
            position = position.add(step.multiply(BigInteger.valueOf((letPos - 1))).negate());
            length = BigInteger.valueOf(sb.length());
            factorial = factorial(length);
        }
        
        return midPerm.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(MiddlePermutation.findMidPerm("abc"));
    }

    private static BigInteger factorial(BigInteger n) {
        if(n.compareTo(BigInteger.valueOf(0)) == 1) 
            return n.multiply(factorial(n.add(BigInteger.valueOf(-1))));
        else
            return BigInteger.valueOf(1);
    }
    
}


