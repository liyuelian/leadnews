package com.li.minio.test;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.FileInputStream;

/**
 * @Author: liyuelian
 * @Date: 2024/7/15 16:51
 * @Description: 把 basic.html文件上传到 minio中，并可以在浏览器中访问
 **/
public class MinIOTest {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("d:\\basic.html");

        //1.获取minio的链接 创建一个minio客户端
        MinioClient minioClient = MinioClient.builder()
                .credentials("root", "12345678")
                .endpoint("http://139.159.245.79:9000")
                .build();

        //2.上传
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .object("basic.html") //上传文件名
                .contentType("text/html")   //文件类型
                .bucket("leadnews")   //上传到哪一个桶
                .stream(fileInputStream, fileInputStream.getChannel().size(), -1).build();

        minioClient.putObject(putObjectArgs);

        //3.访问路径
        System.out.println("http://139.159.245.79:9000/leadnews/basic.html");
    }


}
