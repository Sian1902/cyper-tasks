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
        BigInteger bigM = BigInteger.valueOf(m);

        BigInteger C1=bigAlpha.pow(k).mod(bigQ);
        BigInteger K=bigY.pow(k).mod(bigQ);
        BigInteger C2 = K.multiply(bigM).mod(bigQ);

        return List.of(C1.longValue(), C2.longValue());
    }


    public int decrypt(int c1, int c2, int x, int q) {
        BigInteger bigC1 = BigInteger.valueOf(c1);
        BigInteger bigC2 = BigInteger.valueOf(c2);
        BigInteger bigQ = BigInteger.valueOf(q);



        BigInteger K=bigC1.pow(x).mod(bigQ);


        int kInv= rsa.extendedEuclidean(q,K.intValue()).getKey();
        BigInteger KInverse = BigInteger.valueOf(kInv);
        BigInteger M = bigC2.multiply(KInverse).mod(bigQ);

        return M.intValue();
    }


}
