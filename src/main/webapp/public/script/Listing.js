ENTITY_NAME = "listing";

/**
 * @param event The element that holds all elements with properties of the Event object.
 */
function add(listing) {
	if (listing.querySelector(".title").value) {
		doSubmit(listing, "add", function (response) {
			var savedListing = response.content;
			if (savedListing) {
				listing.id = "listing-"+savedListing.id;
				$qs(listing, ".id", savedListing.id);
				$qs(listing, ".link", savedListing.id);
				$qs(listing, ".title", savedListing.title);
				$qs(listing, ".description", savedListing.description);
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
