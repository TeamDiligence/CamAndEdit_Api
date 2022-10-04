package camandedit.server.global.common;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

public abstract class SelfValidator<T> {

  private final Validator validator;

  public SelfValidator() {
    this.validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  protected void validationSelf() {
    Set<ConstraintViolation<T>> violations = validator.validate((T) this);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
