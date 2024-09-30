package il.ac.hit.validation

/**
 * Concrete implementation of `ValidationResult` representing a valid result.
 */
class Valid extends ValidationResult {
  override def isValid: Boolean = true
  override def getReason: Option[String] = None
}