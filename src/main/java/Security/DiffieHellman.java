package Security;

import java.math.BigInteger;
import java.util.List;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {
        BigInteger bigXa=BigInteger.valueOf(xa);
        BigInteger bigXb=BigInteger.valueOf(xb);
        BigInteger bigAlpha=BigInteger.valueOf(alpha);
        BigInteger bigQ=BigInteger.valueOf(q);

        BigInteger ya=bigAlpha.modPow(bigXa,bigQ);
        BigInteger yb=bigAlpha.modPow(bigXb,bigQ);

        BigInteger ka = yb.modPow(bigXa, bigQ);
        BigInteger kb = ya.modPow(bigXb, bigQ);


        return List.of(ka.intValue(),kb.intValue());
    }
}
