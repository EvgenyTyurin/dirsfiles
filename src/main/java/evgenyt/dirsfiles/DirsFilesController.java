package evgenyt.dirsfiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DirsFilesController {

    @GetMapping("dirsfiles")
    public String getDirsFiles(Model model) {
        model.addAttribute(new Dir());
        return "dirsfiles";
    }

    @PostMapping("dirsfiles")
    public String postDirsFiles(Dir dir) {
        System.out.println("Dir posted: " + dir);
        return "dirsfiles";
    }


}
