busy = false;
function Request() {}

function post(action, request, onSuccess) {
	$.ajax({
	  "type": "POST",
	  "headers": { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
	  "url": action,
	  "contentType": "application/json",
	  "data": JSON.stringify(request),
	  "dataType" : "json",
	  "success": onSuccess
	});
}

/**
 * @param obj
 * @param selector
 * @param optional value. If set, will set instead of returning the value.
 * @returns the value of the result of the query selection.
 */
function $qs(obj, selector, value) {
	if (value) {
		obj.querySelector(selector).value = value;
	} else {
		value = obj.querySelector(selector).value;
		return isNaN(value)? value : parseInt(value);
	}
}

/**
 * Iterates over all inputs (whose class contains "property") of the given element,
 * adding each name and value from the inputs.
 * @param formId Id of the element that contains the properties to send.
 * @param action Desired action URL. Will use the forms name if not specified.
 */
function doSubmit(form, actionName, callback) {
	var request = form2js(form.id);
	console.log("sending " + JSON.stringify(request));
	var action = getActionName(actionName, form); 
	
	post(action, request, function(response) {
		if (callback) callback(response);
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