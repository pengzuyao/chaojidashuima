package com.pzy.study.base.commons.utils;

import com.pzy.study.entity.UserEntity;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-25
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    public static final String HASHALGORITHMNAME = "md5";

    public static final int HASHITERATIONS = 2;

    public static void encryptPassword(UserEntity userEntity){
        userEntity.setSalt(randomNumberGenerator.nextBytes().toHex());
        String password = new SimpleHash(HASHALGORITHMNAME, userEntity.getPassword(), ByteSource.Util.bytes(userEntity.getSalt()), HASHITERATIONS).toHex();
        userEntity.setPassword(password);
    }
}
