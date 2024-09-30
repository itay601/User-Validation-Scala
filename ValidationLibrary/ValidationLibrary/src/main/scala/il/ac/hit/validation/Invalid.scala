package il.ac.hit.validation

class Invalid(reason: String) extends ValidationResult {
  override def isValid: Boolean = false
  override def getReason: Option[String] = Some(reason)
}
