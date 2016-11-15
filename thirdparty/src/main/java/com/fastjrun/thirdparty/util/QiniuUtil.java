package com.fastjrun.thirdparty.util;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuUtil {

    Auth auth;

    private String ak;

    private String sk;

    private String bucket;

    private String domain;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public void init() {
        auth = Auth.create(ak, sk);
    }

    /**
     * 生成上传token
     * 
     * @param expires
     *            有效时长，单位秒。默认3600s
     * @param policy
     *            上传策略的其它参数，如 new StringMap().put("endUser", "uid")。 scope通过
     *            bucket、key间接设置，deadline 通过 expires 间接设置
     * @return 生成的上传token
     */
    public String uploadToken(long expires) {
        StringMap policy = new StringMap();
        policy.putNotEmpty("returnBody", "{\"key\":$(key),\"bucket\":$(bucket)}");
        return auth.uploadToken(this.bucket, null, expires, policy);
    }

    /**
     * 根据Key生成下载url
     * 
     * @param key
     *            资源名
     * @return 生成的文件url
     */
    public String getDownUrl(String key) {
        StringBuffer urlSB = new StringBuffer("http://" + this.domain);
        urlSB.append("/").append(key);
        String url = urlSB.toString();
        // 指定时长为1天
        String urlSigned = auth.privateDownloadUrl(url, 3600 * 24);
        return urlSigned;
    }

    public void upload(String filePath, String key) {
        String uploadToken = this.uploadToken(3600l);
        UploadManager uploadManager = new UploadManager();
        try {
            uploadManager.put(filePath, key, uploadToken);
        } catch (QiniuException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
