package il.ac.hit.validation

/**
 * Demonstrates the usage of the `UserValidation` trait and its various validation functions.
 */
 
object Demo {
  def main(args: Array[String]): Unit = {
    // Original valid user
    val user1 = new User("haim", "haim.michael@gmail.il", "abc123", 33)

    // Check if the email ends with .il and the email length is bigger than 10
    val result1 = UserValidation.emailEndsWithIL.and(UserValidation.emailLengthBiggerThan10).apply(user1)
    println(s"Validation 1 - isValid: ${result1.isValid}, Reason: ${result1.getReason.getOrElse("No issue")}")

    // Check if either email ends with .il or email length is bigger than 10
    val result2 = UserValidation.emailEndsWithIL.or(UserValidation.emailLengthBiggerThan10).apply(user1)
    println(s"Validation 2 - isValid: ${result2.isValid}, Reason: ${result2.getReason.getOrElse("No issue")}")

    // Combine multiple validations: all must pass
    val combinedValidation = UserValidation.all(
      UserValidation.usernameLengthBiggerThan8,
      UserValidation.passwordLengthBiggerThan8,
      UserValidation.ageBiggerThan18
    ).apply(user1)
    println(s"Combined Validation - isValid: ${combinedValidation.isValid}, Reason: ${combinedValidation.getReason.getOrElse("No issue")}")

    // Edge Case: Empty username
    val user2 = new User("", "example@gmail.il", "strongPass123", 25)
    val emptyUsernameValidation = UserValidation.usernameLengthBiggerThan8.apply(user2)
    println(s"Empty Username Validation - isValid: ${emptyUsernameValidation.isValid}, Reason: ${emptyUsernameValidation.getReason.getOrElse("No issue")}")

    // Edge Case: Password shorter than 8 characters
    val user3 = new User("shortpass", "example@gmail.il", "short", 22)
    val shortPasswordValidation = UserValidation.passwordLengthBiggerThan8.apply(user3)
    println(s"Short Password Validation - isValid: ${shortPasswordValidation.isValid}, Reason: ${shortPasswordValidation.getReason.getOrElse("No issue")}")

    // Edge Case: Password does not contain letters and numbers only
    val user4 = new User("invalidUser", "test@gmail.com", "invalid$", 30)
    val invalidCharPasswordValidation = UserValidation.passwordIncludesLettersNumbersOnly.apply(user4)
    println(s"Invalid Char in Password Validation - isValid: ${invalidCharPasswordValidation.isValid}, Reason: ${invalidCharPasswordValidation.getReason.getOrElse("No issue")}")

    // Edge Case: Age less than 18
    val user5 = new User("youngUser", "young.user@gmail.il", "strongPassword123", 16)
    val ageValidation = UserValidation.ageBiggerThan18.apply(user5)
    println(s"Age Validation (under 18) - isValid: ${ageValidation.isValid}, Reason: ${ageValidation.getReason.getOrElse("No issue")}")

    // Edge Case: Password is the same as the username
    val user6 = new User("samePassword", "same.password@gmail.il", "samePassword", 25)
    val passwordSameAsUsernameValidation = UserValidation.passwordIsDifferentFromUsername.apply(user6)
    println(s"Password Same as Username Validation - isValid: ${passwordSameAsUsernameValidation.isValid}, Reason: ${passwordSameAsUsernameValidation.getReason.getOrElse("No issue")}")

    // Edge Case: Invalid email format (doesn't end with .il)
    val user7 = new User("testUser", "test.user@gmail.com", "ValidPass123$", 30)
    val emailValidation = UserValidation.emailEndsWithIL.apply(user7)
    println(s"Email Validation (not ending with .il) - isValid: ${emailValidation.isValid}, Reason: ${emailValidation.getReason.getOrElse("No issue")}")
  }
}
