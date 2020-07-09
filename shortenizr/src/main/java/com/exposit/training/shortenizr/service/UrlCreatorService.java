package com.exposit.training.shortenizr.service;

import com.exposit.training.shortenizr.model.Link;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class UrlCreatorService {

    private static MessageDigest MD5;
    private static String LatinAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public Link create(String sourceUrl) {
        byte[] digest = MD5.digest(sourceUrl.getBytes(StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < digest.length; i+=2) {
            int finalI = i;
            builder.append(LatinAlphabet.codePoints().anyMatch(v -> v == digest[finalI])
                    ? digest[i]
                    : String.valueOf(LatinAlphabet.charAt(Math.abs(digest[i] % LatinAlphabet.length())))
            );
        }
        Link link = new Link();
        link.setSourceUrl(sourceUrl);
        link.setKey(builder.toString());
        return link;
    }
}
