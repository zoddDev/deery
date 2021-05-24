package es.spring.deery.controller;

import antlr.StringUtils;
import es.spring.deery.entity.Artwork;
import es.spring.deery.repository.ArtworkRepository;
import es.spring.deery.repository.CreatorRepository;
import es.spring.deery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
public class MainController {

    private UserRepository userRepository;

    private ArtworkRepository artworkRepository;

    private CreatorRepository creatorRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setArtworkRepository(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @Autowired
    public void setCreatorRepository(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/xd")
    public String displayUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "input";
    }
}
