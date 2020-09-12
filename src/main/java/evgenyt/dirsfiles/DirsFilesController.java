package evgenyt.dirsfiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DirsFilesController {

    private DirRepository dirRepository;
    private FileRepository fileRepository;

    public DirsFilesController(DirRepository dirRepository, FileRepository fileRepository) {
        this.dirRepository = dirRepository;
        this.fileRepository = fileRepository;
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
        dir.setFilesInfo();
        dirRepository.save(dir);
        fileRepository.saveAll(dir.getFiles());
        return "redirect:dirsfiles";
    }

    @GetMapping("filesinfo")
    public String getFilesInfo(@RequestParam(value = "dir_id") String dirId, Model model) {
        Long dir_id = Long.valueOf(dirId);
        System.out.println("Files info for dir with id=" + dir_id);
        Optional<Dir> dirResult = dirRepository.findById(dir_id);
        if (!dirResult.isPresent()) {
            System.out.println("No dir in database!");
            return "redirect:dirsfiles";
        }
        Dir dir = dirResult.get();
        model.addAttribute("filelist", dir.getFiles());
        return "filesinfo";
    }

}
