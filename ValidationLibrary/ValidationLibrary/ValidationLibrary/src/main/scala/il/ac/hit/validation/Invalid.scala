package il.ac.hit.validation

/**
 * Concrete implementation of `ValidationResult` representing an invalid result.
 *
 * @param reason the reason for the validation failure.
 */
class Invalid(reason: String) extends ValidationResult {
  override def isValid: Boolean = false
  override def getReason: Option[String] = Some(reason)
}