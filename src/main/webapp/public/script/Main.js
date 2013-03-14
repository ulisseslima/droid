function Request() {}

/**
 * Iterates over all inputs (whose class contains "property") of the given element,
 * adding each name and value from the inputs.
 * @param formId Id of the element that contains the properties to send.
 * @param action Desired action URL. Will use the forms name if not specified.
 */
function doSubmit(form, actionName, callback) {
	var request = new Request();
	var action = getActionName(actionName, form); 
	
	$(form).children(".property").each(function(i) {
		if (this.name && this.value) {
			request[this.name] = this.value;
		}
	});
	
	$.ajax({
	  type: "POST",
	  url: action,
	  data: request,
	  success: function(response) {
		if (callback) {
			callback(response);
		}
	  }
	});
}

function getActionName(action, form) {
	if (action) return "./"+action;

	else if (form && form.getAttribute("name")) {
		return "./"+form.getAttribute("name");
	} else {
		console.log("Missing action name");
	}
}