package camandedit.server.workspace.application.command;


import camandedit.server.global.common.SelfValidator;
import java.util.HashSet;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CreateWorkSpaceCommand extends SelfValidator<CreateWorkSpaceCommand> {

  @NotNull
  private Long userId;

  @NotBlank
  @Size(min = 2, max = 10)
  private String name;

  private MultipartFile file;

  public CreateWorkSpaceCommand(Long userId, String name, MultipartFile file) {
    this.userId = userId;
    this.name = name;
    this.file = file;
    validationFile(file);
    this.validationSelf();
  }

  private void validationFile(MultipartFile file) {
    if (file == null) {
      throw new ConstraintViolationException(new HashSet<>());
    }

    if (file.getSize() == 0) {
      throw new ConstraintViolationException(new HashSet<>());
    }
  }

}
