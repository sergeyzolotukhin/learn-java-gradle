package ua.in.sz.spring;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


public class HelloWorldController extends AbstractController {


    private HelloWorldDao helloWorldDao;



    public void setHelloWorldDao(HelloWorldDao helloWorldDao) {
        this.helloWorldDao = helloWorldDao;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Get hello request");

        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("msg", "hello world");

        helloWorldDao.doTask();

        return model;
    }



}