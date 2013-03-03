$(document).ready(function () {
	$(".event-draft").bind("focus", newEvent);
	
    $(".event-input").bind("blur", function () {
		add(this.parentNode);
    });
    
    $(".event-vote").bind("click", function () {
    	var $event = $(this).closest(".event");
    	var priority = $event.find(".priority")[0];
        if (this.className.indexOf("vote-up") !== -1) {
            vote(priority, +1);
        } else {
            vote(priority, -1);
        }
        add($event[0]);
	});
    
    $(".priority").bind("input", reorder);
});

function vote(input, times) {
	var previousValue = input.value? parseInt(input.value) : 0;
	var newValue = previousValue + times;
	input.value = newValue;
	reorder();
}

function add(event) {
	if (event.querySelector(".title").value) {
		doSubmit(event, "add", function (response) {
			var savedEvent = response.content;
			event.id = "event-"+savedEvent.id;
			event.querySelector(".id").value = savedEvent.id; 
			event.querySelector(".title").value = savedEvent.title;
			event.querySelector(".priority").value = savedEvent.priority;
			event.querySelector(".description").value = savedEvent.description;
		});
	}
}

function newEvent() {
	var clone = $(this.parentNode).clone(true);		
	$(this.parentNode).removeClass("transparent");
	$("input").removeClass("event-draft");
	$("input").off("focus");    
	clone.appendTo("#events");
}

function reorder() {	
	var events = $("#events");
	var event = events.children(".event");
	event.detach().sort(function (a, b) {	
		return a.querySelector(".priority").value < b.querySelector(".priority").value;
	});
	events.append(event);	
}