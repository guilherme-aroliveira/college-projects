function validadeForm() {

  // error fields
  var errorFields = [];

  // student form
  var studentForm = document.forms["studentForm"];

  // check first name
  var firstName = studentForm["firstName"].value.trim();
  if (firstName == "") {
    errorFields.push("First name");
  }

  // check last name
  var lastName = studentForm["lastName"].value.trim();
  if (lastName == "") {
    errorFields.push("Last name");
  }

  // check email
  var email = studentForm["email"].value.trim();
  if (email = "") {
    errorFields.push("Email");
  }

  if (theErrorFields.length > 0) {
		alert("Form validation failed. Please add data for following fields: " + theErrorFields);
		return false;
	}
}