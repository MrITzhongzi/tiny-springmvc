package cn.haitaoss.tinyspringmvc.webappProject.controller;

import cn.haitaoss.tinyioc.beans.annotation.Autowired;
import cn.haitaoss.tinyioc.beans.annotation.Controller;
import cn.haitaoss.tinyspringmvc.framework.annotation.RequestMapping;
import cn.haitaoss.tinyspringmvc.framework.annotation.ResponseBody;
import cn.haitaoss.tinyspringmvc.framework.modelAndView.Model;
import cn.haitaoss.tinyspringmvc.framework.modelAndView.ModelAndView;
import cn.haitaoss.tinyspringmvc.webappProject.service.PersonService;

import javax.servlet.http.HttpServletRequest;

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

    // http://localhost:8080/web/mvc/person/sleep.do?time=100
    @RequestMapping("sleep.do")
    public String sleep(int time, Model model) {
        System.out.println("i sleep " + time + " hours");
        model.put("time", time);
        return "success";
    }

    // http://localhost:8080/web/mvc/person/sleep2.do?time=100
    @RequestMapping("sleep2.do")
    public ModelAndView sleep2(int time, ModelAndView mv) {
        System.out.println("i sleep " + time + " hours");
        mv.setView("success");
        return mv;
    }

    // http://localhost:8080/web/mvc/person/redirect.do
    @RequestMapping("redirect.do")
    public String redirect(HttpServletRequest request) {
        return "redirect:" + request.getContextPath() + "/notice/error.jsp";
    }

    // http://localhost:8080/web/mvc/person/json.do
    @ResponseBody
    @RequestMapping("json.do")
    public Baby json() {
        return new Baby("json", 1, 7.6f);
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
