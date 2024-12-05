package sg.edu.nus.iss.vttp_day18.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.vttp_day18.model.SessionData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller //Serving only the ui the website
@RequestMapping("/sessions")
public class SessionController {

    @GetMapping(path = {"/home", ""})
    public String showHome() {
        return "home";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    public String showSessions(HttpSession httpSession, Model model) {

        //List of sessionData
        List<SessionData> sessionsList = null;

        if (httpSession.getAttribute("session") == null) {
            sessionsList = new ArrayList<>();
        } else {
                        //Cast to List<SessionData> object
            sessionsList = (List<SessionData>) httpSession.getAttribute("session");
            //In the httpsession, there is an attribute called session
            //session stores a list of sessionData(name,dob)
        }

        //Save into model so that we can retrieve and show on html side
        model.addAttribute("sessionsList", sessionsList);
        return "sessionlist";
    }

    //when directed to /create form
    //show form only
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        SessionData sessionData = new SessionData();
        model.addAttribute("session", sessionData);
        return "sessioncreate";
    }
    

    //When submit form on /home/create
    @SuppressWarnings("unchecked")
    @PostMapping("/create")
    public String postFromCreateForm(@ModelAttribute("session") SessionData sessionObject, HttpSession httpSession, Model model){
        List<SessionData> sessions = null;

        if(httpSession.getAttribute("session") == null){
            sessions = new ArrayList<>();
        } else {
            sessions = (List<SessionData>) httpSession.getAttribute("session");
        }

        sessions.add(sessionObject);

        //Save into session
        httpSession.setAttribute("session", sessions);

        //Save into the model for retriving to display on html
        model.addAttribute("sessions", sessions);

        return "sessionlist";
    }

    @GetMapping("/clear")
    public String clearSession(HttpSession httpSession) {

        //Clear the httpsession session attribute
        httpSession.removeAttribute("session");
        httpSession.invalidate();

        return "redirect:/sessions/home";
    }
    
    
}
