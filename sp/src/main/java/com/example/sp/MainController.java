package com.example.sp;

import com.example.sp.model.User;
import com.example.sp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage(ModelAndView model){
        model.setViewName("loginpage");
//        List<EmployeeEntity> user=employeeDao.getUsers();
//        model.addObject("user",user);
        return  "loginpage";
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView model){
        ModelAndView   mode=new ModelAndView(("index"));
//        List<EmployeeEntity> user=employeeDao.getUsers();
//        model.addObject("user",user);
        return  mode;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUsers(ModelAndView model,  @PathVariable("id") int id){
        return  "UserAccount";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelAndView model, HttpServletRequest req){
        req.getSession().invalidate();
        return  "/";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView m){
        m.setViewName("register");
        return  m;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public String saveUser(@ModelAttribute("user") User user){
        ModelAndView mode=new ModelAndView();
        userService.save(user);
        mode=new ModelAndView(new RedirectView(""));
        return  "redirect:/";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET )
    public ModelAndView getUserById(HttpServletRequest req, @RequestParam("username") String username, @RequestParam("password") String password) throws ServletException, IOException {
        ModelAndView mode=new ModelAndView();
        System.out.println(req.getParameter("email"));
        System.out.println(req.getParameter("password"));
        User user = userService.checkLogin(username, password);
        if (user!=null) {
            HttpSession session = req.getSession(false);
            session.setAttribute("user", user);
            mode=new ModelAndView(new RedirectView("/index"));

        }else{
            mode=new ModelAndView(new RedirectView("/register"));
        }
            return mode;
    }
}
