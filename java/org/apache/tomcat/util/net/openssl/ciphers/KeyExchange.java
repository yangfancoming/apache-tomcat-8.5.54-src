

package org.apache.tomcat.util.net.openssl.ciphers;

enum KeyExchange {
    EECDH /* SSL_kEECDH - ephemeral ECDH */,
    RSA   /* SSL_kRSA   - RSA key exchange */,
    DHr   /* SSL_kDHr   - DH cert, RSA CA cert */ /* no such ciphersuites supported! */,
    DHd   /* SSL_kDHd   - DH cert, DSA CA cert */ /* no such ciphersuite supported! */,
    EDH   /* SSL_kDHE   - tmp DH key no DH cert */,
    PSK   /* SSK_kPSK   - PSK */,
    FZA   /* SSL_kFZA   - Fortezza */  /* no such ciphersuite supported! */,
    KRB5  /* SSL_kKRB5  - Kerberos 5 key exchange */,
    ECDHr /* SSL_kECDHr - ECDH cert, RSA CA cert */,
    ECDHe /* SSL_kECDHe - ECDH cert, ECDSA CA cert */,
    GOST  /* SSL_kGOST  - GOST key exchange */,
    SRP   /* SSL_kSRP   - SRP */,
    RSAPSK,
    ECDHEPSK,
    DHEPSK,
    ANY   /* TLS 1.3 */
}
