package org.project.Project.Controller;


import java.util.List;


import org.project.Project.models.Post;
import org.project.Project.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class homeController {

    

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "index";
    }   
}
