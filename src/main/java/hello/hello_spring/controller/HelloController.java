package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //static
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    //MVC와 템플릿 엔진
    //모델뷰와 컨트롤러로 쪼개기
    //렌더링이 된 html을 클라이언트에게 보내준다
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) { //required default는 true
        model.addAttribute("name", name);
        return "hello-template";
    }

    //api
    @GetMapping("hello-string")
    @ResponseBody //http의 바디부에 이 data를 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring"
    }

    //api
    //json 방식으로 나타남
    //@ResponseBody
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //처음으로 문자가 아닌 객체를 넣은 모습
    }

    static class Hello {
        private String name;
        //generate getter and setter
        //private이니까 게터세터가 필요함
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        //객체이면 JsonConverter
        //단순문자면 StringConverter
    }
}
