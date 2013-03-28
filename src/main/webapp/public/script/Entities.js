ENTITY_NAME = undefined;
var currentEntityTitle;

$(document).ready(function () {
	$(".draft").children(".title").bind("focus", newEntity);
	
    $(".property").bind("blur", function () {
		add(this.parentNode);
    });
    $(".title, .description").bind("focus", function () {
    	var entity = this.parentNode;
    	currentEntityTitle = entity.querySelector(".title");
    	console.log(currentEntityTitle.value + " has focus");
    });
});

function getEntityCollection() {
	return "#" + ENTITY_NAME + "-collection";
}
function getEntitySelector() {
	return "." + ENTITY_NAME;
}

/**
 * Creates a new Event element.
 */
function newEntity() {
	var clone = $(this.parentNode).clone(true);
	$(this.parentNode).removeClass("transparent");
	$("input").parent().removeClass("draft");
	$("input").off("focus");
	if (extend_newEntity) extend_newEntity(clone);
	clone.appendTo(getEntityCollection());
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