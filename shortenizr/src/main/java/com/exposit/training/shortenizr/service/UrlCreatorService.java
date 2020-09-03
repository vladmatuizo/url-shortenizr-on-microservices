package com.exposit.training.shortenizr.service;

import com.exposit.training.shortenizr.model.Link;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class UrlCreatorService {

    private static final String ENCODING_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final MessageDigest md5;

    public UrlCreatorService(MessageDigest md5) {
        this.md5 = md5;
    }


    public Link create(String sourceUrl) {
        byte[] digest = md5.digest(sourceUrl.getBytes(StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < digest.length; i+=2) {
            int finalI = i;
            builder.append(ENCODING_ALPHABET.codePoints().anyMatch(v -> v == digest[finalI])
                    ? (char) digest[i]
                    : String.valueOf(ENCODING_ALPHABET.charAt(Math.abs(digest[i] % ENCODING_ALPHABET.length())))
            );
        }
        Link link = new Link();
        link.setSourceUrl(sourceUrl);
        link.setKey(builder.toString());
        return link;
    }
}
