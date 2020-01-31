// -*- coding: utf-8 -*-

import java.math.BigInteger;
import java.util.Random;

public class RSA_raw {
    private static BigInteger code, codeChiffré, codeDéchiffré ;
    private static BigInteger n ;      // Le module de la clef publique
    private static BigInteger e ;      // L'exposant de la clef publique
    private static BigInteger d ;      // L'exposant de la clef privée

    static void fabrique() {           // Fabrique d'une paire de clefs RSA (A MODIFIER)
        Random rand = new Random();
        BigInteger p, q, w;
        n = new BigInteger("196520034100071057065009920573", 10);
        e = new BigInteger("7", 10);
        d = new BigInteger("56148581171448620129544540223", 10);

        do {
            do {
                p = new BigInteger(1024, 50, rand);
                q = new BigInteger(1024, 50, rand);
            } while (p.compareTo(q) == 0);
            n = p.multiply(q);
            w = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
            System.out.println("n = " + n);
            System.out.println("w = " + w);

            do {
                d = new BigInteger(w.bitLength(), rand).add(BigInteger.ONE);
            } while (d.compareTo(w.subtract(BigInteger.ONE)) > 0 || !d.gcd(w).equals(BigInteger.ONE));
            e = d.modInverse(w);
        } while(!p.subtract(BigInteger.ONE).gcd(e).equals(BigInteger.ONE) && !q.subtract(BigInteger.ONE).gcd(e).equals(BigInteger.ONE));

        System.out.println("e = " + e);

    }

    static void fabriqueEnRegle() {           // Fabrique d'une paire de clefs RSA (A MODIFIER)
        Random rand = new Random();
        BigInteger p, q, w;
        n = new BigInteger("196520034100071057065009920573", 10);
        e = new BigInteger("65537", 10);
        d = new BigInteger("56148581171448620129544540223", 10);

        do {
            do {
                p = new BigInteger(1024, 50, rand);
                q = new BigInteger(1024, 50, rand);
            } while (p.compareTo(q) == 0);
            n = p.multiply(q);
            w = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
        } while(!e.gcd(w).equals(BigInteger.ONE));

        d = e.modInverse(w);

        System.out.println("n = " + n);
        System.out.println("w = " + w);
        System.out.println("e = " + e);
    }

    public static void main(String[] args) {
        code = new BigInteger("4b594f544f", 16);

        /* Affichage du code clair */
        System.out.println("Code clair        : " + code);

        fabriqueEnRegle();

        /* Affichage des clefs utilisées */
        System.out.println("Clef publique (n) : " + n);
        System.out.println("Clef publique (e) : " + e);
        System.out.println("Clef privée (d)   : " + d);

        /* On effectue d'abord le chiffrement RSA du code clair avec la clef publique */
        codeChiffré = code.modPow(e, n);
        System.out.println("Code chiffré      : " + codeChiffré);

        /* On déchiffre ensuite avec la clef privée */
        codeDéchiffré = codeChiffré.modPow(d, n);
        System.out.println("Code déchiffré    : " + codeDéchiffré);
    }
}