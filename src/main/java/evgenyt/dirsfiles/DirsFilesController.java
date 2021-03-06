package evgenyt.dirsfiles;

import evgenyt.dirsfiles.repo.DirRepository;
import evgenyt.dirsfiles.repo.FileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Web actions controller
 */

@Controller
public class DirsFilesController {

    private final DirRepository dirRepository;
    private final FileRepository fileRepository;

    public DirsFilesController(DirRepository dirRepository, FileRepository fileRepository) {
        this.dirRepository = dirRepository;
        this.fileRepository = fileRepository;
    }

    @GetMapping("dirsfiles")
    public String getDirsFiles(Model model) {
        model.addAttribute(new Dir());
        ArrayList<Dir> dirList = new ArrayList<>();
        dirRepository.findAll().forEach(dirList::add);
        System.out.println("Found dirs: " + dirList.size());
        List<DirDisplay> displayList = new ArrayList<>();
        for (Dir dir : dirList){
            displayList.add(new DirDisplay(dir));
        }
        model.addAttribute("dirlist", displayList);
        return "dirsfiles";
    }

    @PostMapping("dirsfiles")
    public String postDirsFiles(Dir dir) {
        System.out.println("Dir posted: " + dir);
        if (!dir.getPath().equals("") && Files.exists(Paths.get(dir.getPath()))) {
            dir.setDateTime(LocalDateTime.now());
            dir.setFilesInfo();
            dirRepository.save(dir);
            fileRepository.saveAll(dir.getFiles());
        }
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
        List<FileDisplay> displayFiles = new ArrayList<>();
        for (FileInfo fileInfo : dir.getFiles())
            displayFiles.add(new FileDisplay(fileInfo));
        Collections.sort(displayFiles);
        model.addAttribute("filelist", displayFiles);
        model.addAttribute("dirname", dir.getPath());
        return "filesinfo";
    }

}
