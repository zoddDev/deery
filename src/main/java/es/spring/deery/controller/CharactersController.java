package es.spring.deery.controller;

import es.spring.deery.entity.*;
import es.spring.deery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CharactersController {

    private UserRepository userRepository;

    private ArtworkRepository artworkRepository;

    private CreatorRepository creatorRepository;

    private OCRepository ocRepository;

    private ArtworkOCsRepository artworkOCsRepository;

    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setArtworkOCsRepository(ArtworkOCsRepository artworkOCsRepository) {
        this.artworkOCsRepository = artworkOCsRepository;
    }

    @Autowired
    public void setOcRepository(OCRepository ocRepository) {
        this.ocRepository = ocRepository;
    }

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

    @GetMapping("/characters")
    public String characters(Model model) {
        model.addAttribute("characters", ocRepository.findAll());
        return "characters/characters";
    }

    @GetMapping("/characters-create")
    public String charactersCreate(Model model) {
        List<OC> ocs = ocRepository.findAll();
        model.addAttribute("ocs", ocs);

        return "characters/characters-create";
    }

    @PostMapping("/characters-save")
    public String charactersSave(HttpServletRequest request,
                               @RequestParam("id") String id,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description)
    {
        boolean edit = id != null && !id.isEmpty();
        OC oc = edit ? ocRepository.findById(Integer.parseInt(id)).get() : new OC();
        oc.setName(name);
        oc.setDescription(description);
        oc.setCreatorByCreatorUserbdId(creatorRepository.findById(1).get());

        if (!edit)
            oc.setDate(new Date(new java.util.Date().getTime()));

        if (!edit)
            oc.setId(null);

        if (file != null && !file.isEmpty()) {
            try {
                oc.setImg(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Edit or Create
        ocRepository.save(oc);

        return "redirect:characters";
    }

    @GetMapping("/characters-edit")
    public String charactersEdit(Model model, @RequestParam("id") String id) {
        OC oc = ocRepository.getById(Integer.parseInt(id));
        model.addAttribute("oc", oc);

        return "characters/characters-create";
    }

    @GetMapping("/characters-delete")
    public String charactersDelete(Model model, @RequestParam("id") String id) {
        OC oc = ocRepository.getById(Integer.parseInt(id));

        oc.getArtworkOcsById().forEach(aocs -> artworkOCsRepository.delete(aocs));
        ocRepository.delete(oc);

        return "redirect:characters";
    }

    @GetMapping("/characters-display")
    public String characetrsDisplay(Model model,  @RequestParam("id") String id) {
        OC oc = ocRepository.getById(Integer.parseInt(id));

        model.addAttribute("oc", oc);
        model.addAttribute("comments", oc.getCommentsById());

        return "characters/characters-display";
    }

    @PostMapping("/characters-comment")
    public String charactersComment(Model model,
                                  @RequestParam("id") String id,
                                  @RequestParam("user-id") String userId,
                                  @RequestParam("comment") String commentStr)
    {
        Comment comment = new Comment();
        User u = "".equals(userId) ? null : userRepository.findById(Integer.parseInt(userId)).get();
        OC oc = ocRepository.findById(Integer.parseInt(id)).get();

        comment.setDate(new Date(new java.util.Date().getTime()));
        comment.setText(commentStr);
        comment.setOriginalCharacterByOriginalCharacterId(oc);
        comment.setUserbdByUserbdId(u);

        commentRepository.save(comment);

        return "redirect:characters-display?id=" + id;
    }
}
