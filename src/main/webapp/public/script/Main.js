busy = false;
function Request() {}
DEBUG = true;

$(document).ready(function () {
    $(".auto-width").bind("keypress", autoWidth);
});

function autoWidth() {
	if (this.value) {
		this.style.width = ((this.value.length + 1) * 8) + "px";
	} else {
		this.style.width = "10px";
	}
}

function debug(msg, obj) {
	if(DEBUG) {
		var objStr = "";
		if(obj) objStr = JSON.stringify(obj);
		console.log(msg + objStr);
	}
}

function post(action, request, onSuccess) {
	debug("sent: ", request);
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
    var object = obj.querySelector(selector);
	if (value) {
		switch (object.tagName) {
			case "A" : object.setAttribute("href", value); break;
			default: object.value = value;
		}		
	} else {
		if (object) {
	        switch (object.tagName) {
	            case "A" : return object.getAttribute("href");
	            default: {
	                value = object.value;
			        return isNaN(value)? value : parseInt(value);
	            }
	        }
		}
	}
}

function fillParent(request, child) {
	var parent = child.parentNode;
	if (parent) { 
		var parentForm = parent.querySelector(".parent");
		if (parentForm) {
			request["parent"] = form2js(parentForm);
		}
	}
}
/**
 * Iterates over all inputs (whose class contains "property") of the given element,
 * adding each name and value from the inputs.
 * @param formId Id of the element that contains the properties to send.
 * @param action Desired action URL. Will use the forms name if not specified.
 */
function doSubmit(form, actionName, callback) {
	if (form.id) {
		var request = form2js(form.id);
		var action = getActionName(actionName, form); 
		
		post(action, request, function(response) {
			if (callback) callback(response);
		});
	} else {
		console.log("Element to submit has no id: " + form.className);
	}
}

function getActionName(action, form) {
	if (action) return "./"+action;

	else if (form && form.getAttribute("name")) {
		return "./"+form.getAttribute("name");
	} else {
		console.log("Missing action name");
	}
}

/**
 * @param name this is the class that is declared before the desired class name.
 * @param allClasses this is the object's className.
 * @returns the name of the class that is next to "name" in the list of "allClasses". 
 */
function classAfter(name, allClasses) {
	var classArr = allClasses.split(" ");			
	
	for (var i in classArr) {					
		if (classArr[i] == name) {
			var j = parseInt(i)+1;
			return classArr[j];					
		}
	}
	return undefined;
}

function path(url) {
	return $("#url-context")[0].value + url;
}

/**
 * Marks a selection as being of a certain class, with the option of providing an exception selector, and removing a certain class.
 * @param className class name to add.
 * @param selector target elements.
 * @param exceptionSelector exclusion elements.
 * @param notClassName if specified, this class will be removed.
 */
function markAs(className, selector, exceptionSelector, notClassName) {
	var collection = $(selector);
	if (exceptionSelector) collection=collection.not(exceptionSelector);
	
	collection.addClass(className);
	if (notClassName) collection.removeClass(notClassName);
}
