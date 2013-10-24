ENTITY_NAME = undefined;
currentEntityName = undefined;
draft_counter = 0;
var saveTimeout = undefined;
var DELAY_BEFORE_SAVING = 1000;

$(document).ready(function () {
	$(".draft").children(".name, .participant-name").bind("focus", newEntity);
	
    $(".property").on("input", function () {
    	if (saveTimeout) clearTimeout(saveTimeout);
    	var parent = this.parentNode; 
    	saveTimeout = setTimeout(function () {
    		add(parent);
    	}, DELAY_BEFORE_SAVING);
    });
    $(".name, .description").bind("focus", function () {
    	var entity = this.parentNode;
    	currentEntityName = entity.querySelector(".name");
    });
});

function getEntityCollection() {
	return "#" + ENTITY_NAME + "-collection";
}
function getEntitySelector() {
	return "." + ENTITY_NAME;
}

/**
 * Creates a new element.
 */
function newEntity() {
	var clone = $(this.parentNode).clone(true);
	$(this.parentNode).removeClass("transparent draft");
	$(this.parentNode).children("input").off("focus");
	if (typeof extend_newEntity === "function") extend_newEntity(clone);
	var newId = clone[0].id.replace(/\d+$/, ++draft_counter);
	clone[0].id = newId;
	clone.appendTo(this.parentNode.parentNode);
}

/**
 * Reorder Event elements according to the value of their priority element.
 */
function reorder() {
	var entityCollection = $(getEntityCollection());
	var entity = entityCollection.children(getEntitySelector());
	entity.detach().sort(extend_reorder);
	entityCollection.append(entity);	
}