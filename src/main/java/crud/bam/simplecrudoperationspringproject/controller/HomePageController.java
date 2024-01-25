package crud.bam.simplecrudoperationspringproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("")
    public String displayHomePage() {
        return "index";//Note: We need to  create the index html file to return this logical view name
    }
}

