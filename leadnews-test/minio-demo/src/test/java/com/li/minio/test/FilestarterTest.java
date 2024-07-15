package com.li.minio.test;

import com.li.file.service.FileStorageService;
import com.li.minio.MinIOApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: liyuelian
 * @Date: 2024/7/15 19:12
 * @Description:
 **/
@SpringBootTest(classes = MinIOApplication.class)
@RunWith(SpringRunner.class)
public class FilestarterTest {
    @Autowired
    private FileStorageService fileStorageService;

    //将basic.html上传到minio中，并可以通过浏览器访问
    @Test
    public void test() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("d:\\basic.html");

        String filePath = fileStorageService.uploadHtmlFile("liyuelian", "basic.html", fileInputStream);
        System.out.println(filePath);
    }

}
