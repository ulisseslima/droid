/**
 * @param event The element that holds all elements with properties of the Event object.
 */
function addParticipant(participant) {
	if (participant.querySelector(".name").value) {
		doSubmit(participant, "add", function (response) {
			var savedParticipant = response.content;
			if (savedParticipant) {
				participant.id = "participant-"+savedParticipant.id;
				$qs(participant, ".id", savedParticipant.id);
				debug("set: "+savedParticipant.id + " got: "+ $qs(participant, ".id"));
				$qs(participant, ".link", savedParticipant.id);
				$qs(participant, ".name", savedParticipant.name);
			}
		});
	}
}
