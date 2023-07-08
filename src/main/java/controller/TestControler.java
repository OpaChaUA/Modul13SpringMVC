package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller

public class TestControler {
    @RequestMapping(value = "/time", method = {RequestMethod.GET})
    public ModelAndView getTime() {
        ModelAndView result = new ModelAndView("time/current");
        result.addObject("now", LocalDateTime.now());
        return result;
    }
}
