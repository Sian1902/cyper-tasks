package Security;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.Map.Entry;

public class RSA {
    public Entry<Integer, Boolean> extendedEuclidean(int tutiont, int e) {
        int a1 = 1, a2 = 0, a3 = tutiont;
        int b1 = 0, b2 = 1, b3 = e;

        while (true) {
            if (b3 == 0) {
                return new AbstractMap.SimpleEntry<>(a3, false); // GCD
            }
            if (b3 == 1) {
                int inverse = (b2 % tutiont + tutiont) % tutiont;
                return new AbstractMap.SimpleEntry<>(inverse, true);  // Inverse
            }

            int q = a3 / b3;

            int t1 = a1 - q * b1;
            int t2 = a2 - q * b2;
            int t3 = a3 - q * b3;

            a1 = b1;
            a2 = b2;
            a3 = b3;
            b1 = t1;
            b2 = t2;
            b3 = t3;
        }
    }


    public int encrypt(int p, int q, int M, int e) {
        BigInteger bigP = BigInteger.valueOf(p);
        BigInteger bigQ = BigInteger.valueOf(q);
        BigInteger n = bigP.multiply(bigQ);
        BigInteger message = BigInteger.valueOf(M);
        BigInteger cipher = message.pow(e).mod(n);
        return cipher.intValue();
    }


    public int decrypt(int p, int q, int C, int e) {
        int tutiont = (p - 1) * (q - 1);

        Entry<Integer, Boolean> result = extendedEuclidean(tutiont, e);

        if (!result.getValue()) {
            throw new ArithmeticException("No modular inverse found — e and φ(n) are not coprime");
        }

        int d = result.getKey();




        BigInteger bigC = BigInteger.valueOf(C);
        BigInteger n = BigInteger.valueOf((long) p * q);


        BigInteger message = bigC.pow(d).mod(n);
        return message.intValue();
    }

}
