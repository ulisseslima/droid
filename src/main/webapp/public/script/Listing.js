ENTITY_NAME = "listing";

/**
 * @param event The element that holds all elements with properties of the Event object.
 */
function add(entity) {
	if (entity.className.indexOf("listing") != -1) {
		if (entity.querySelector(".name").value) {
			var listing = entity;
			doSubmit(listing, "add", function (response) {
				var savedListing = response.content;
				if (savedListing) {
					listing.id = "listing-"+savedListing.id;
					$qs(listing, ".id", savedListing.id);
					$qs(listing, ".link", savedListing.id);
					$qs(listing, ".name", savedListing.name);
					$qs(listing, ".description", savedListing.description);
				}
			});
		}
	} else {
		if (entity.querySelector(".participant-name").value) {
			var participant = entity;
			doSubmit(participant, "addparticipant", function (response) {
				var savedUser = response.content;
				if (savedUser) {
					participant.id = "participant-"+savedUser.id;
				}
			});
		}
	}
}

/**
 * Reorder Event elements according to the value of their priority element.
 * @see Entities.reorder();
 */
function extend_reorder(a, b) {
	var av = $qs(a, ".name");
    var bv = $qs(b, ".name");
    if (av > bv) return -1;
    else if (av < bv) return 1;
    else  return 0;
}

function showParticipants() {
	
}