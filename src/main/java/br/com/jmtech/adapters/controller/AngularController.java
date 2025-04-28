package br.com.jmtech.adapters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {

    @RequestMapping(value = {"/{path:^(?!api|health|static|index\\.html|favicon\\.ico).*}", "/**/{path:^(?!api|health|static|index\\.html|favicon\\.ico).*}"})
    public String redirect() {
        return "forward:/index.html";
    }
}
