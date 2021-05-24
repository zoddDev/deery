package es.spring.deery.controller;

import es.spring.deery.entity.Artwork;
import es.spring.deery.entity.ArtworkOcs;
import es.spring.deery.entity.OC;
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
import java.util.LinkedList;
import java.util.List;

@Controller
public class ArtworksController {
    private UserRepository userRepository;

    private ArtworkRepository artworkRepository;

    private CreatorRepository creatorRepository;

    private OCRepository ocRepository;

    private ArtworkOCsRepository artworkOCsRepository;

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

    @PostMapping("/artworks-save")
    public String artworksSave(HttpServletRequest request,
                               @RequestParam("id") String id,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam("ocs-in-artwork") List<String> ocsInArtwork)
    {
        boolean edit = id != null && !id.isEmpty();
        Artwork a = edit ? artworkRepository.findById(Integer.parseInt(id)).get() : new Artwork();
        a.setTitle(title);
        a.setDescription(description);
        a.setCreatorByCreatorUserId(creatorRepository.findById(1).get());

        if (edit) {
            for (ArtworkOcs artworkOcs : a.getArtworkOcsById()) {
                artworkOCsRepository.delete(artworkOcs);
            }
        }

        a.setArtworkOcsById(new LinkedList<>());

        for (String ocId : ocsInArtwork) {
            ArtworkOcs artworkOcs = new ArtworkOcs();
            artworkOcs.setArtworkByArtworkId(a);
            artworkOcs.setArtworkId(a.getId());
            artworkOcs.setOriginalcharacterId(Integer.parseInt(ocId));

            OC oc = ocRepository.findById(Integer.parseInt(ocId)).get();
            artworkOcs.setOriginalCharacterByOriginalcharacterId(oc);

            artworkOCsRepository.save(artworkOcs);
            a.getArtworkOcsById().add(artworkOcs);
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

        return "redirect:artworks";
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
        artworkRepository.delete(a);

        return "redirect:artworks";
    }
}
