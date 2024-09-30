package il.ac.hit.validation

/**
 * Trait representing the result of a validation operation.
 */
trait ValidationResult {
  /**
   * Checks if the validation result is valid.
   *
   * @return `true` if the validation is valid, `false` otherwise.
   */
  def isValid: Boolean

  /**
   * Retrieves the reason for the validation failure, if any.
   *
   * @return `Some(reason)` if the validation is invalid, `None` otherwise.
   */
  def getReason: Option[String]
}