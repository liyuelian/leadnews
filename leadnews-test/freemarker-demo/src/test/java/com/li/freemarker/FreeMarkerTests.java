package com.li.freemarker;

import com.li.freemarker.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liyuelian
 * @Date: 2024/7/15 14:51
 * @Description:
 **/
@SpringBootTest(classes = FreeMarkerDemoApplication.class)
@RunWith(SpringRunner.class)
public class FreeMarkerTests {
    @Autowired
    private Configuration configuration;

    @Test
    public void test() throws IOException, TemplateException {

        Template template = configuration.getTemplate("01-basic.ftl");
        /**
         * 合成方法
         * 参数1：模型数据
         * 参数2：输出流
         */
        template.process(getData(), new FileWriter("d:/basic.html"));
    }

    public Map getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "FreeMarker");
        //2.实体类相关的参数
        Student student = new Student();
        student.setName("李华");
        student.setAge(18);
        map.put("stu", student);
        return map;
    }
}
