ENTITY_NAME = "event";

function fill(event, savedEvent) {
	event.id = "event-"+savedEvent.id;
	$qs(event, ".id", savedEvent.id);
	$qs(event, ".parent\\.id", savedEvent.parent.id);
	$qs(event, ".title", savedEvent.title);
	$qs(event, ".priority", savedEvent.priority);
	$qs(event, ".description", savedEvent.description);
}

function refresh() {	
	var request = new Request();
	request["lastUpdate"] = new Date().getTime();
	if (currentEntityTitle && currentEntityTitle.value) {
		request["focusedEventTitle"] = currentEntityTitle.value;
		request["parentId"] = $(".parentId")[0].value;
	}

	$.ajax({
	  type: "POST",
	  url: "refresh",
	  data: request,
	  success: function(response) {
		  if (response.contents) {
			for (var i in response.contents) {
				var event = response.contents[i];
				var outdatedEvent = $("#event-"+event.id)[0];
				if (outdatedEvent) {
					fill(outdatedEvent, event);
				} else {
					var newEvent = $("#events").children(":last").clone(true);
					fill(newEvent[0], event);
					newEvent.appendTo("#events");
				}
			}
		  } else if (response.indexOf("!doctype") != -1) {
			document.location.reload(true);
		  }
		  setTimeout(refresh, 100);
	  },
	  error: function(jqXHR, textStatus, errorThrown) {
		  document.location.reload(true);
	  }
	});
}

$(document).ready(function () {
    $(".event-vote").bind("click", function () {        
        var priority = this;
        vote(priority, +1);
        vote(priority.parentNode.querySelector(".hidden-priority"), +1);
    });
});

/**
 * Increases the priority of an input by "times" times and causes the elements to be reordered.
 * @param input The component that holds the "priority" property value. 
 * @param times Increase the "priority" property by "times" times.
 */
function vote(priority, times) {
	var previousValue = priority.value? parseInt(priority.value) : 0;
	var newValue = previousValue + times;
	priority.value = newValue;
	reorder();
	var event = priority.parentNode;
	add(event);
}

/**
 * @param event The id of the element that holds all elements with properties of the Event object.
 */
function add(event) {
	if (event.querySelector(".title").value) {
		doSubmit(event, "addevent", function (response) {
			var savedEvent = response.content;
			if (savedEvent) {
				fill(event, savedEvent);
			}
		});
	}
}

/**
 * Creates a new Event element.
 * @see Entities.newEntity()
 */
function extend_newEntity(clone) {
	clone.children(".priority").value = "0";
}

/**
 * Reorder Event elements according to the value of their priority element.
 * @see Entities.reorder();
 */
function extend_reorder(a, b) {
	var av = $qs(a, ".priority");
    var bv = $qs(b, ".priority");
    if (av > bv) return -1;
    else if (av < bv) return 1;
    else {
        var at = $qs(a, ".title");
        var bt = $qs(b, ".title");
        if (at > bt) return 1;
        else if (at < bt) return -1;
        else return 0;
    }
}