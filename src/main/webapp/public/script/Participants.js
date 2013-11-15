var spacing = 50, margin = 60;

$(document).ready(function() { updatePeople(); });
$(window).resize(function() { moveEverybody(); });

/**
 * Updates the position of all online user's icons.
 */
function moveEverybody() {
	var offset = 0;
	var offsets = [];
	$(".online-user").each(function() {
			var by = this.dataset.focus;
			if ($("#"+by).length) {
				var position = $("#"+by).offset();
				if (!offsets[by]) offsets[by] = 0;
				$(this).css({top: position.top, left: position.left - (margin+offsets[by])});
				offsets[by] += spacing;
			}
	});
}

/**
 * Updates the position of the icon of the person logged in.
 */
function moveMe(node) {
	var position = $(node).offset();
	var left = position.left - margin;
	var newLeft = left;
		
	var collisions = getCollisions($("#me"), position.top, newLeft, $(".online-user"));
	newLeft = collisions["minx"] - spacing;
	
	$("#me").css({top: position.top, left: newLeft});
}

/**
 * request the list of online users that are currently on this page.
 */
function updatePeople(focusedElement) {
	debug("update people!");
	var request = new Request();
	request["lastUpdate"] = new Date().getTime();
	request["active"] = true;
	if (focusedElement) {
		request["element"] = focusedElement.id;
		me.dataset.focus = focusedElement.id;
	} else {
		request["element"] = me.dataset.focus;
	}
	request["userId"] = me.dataset.id;
	
	$.ajax({
	  type: "POST",
	  url: path("/user/updatePeople"),
	  data: request,
	  success: function(response) {
		  debug("updatePeople response: " + response.success);
		  if (response.contents) {
			  markAsGarbage();
			  for (var i in response.contents) {
				  var onlineUser = response.contents[i];
				  addOrUpdateOnlineUser(onlineUser);
			  }
			  removeGarbage();
			  moveEverybody();
		  }
		  setTimeout(updatePeople, 100);
	  },
	  error: function(jqXHR, textStatus, errorThrown) {
		  debug("people refresh error: "+errorThrown);
		  setTimeout(updatePeople, 5000);
	  }
	});
}

function markAsGarbage() {
	markAs("garbage", ".online-user", "#me");
}

function removeGarbage() {
	$(".garbage").remove();
}

function updateLocation(focusedElement, active) {
	var me = $("#me")[0];
	var request = new Request();
	request["active"] = active;
	if (focusedElement) {
		request["element"] = focusedElement.id;
		me.dataset.focus = focusedElement.id;
		debug("i'm focusing "+focusedElement.id);
	} else {
		request["element"] = me.dataset.focus;
	}
	request["userId"] = me.dataset.id;
	
	moveEverybody();
	
	$.ajax({
	  type: "POST",
	  url: path("/user/updateLocation"),
	  data: request
	});
}

/**
 * Checks if the user element already exists in the page. If it does, the element is updated with new information. A new element is created, otherwise.
 * @param user user object returned by the server.
 */
function addOrUpdateOnlineUser(user) {
	if (!user.id) return;
	
	var existingElement = $("#user-"+user.id);
	var update = false;
	if (existingElement.length) {
		update = true;
	}
	
	var div = update? existingElement[0] : document.createElement("DIV");
	div.id = "user-"+user.id;
	div.className = user.active? "active" : "idle";
	div.className += " online-user";
	div.dataset.focus = user.element? user.element : "nowhere-0";
	div.dataset.name = user.name;
	
	var img = update? existingElement.children("img")[0] : document.createElement("IMG");
	img.src = user.image;
	img.alt = user.name;
	
	if (!update) {
		div.appendChild(img);
		$("#online-user-collection").append(div);
		debug("created "+div.id);
	}
}