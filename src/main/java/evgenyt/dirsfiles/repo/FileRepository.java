package evgenyt.dirsfiles.repo;

import evgenyt.dirsfiles.FileInfo;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileInfo, Long> {
}
