function Request() {}

/**
 * Iterates over all inputs (whose class contains "property") of the given element,
 * adding each name and value from the inputs.
 * @param formId Id of the element that contains the properties to send.
 * @param action Desired action URL. Will use the forms name if not specified.
 */
function doSubmit(form, action, callback) {
	var request = new Request();
	var actionName = getActionName(action, form); 
	
	$(form).children(".property").each(function(i) {
		if (this.name && this.value) {
			request[this.name] = this.value;
		}
	});
	
	$.getJSON("./" + actionName, request, function(response) {
		if (callback) {
			callback(response);
		}
	});
}

function scroll(containerId) {
	var container = document.getElementById(containerId);
	$.getJSON("./scroll", null, function(response) {
		$.each(response.contents, function(i, item) {
			var div = document.createElement("DIV");
			div.innerHTML = item.title;
			container.appendChild(div);
		});
	});
}

function getActionName(action, form) {
	if (action) return action;

	else if (form && form.getAttribute("name")) {
		return form.getAttribute("name");
	} else {
		console.log("Missing action name");
	}
}