// -*- coding: utf-8 -*-

import java.math.BigInteger;
import java.util.Random;

public class EPP
{
    public static void main(String[] args)
    {
        long t1, t2;
        BigInteger n = new BigInteger("170141183460469231731687303715884105727", 10);

        System.out.print("Le nombre " + n);
        if (est_probablement_premier(n))
            System.out.println(" est très probablement premier!");
        else
            System.out.println(" n'est absolument pas premier!");

        int sommeTentatives = 0;
        double sommeTemps = 0;
        BigInteger[] results;
        int nbIter = 10;

        for(int i=0; i<nbIter; i++) {
            t1 = System.nanoTime() / 1000000;
            results = getPrimeNumber();
            BigInteger prime = results[0];
            t2 = System.nanoTime() / 1000000;
            sommeTentatives += results[1].intValue();
            sommeTemps += (double)(t2 - t1);
        }
        System.out.println("Nombre de tentative moyenne : " + sommeTentatives/nbIter);
        System.out.println("Temps moyens : " + sommeTemps/nbIter + "ms");
    }

    static boolean est_probablement_premier(BigInteger n)
    {
        /*
          50 = 15 * (ln(10)/ln(2))
        */
        return n.isProbablePrime(50);
    }

    static BigInteger[] getPrimeNumber(){
        Random rand = new Random();
        BigInteger[] prime = new BigInteger[2];
        prime[0] = new BigInteger(512,  rand);
        int nbTentatives = 1;
        while(!est_probablement_premier(prime[0])) {
            prime[0] = new BigInteger(512,  rand);
            nbTentatives++;
        }
        System.out.println("Nombre trouvé : " + prime[0]);
        System.out.println("Nombre de tentatives : " + nbTentatives);
        prime[1] = new BigInteger(Integer.toString(nbTentatives));
        return prime;
    }

}
/*
    Pour 10 executions :
    Nombre de tentative moyenne : 291
    Temps moyens : 25.3ms
 */
/*
  $ make
  javac *.java
  $ java EPP
  Le nombre 170141183460469231731687303715884105727 ...
*/
