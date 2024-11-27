package org.project.Project.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.project.Project.models.Account;
import org.project.Project.models.Authority;
import org.project.Project.models.Post;
import org.project.Project.services.AccountService;
import org.project.Project.services.AuthorityService;
import org.project.Project.services.PostService;
import org.project.Project.util.constants.Authorities;
import org.project.Project.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        for(Authorities auth: Authorities.values()){
            Authority authority= new Authority();
            authority.setId(auth.getAuthorityId());
            authority.setName(auth.getAuthorityString());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        account01.setEmail("cmihidis@gmail.com");
        account01.setFullName("Christos Michidis");
        account01.setPassword("password");
        account01.setPhone("1111111111");
        
        accountService.save(account01);

        Account account02 = new Account();
        account02.setEmail("gmihidis@gmail.com");
        account02.setFullName("Giannis Michidis");
        account02.setPassword("password");
        account02.setPhone("2222222222");
        
        accountService.save(account02);

        Account account03 = new Account();
        account03.setEmail("admin@gmail.com");
        account03.setFullName("Admin Administratorvich");
        account03.setPassword("password");
        account03.setPhone("1234567890");
        account03.setRole(Roles.ADMIN.getRole());
        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Authorities.RESET_ANY_USER_PASSWORD.getAuthorityId()).ifPresent(authorities::add);
        authorityService.findById(Authorities.ACCESS_ADMIN_PANEL.getAuthorityId()).ifPresent(authorities::add);
        account03.setAuthorities(authorities);

        accountService.save(account03);

        List<Post> posts = postService.getAll();
        if(posts.size()==0){
            Post post01 = new Post();
            post01.setTitle("post01");      
            post01.setBody("Post01 body......");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("post02");      
            post02.setBody("Post02 body......");
            post02.setAccount(account02);
            postService.save(post02);
        }
    }

}
