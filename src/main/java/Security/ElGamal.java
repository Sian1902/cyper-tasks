package Security;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.List;

public class ElGamal {
    RSA rsa= new RSA();

    public List<Long> encrypt(int q, int alpha, int y, int k, int m) {
        BigInteger bigQ = BigInteger.valueOf(q);
        BigInteger bigAlpha = BigInteger.valueOf(alpha);
        BigInteger bigY = BigInteger.valueOf(y);
        BigInteger bigK = BigInteger.valueOf(k);
        BigInteger bigM = BigInteger.valueOf(m);

        BigInteger C1 = bigAlpha.modPow(bigK, bigQ);
        BigInteger K = bigY.modPow(bigK, bigQ);
        BigInteger C2 = K.multiply(bigM).mod(bigQ);

        return List.of(C1.longValue(), C2.longValue());
    }


    public int decrypt(int c1, int c2, int x, int q) {
        BigInteger bigC1 = BigInteger.valueOf(c1);
        BigInteger bigC2 = BigInteger.valueOf(c2);
        BigInteger bigQ = BigInteger.valueOf(q);
        BigInteger bigX = BigInteger.valueOf(x);

        // Step 1: Compute K = c1^x mod q
        BigInteger K = bigC1.modPow(bigX, bigQ);

        int kInv= rsa.extendedEuclidean(q,K.intValue()).getKey();
        // Step 2: Compute K⁻¹ mod q (modular inverse)
        BigInteger KInverse = BigInteger.valueOf(kInv);

        // Step 3: M = (c2 * K⁻¹) mod q
        BigInteger M = bigC2.multiply(KInverse).mod(bigQ);

        return M.intValue(); // You can also return BigInteger if needed
    }


}
