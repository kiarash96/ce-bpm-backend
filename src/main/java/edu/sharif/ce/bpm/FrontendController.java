package edu.sharif.ce.bpm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    // Forward any url starting with /forms and not containing a dot
    @RequestMapping("/forms/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/forms/index.html";
    }
}
