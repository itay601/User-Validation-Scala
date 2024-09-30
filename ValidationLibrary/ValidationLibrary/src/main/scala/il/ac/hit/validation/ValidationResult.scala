package il.ac.hit.validation

trait ValidationResult {
  def isValid: Boolean
  def getReason: Option[String]
}
