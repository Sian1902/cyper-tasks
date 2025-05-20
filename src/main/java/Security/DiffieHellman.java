package Security;

import java.math.BigInteger;
import java.util.List;

public class DiffieHellman {
    RSA rsa= new RSA();
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {

        BigInteger bigAlpha=BigInteger.valueOf(alpha);
        BigInteger bigQ=BigInteger.valueOf(q);
        BigInteger Xa=BigInteger.valueOf(xa);
        BigInteger Xb=BigInteger.valueOf(xb);

        BigInteger ya=rsa.newModPow(bigAlpha,Xa,bigQ);
        BigInteger yb=rsa.newModPow(bigAlpha,Xb,bigQ);
        BigInteger ka=rsa.newModPow(yb,Xa,bigQ);
        BigInteger kb=rsa.newModPow(ya,Xb,bigQ);

        return List.of(ka.intValue(),kb.intValue());
    }
}
