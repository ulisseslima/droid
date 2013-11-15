/**
 * Returns all jQuery objects that would collide if the subject moved to the specified location.
 * @param subject object that will change location.
 * @param xdestination desired left position.
 * @param ydestination desired top position.
 * @param objects a selection of jQuery objects that should be calculated for collisions.
 * @returns a collision object.
 */
function getCollisions(subject, xdestination, ydestination, objects) {
	var collisions = [];			
	var $target = $(subject);
	var t_x = [ydestination, ydestination + $target.outerWidth()];
	var t_y = [xdestination, xdestination + $target.outerHeight()];
	
	var minx = undefined, maxx = undefined;
	var miny = undefined, maxy= undefined;
	$(objects).each(function() {
		if (!(subject[0].id === this.id)) {					
			var $object = $(this);
			var objectPosition = $object.offset();
			var o_x = [objectPosition.left, objectPosition.left + $object.outerWidth()];
			var o_y = [objectPosition.top, objectPosition.top + $object.outerHeight()];
			
			if (t_x[0] <= o_x[1] && t_x[1] >= o_x[0] &&
				t_y[0] <= o_y[1] && t_y[1] >= o_y[0]) {
				collisions.push($object);
				if (!minx) minx = objectPosition.left;
				if (minx > objectPosition.left) minx = objectPosition.left;
				
				if (!maxx) maxx = objectPosition.left;
				if (maxx < objectPosition.left) maxx = objectPosition.left;
				
				if (!miny) miny = objectPosition.top;
				if (miny > objectPosition.top) miny = objectPosition.top;
				
				if (!maxy) maxy = objectPosition.top;
				if (maxy < objectPosition.top) maxy = objectPosition.top;
			}
		}
	});
	
	collisions["minx"] = minx; collisions["maxx"] = maxx;			
	collisions["miny"] = miny; collisions["maxy"] = maxy;
	return collisions;
}