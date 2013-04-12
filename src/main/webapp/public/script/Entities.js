ENTITY_NAME = undefined;
currentEntityName = "";

$(document).ready(function () {
	$(".draft").children(".name").bind("focus", newEntity);
	
    $(".property").bind("blur", function () {
    	debug("adding: "+this.parentNode.className);
		add(this.parentNode);
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
	debug("new entity...");
	var clone = $(this.parentNode).clone(true);
	$(this.parentNode).removeClass("transparent");
	$(this.parentNode).removeClass("draft");
	$(this.parentNode).children("input").off("focus");
	if (typeof extend_newEntity == "function") extend_newEntity(clone);
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