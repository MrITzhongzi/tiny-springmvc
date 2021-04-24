package cn.haitaoss.tinyspringmvc.webappProject.service;

import cn.haitaoss.tinyioc.beans.annotation.Service;
import cn.haitaoss.tinyioc.beans.annotation.Value;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 15:36
 *
 */
@Service
public class Person {
    @Value("黄大宁的第一次注入")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
