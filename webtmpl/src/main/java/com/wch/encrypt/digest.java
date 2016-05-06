package com.wch.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by weichunhe on 2016/4/29.
 */
public class Digest {
    private static Logger log = LoggerFactory.getLogger(Digest.class);

    public static void sha1() {
        String sha1 = DigestUtils.sha1Hex("spring");
        log.info("sha1摘要:{}->{}", "spring", sha1);

    }

    public static void md5(){
        String md5 = DigestUtils.md5Hex("spring");
        log.info("md5摘要:{}->{}","spring",md5);
    }

    public static void main(String[] args) {
        sha1();
        md5();
    }
}
