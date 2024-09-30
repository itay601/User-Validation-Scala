package il.ac.hit.validation

trait UserValidation extends (User => ValidationResult) {
  def and(that: UserValidation): UserValidation = (user: User) => {
    val result1 = this(user)
    if (!result1.isValid) result1 else that(user)
  }

  def or(that: UserValidation): UserValidation = (user: User) => {
    val result1 = this(user)
    if (result1.isValid) result1 else that(user)
  }
}

object UserValidation {
  def all(validations: UserValidation*): UserValidation = (user: User) => {
    validations.map(_.apply(user)).find(!_.isValid).getOrElse(new Valid)
  }

  def none(validations: UserValidation*): UserValidation = (user: User) => {
    validations.map(_.apply(user)).find(_.isValid).map(res => new Invalid("One validation passed")).getOrElse(new Valid)
  }

  def emailEndsWithIL: UserValidation = (user: User) => {
    if (user.email.endsWith(".il")) new Valid
    else new Invalid("Email does not end with '.il'")
  }

  def emailLengthBiggerThan10: UserValidation = (user: User) => {
    if (user.email.length > 10) new Valid
    else new Invalid("Email must be longer than 10 characters")
  }

  def passwordLengthBiggerThan8: UserValidation = (user: User) => {
    if (user.password.length > 8) new Valid
    else new Invalid("Password must be longer than 8 characters")
  }

  def passwordIncludesLettersNumbersOnly: UserValidation = (user: User) => {
    if (user.password.forall(_.isLetterOrDigit)) new Valid
    else new Invalid("Password must include only letters and numbers")
  }

  def passwordIncludesDollarSign: UserValidation = (user: User) => {
    if (user.password.contains('$')) new Valid
    else new Invalid("Password must include the $ symbol")
  }

  def passwordIsDifferentFromUsername: UserValidation = (user: User) => {
    if (user.password != user.username) new Valid
    else new Invalid("Password cannot be the same as the username")
  }

  def ageBiggerThan18: UserValidation = (user: User) => {
    if (user.age > 18) new Valid
    else new Invalid("User must be older than 18")
  }

  def usernameLengthBiggerThan8: UserValidation = (user: User) => {
    if (user.username.length > 8) new Valid
    else new Invalid("Username must be longer than 8 characters")
  }
}
