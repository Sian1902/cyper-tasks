package Security;

import java.math.BigInteger;
import java.util.List;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {

        BigInteger bigAlpha=BigInteger.valueOf(alpha);
        BigInteger bigQ=BigInteger.valueOf(q);


        BigInteger ya=bigAlpha.pow(xa);
        ya=ya.mod(bigQ);

        BigInteger yb=bigAlpha.pow(xb);
        yb=yb.mod(bigQ);

        BigInteger ka=yb.pow(xa);
        ka=ka.mod(bigQ);

        BigInteger kb=ya.pow(xb);
        kb=kb.mod(bigQ);



        return List.of(ka.intValue(),kb.intValue());
    }
}
