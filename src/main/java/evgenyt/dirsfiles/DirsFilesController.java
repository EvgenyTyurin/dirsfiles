package evgenyt.dirsfiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class DirsFilesController {

    private DirRepository dirRepository;

    public DirsFilesController(DirRepository dirRepository) {
        this.dirRepository = dirRepository;
    }

    @GetMapping("dirsfiles")
    public String getDirsFiles(Model model) {
        model.addAttribute(new Dir());
        ArrayList<Dir> dirList = new ArrayList<>();
        dirRepository.findAll().forEach(dir -> dirList.add(dir));
        System.out.println("Found dirs: " + dirList.size());
        model.addAttribute("dirlist", dirList);
        return "dirsfiles";
    }

    @PostMapping("dirsfiles")
    public String postDirsFiles(Dir dir) {
        System.out.println("Dir posted: " + dir);
        dir.setDateTime(LocalDateTime.now());
        dirRepository.save(dir);
        return "redirect:dirsfiles";
    }

}