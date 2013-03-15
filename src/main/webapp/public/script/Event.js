var refreshTimerId = setInterval(refresh, 5000);
var focusedEventTitle = "";

function refresh() {
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
					outdatedEvent.querySelector(".title").value = event.title;
					outdatedEvent.querySelector(".priority").value = event.priority;
					outdatedEvent.querySelector(".description").value = event.description;
				} else {
					var newEvent = $("#events").children(":first").clone(true);
					newEvent[0].id = "event-"+event.id;
					newEvent[0].querySelector(".id").value = event.id;
					newEvent[0].querySelector(".title").value = event.title;
					newEvent[0].querySelector(".priority").value = event.priority;
					newEvent[0].querySelector(".description").value = event.description;
					newEvent.appendTo("#events");
				}
			}
		  } else if (response.indexOf("!doctype") != -1) {
			document.location.reload(true);
		  }
	  },
	  error: function(jqXHR, textStatus, errorThrown) {
		  document.location.reload(true);
	  }
	});
}

$(document).ready(function () {
	$(".event-draft").bind("focus", newEvent);
	
    $(".event-input").bind("blur", function () {
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
function vote(input, times) {
	var previousValue = input.value? parseInt(input.value) : 0;
	var newValue = previousValue + times;
	input.value = newValue;
	reorder();
	var event = input.parentNode;
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
				event.querySelector(".id").value = savedEvent.id;
				event.querySelector(".title").value = savedEvent.title;
				event.querySelector(".priority").value = savedEvent.priority;
				event.querySelector(".description").value = savedEvent.description;
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
	$("input").removeClass("event-draft");
	$("input").off("focus");
	clone[0].querySelector(".priority").value = "0";
	clone.appendTo("#events");
}

/**
 * Reorder Event elements according to the value of their priority element.
 */
function reorder() {
	var events = $("#events");
	var event = events.children(".event");
	event.detach().sort(function (a, b) {	
		return parseInt(a.querySelector(".priority").value) < parseInt(b.querySelector(".priority").value);
	});
	events.append(event);	
}