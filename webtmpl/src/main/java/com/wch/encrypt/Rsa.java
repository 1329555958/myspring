package com.wch.encrypt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by weichunhe on 2016/4/29.
 */
public class Rsa {
    public static void main(String[] args) throws Exception {
        KeyFactory factory = KeyFactory.getInstance("RSA");
        byte[] bytes = Base64
                .getDecoder()
                .decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1gr+rIfYlaNUNLiFsK/Knb54nQrCRTRMOdujTwkpqKLo4pNYj2StmryWETeFxOCFtCt/7ixTUrU2RGGjkIOlYC3144h0dJKDtPXw9+mFyW1VwWvtfoiSUeKTEbz1tSHghEcdEvVq6qlSQukiLAEZabiwfEE30TQ6g979X6YXhnQIDAQAB");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        PublicKey publicKey = factory.generatePublic(spec);

        byte[] pirvateBytes = Base64
                .getDecoder()
                .decode("MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBALWCv6sh9iVo1Q0uIWwr8qdvnidCsJFNEw526NPCSmooujik1iPZK2avJYRN4XE4IW0K3/uLFNStTZEYaOQg6VgLfXjiHR0koO09fD36YXJbVXBa+1+iJJR4pMRvPW1IeCERx0S9WrqqVJC6SIsARlpuLB8QTfRNDqD3v1fpheGdAgMBAAECgYEArmr1w3zfCxOxpvitJUUV589aKl/rS7TEmyGomdQZrel1CPlczRXinsmvQ3OTLzjA5geNNCpx2eyunL7YDF+T2WgcvpPlM+TUrBnTI9gKYwLXiWPuWou8oZolHaTuQQuLfnJTZ+6cYjHA3S4xnrJaEvvs8xgy6/UAJWXUpm/dQAECQQDlW9LPOrWaaVFuZqftVTwhTjhVmBZ/AeuXcmmmF2KBGaujbXJuVHVE5rzW9/6TQoWinuK4J/UOLcFl3VTTEPgZAkEAypggJwgYq3uz0KX8YScHV/gUZa56l5gRofMrIpiek4uvt2JM1kqgIq9T9PIOnv7dz7buRk2GXi3GLgEuhDr2JQJBAJPAypZ7QMBfdnkDosyOqzTdegcR+fQJ3aZrq0m3KNr4GY0nlZ8jw4QGjMKDcjmVkhdH+dAe1Ywzx7ICmoF6HgkCQQCTo+lKiIvx7GROWahi5J5lbVTwBQcyEpBHBX8Z5z8pJ1MWwXxdbmTk4gC9MOmW1QWwqg9bDIQvfgw+2n2bv5xBAkEAhUaiDfOywrIB3VUZWTqeIlVfqXHd8a9AcellTgIjK8WIu9gNGIfkWUVoVeOq0GCFTVqwO5tlac+oeDHCCimLyQ==");
        PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(pirvateBytes);
        PrivateKey privateKey = factory.generatePrivate(privateSpec);

        //matrix-utils jar
//        byte[] encode = RSA.encrypt("wch".getBytes(), privateKey);
//        System.out.println(Base64.getEncoder().encodeToString(encode));
//        System.out.println(new String(RSA.decrypt(encode, publicKey)));
//
//        byte[] publicEncode = RSA.encrypt("pubic".getBytes(), publicKey);
//        System.out.println(Base64.getEncoder().encodeToString(publicEncode));
//        System.out.println(new String(RSA.decrypt(publicEncode, privateKey)));
//
//        byte[] sign = RSA.sign("wch".getBytes(), privateKey);
//        System.out.println(Base64.getEncoder().encodeToString(sign));
//
//        System.out.println(RSA.verify("wch".getBytes(), sign, publicKey));

    }
}
