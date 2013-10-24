ENTITY_NAME = "meme";
MEME = "#"+ENTITY_NAME;
MEME_COLLECTION = MEME + "-collection";
thumbnailRefresher = undefined;

$(document).ready(function () {
    $(".draft").children(".image").bind("focus", newEntity);

    $(".image").on("input", function () {
        if (thumbnailRefresher) clearTimeout(thumbnailRefresher);
        var parent = this.parentNode;
        thumbnailRefresher = setTimeout(function () {
            updateThumbnail(parent);
        }, 200);
    });

    // http://api.jqueryui.com/draggable/
    $(".draggable").draggable({
      stop: function(event, ui) {
          var captionX = this.parentNode.querySelector(".captionX");
          if (!captionX) return;
          var captionY = this.parentNode.querySelector(".captionY");          
          
          captionX.value = ui.position.left;
          captionY.value = ui.position.top;
          add(this.parentNode);
      }
    });

    $(".text").dblclick(function () {
        if ($(this).hasClass("draggable")) $(this).draggable("disable");
        this.contentEditable = true;
    });
    $(".text").keypress(function (e) {
        var code = (e.keyCode ? e.keyCode : e.which);
        if (code == 13) {
            this.contentEditable = false;
            $(this.parentNode).children(".caption").val(this.innerHTML);
            if ($(this).hasClass("draggable")) $(this).draggable("enable");
        }
    });
});

$(window).load(function() {
	$(".text, .immutable-text").each(function () {
		adjustSize(this);
	});
});

function adjustSize(e) {
    var thumbnail = $(e.parentNode).children(".thumbnail")[0];
    var imageWidth = thumbnail.width;
    var fontSize = imageWidth / 7;
    if (fontSize && fontSize > 0) {
    	$(e).css("font-size", fontSize);
    	debug("new font size: "+fontSize);
    }
}

function fill(meme, savedMeme) {
	if (savedMeme) {
		meme.id = ENTITY_NAME+"-"+savedMeme.id;
		$qs(meme, ".id", savedMeme.id);
		$qs(meme, ".name", savedMeme.name);
		$qs(meme, ".image", savedMeme.image);
		$qs(meme, ".captionX", savedMeme.captionX);
		$qs(meme, ".captionY", savedMeme.captionY);
		$qs(meme, ".caption", savedMeme.caption);
	}
}

/**
 * @param meme The id of the element that holds all elements with properties of the Meme object.
 */
function add(meme) {
	if (meme.querySelector(".name").value) {
		doSubmit(meme, "add", function (response) {
			var savedMeme = response.content;
			fill(meme, savedMeme);
		});
	}
}

/**
 * @param meme The id of the element that holds all elements with properties of the Meme object.
 */
function remove(meme) {
	if (meme.querySelector(".name").value) {
		doSubmit(meme, "remove", function (response) {
			var savedMeme = response.content;
			fill(meme, savedMeme);
		});
	}
}

function updateThumbnail(parent) {
    var newSrc = $(parent).children(".image").val();
    $(parent).children(".thumbnail").attr("src", newSrc);
}