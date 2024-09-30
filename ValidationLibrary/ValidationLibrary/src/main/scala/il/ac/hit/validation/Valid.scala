package il.ac.hit.validation

class Valid extends ValidationResult {
  override def isValid: Boolean = true
  override def getReason: Option[String] = None
}
