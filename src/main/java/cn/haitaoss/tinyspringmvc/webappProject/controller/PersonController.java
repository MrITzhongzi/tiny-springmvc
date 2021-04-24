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

    @RequestMapping("eat.do")
    public void eating() {
        System.out.println("I am eating!");
    }

    /**
     * 这个还不能测试，没有实现参数的分析
     * @author haitao.chen
     * email
     * date 2021/4/24 8:26 下午
     * @param age
     */
    @RequestMapping("speak.do")
    public void speak(int age) {
        System.out.println("i am " + age + "years old");
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
