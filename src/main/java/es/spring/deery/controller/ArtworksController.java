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
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class ArtworksController {

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

    @GetMapping("/artworks")
    public String artworks(Model model) {
        model.addAttribute("artworks", artworkRepository.findAll());
        return "artworks/artworks";
    }

    @GetMapping("/artworks-create")
    public String artworksCreate(Model model) {
        List<OC> ocs = ocRepository.findAll();
        model.addAttribute("ocs", ocs);

        return "artworks/artworks-create";
    }

    @GetMapping("/artworks-display")
    public String artworksDisplay(Model model,  @RequestParam("id") String id) {
        Artwork a = artworkRepository.getById(Integer.parseInt(id));
        List<OC> ocs = ocRepository.findAll();

        model.addAttribute("artwork", a);
        model.addAttribute("ocs", ocs);
        model.addAttribute("comments", a.getCommentsById());

        return "artworks/artworks-display";
    }

    @GetMapping("/artworks-edit")
    public String artworksEdit(Model model,  @RequestParam("id") String id) {
        Artwork a = artworkRepository.getById(Integer.parseInt(id));
        List<OC> ocs = ocRepository.findAll();
        model.addAttribute("artwork", a);
        model.addAttribute("ocs", ocs);

        return "artworks/artworks-create";
    }

    @GetMapping("/artworks-delete")
    public String artworksDelete(Model model, @RequestParam("id") String id) {
        Artwork a = artworkRepository.getById(Integer.parseInt(id));

        a.getArtworkOcsById().forEach(aocs -> artworkOCsRepository.delete(aocs));
        a.getCommentsById().forEach(c -> commentRepository.delete(c));

        artworkRepository.delete(a);

        return "redirect:artworks";
    }

    @PostMapping("/artworks-save")
    public String artworksSave(HttpServletRequest request,
                               @RequestParam("id") String id,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam(value = "ocs-in-artwork", required = false) List<String> ocsInArtwork)
    {
        boolean edit = id != null && !id.isEmpty();
        Artwork a = edit ? artworkRepository.findById(Integer.parseInt(id)).get() : new Artwork();
        a.setTitle(title);
        a.setDescription(description);
        a.setCreatorByCreatorUserbdId(creatorRepository.findById(1).get());

        if (!edit)
            a.setDate(new Date(new java.util.Date().getTime()));

        if (edit) {
            for (ArtworkOcs artworkOcs : a.getArtworkOcsById()) {
                artworkOCsRepository.delete(artworkOcs);
            }
        }

        a.setArtworkOcsById(new LinkedList<>());
        if (ocsInArtwork != null && !ocsInArtwork.isEmpty()) {
            for (String ocId : ocsInArtwork) {
                ArtworkOcs artworkOcs = new ArtworkOcs();
                artworkOcs.setArtworkByArtworkId(a);
                artworkOcs.setArtworkId(a.getId());
                artworkOcs.setOriginalCharacterId(Integer.parseInt(ocId));

                OC oc = ocRepository.findById(Integer.parseInt(ocId)).get();
                artworkOcs.setOriginalCharacterByOriginalCharacterId(oc);

                artworkOCsRepository.save(artworkOcs);
                a.getArtworkOcsById().add(artworkOcs);
            }
        }

        if (!edit)
            a.setId(null);

        if (file != null && !file.isEmpty()) {
            try {
                a.setImg(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Edit or Create
        artworkRepository.save(a);

        // Display saved changes
        if (edit)
            return "redirect:artworks-display?id=" + id;

        return "redirect:artworks";
    }

    @PostMapping("/artworks-comment")
    public String artworksComment(Model model,
                                  @RequestParam("id") String id,
                                  @RequestParam("user-id") String userId,
                                  @RequestParam("comment") String commentStr)
    {
        Comment comment = new Comment();
        User u = "".equals(userId) ? null : userRepository.findById(Integer.parseInt(userId)).get();
        Artwork a = artworkRepository.findById(Integer.parseInt(id)).get();

        comment.setDate(new Date(new java.util.Date().getTime()));
        comment.setText(commentStr);
        comment.setArtworkByArtworkId(a);
        comment.setUserbdByUserbdId(u);

        commentRepository.save(comment);

        return "redirect:artworks-display?id=" + id;
    }
}
