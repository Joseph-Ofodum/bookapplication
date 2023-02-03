package com.example.bookapplication.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


    @ConfigurationProperties(prefix = "rsa")
    @Getter
    @Setter
    public class RSAKeyProperties {
        private RSAPublicKey publicKey;
        private RSAPrivateKey privateKey;
    }


