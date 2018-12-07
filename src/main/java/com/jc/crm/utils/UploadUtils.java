package com.jc.crm.utils;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * @author asuis
 */
public class UploadUtils {
    public static String getQiniuUploadToken(String fileKey) {
        String accessKey = "u69bNuR9AMsDD2gM_qSg1n4mw6z5ncEk4EZtLaaH";
        String secretKey = "ddHSAHwhvIGX6QxHrSSVaY-b6nL8YhxLHNWn9oWO";
        String bucket = "asuis-oss";
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", "http://asuis.mynatapp.cc/blog/logo/callback");
        putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        putPolicy.put("callbackBodyType", "application/json");
        long expireSeconds = 3600;
        return auth.uploadToken(bucket, fileKey, expireSeconds, putPolicy);
    }
}
