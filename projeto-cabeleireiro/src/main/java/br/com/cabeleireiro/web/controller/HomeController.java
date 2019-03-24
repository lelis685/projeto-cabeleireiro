package br.com.cabeleireiro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	 @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	    public ModelAndView login(){
		    System.out.println("login");
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("login");
	        return mv;
	    }


	 
	 

}
