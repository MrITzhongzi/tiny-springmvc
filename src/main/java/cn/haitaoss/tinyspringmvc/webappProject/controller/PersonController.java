package cn.haitaoss.tinyspringmvc.webappProject.controller;

import cn.haitaoss.tinyioc.beans.annotation.Autowired;
import cn.haitaoss.tinyioc.beans.annotation.Controller;
import cn.haitaoss.tinyspringmvc.framework.annotation.RequestMapping;
import cn.haitaoss.tinyspringmvc.webappProject.service.PersonService;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:20
 *
 */
@Controller
@RequestMapping("/mvc/person/")
public class PersonController {
    @Autowired
    private PersonService personService;

    // http://localhost:8080/web/mvc/person/eat.do
    @RequestMapping("eat.do")
    public void eating() {
        System.out.println("I am eating!");
    }

    // http://localhost:8080/web/mvc/person/speak.do?age=10
    @RequestMapping("speak.do")
    public void speak(int age) {
        System.out.println("i am " + age + "years old");
    }

    // http://localhost:8080/web/mvc/person/baby.do?age=10&name=haitao&babyName=haitaoSon&babyAge=10&weight=20
    @RequestMapping("baby.do")
    public void baby(Integer age, Baby baby, String name) {
        System.out.println(age);
        System.out.println(baby);
        System.out.println(name);
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
