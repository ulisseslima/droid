var refreshTimerId = setInterval(refresh, 5000);
var focusedEventTitle = "";

function refresh() {
	if (!busy) {
		busy = true;
		console.log("busy!");
		var request = new Request();
		request["lastUpdate"] = new Date().getTime();
		if (focusedEventTitle) {
			request["focusedEventTitle"] = focusedEventTitle;
		}
	
		$.ajax({
		  type: "POST",
		  url: "./refresh",
		  data: request,
		  success: function(response) {
			  if (response.contents) {
				for (var i in response.contents) {
					var event = response.contents[i];
					var outdatedEvent = $("#event-"+event.id)[0];
					if (outdatedEvent) {
						$qs(outdatedEvent, ".title", event.title);
						$qs(outdatedEvent, ".priority", event.priority);
						$qs(outdatedEvent, ".description", event.description);
					} else {
						var newEvent = $("#events").children(":first").clone(true);
						newEvent[0].id = "event-"+event.id;
						$qs(newEvent[0], ".id", event.id);
						$qs(newEvent[0], ".title", event.title);
						$qs(newEvent[0], ".priority", event.priority);
						$qs(newEvent[0], ".description", event.description);
						newEvent.appendTo("#events");
					}
				}
			  } else if (response.indexOf("!doctype") != -1) {
				document.location.reload(true);
			  }
			  console.log("completed refresh");
			  console.log("!busy");
			  busy = false;
		  },
		  error: function(jqXHR, textStatus, errorThrown) {
			  document.location.reload(true);
		  }
		});
	} else {
		console.log("busy...");
	}
}

$(document).ready(function () {
	$(".draft").children(".title").bind("focus", newEvent);
	
    $(".property").bind("blur", function () {
		add(this.parentNode);
    });
    $(".event-input").bind("focus", function () {
    	focusedEventTitle = this.parentNode.querySelector(".title").value;
    });
    
    $(".event-vote").bind("click", function () {        
        var priority = this;
        vote(priority, +1);
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
 * @param event The element that holds all elements with properties of the Event object.
 */
function add(event) {
	if (event.querySelector(".title").value) {
		doSubmit(event, "add", function (response) {
			var savedEvent = response.content;
			if (savedEvent) {
				event.id = "event-"+savedEvent.id;
				$qs(event, ".id", savedEvent.id);
				$qs(event, ".title", savedEvent.title);
				$qs(event, ".priority", savedEvent.priority);
				$qs(event, ".description", savedEvent.description);
			}
		});
	}
}

/**
 * Creates a new Event element.
 */
function newEvent() {
	var clone = $(this.parentNode).clone(true);
	$(this.parentNode).removeClass("transparent");
	$("input").parent().removeClass("draft");
	$("input").off("focus");
	clone.children(".priority").value = "0";
	clone.appendTo("#events");
}

/**
 * Reorder Event elements according to the value of their priority element.
 */
function reorder() {
	var events = $("#events");
	var event = events.children(".event");
	event.detach().sort(function (a, b) {	
		var av = $qsInt(a, ".priority");
        var bv = $qsInt(b, ".priority");
        if (av > bv) return -1;
        else if (av < bv) return 1;
        else {
            var at = $qs(a, ".title");
            var bt = $qs(b, ".title");
            if (at > bt) return 1;
            else if (at < bt) return -1;
            else return 0;
        }
	});
	events.append(event);	
}