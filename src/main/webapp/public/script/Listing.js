ENTITY_NAME = "listing";

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
				$qs(event, ".description", savedEvent.description);
			}
		});
	}
}

/**
 * Reorder Event elements according to the value of their priority element.
 * @see Entities.reorder();
 */
function extend_reorder(a, b) {
	var av = $qs(a, ".title");
    var bv = $qs(b, ".title");
    if (av > bv) return -1;
    else if (av < bv) return 1;
    else  return 0;
}