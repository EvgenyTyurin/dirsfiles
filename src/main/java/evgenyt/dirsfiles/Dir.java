package evgenyt.dirsfiles;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Dir {
    @Id
    private String path;
}
