package com.dvlcube.droid.servlet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvlcube.droid.bean.Meme;
import com.dvlcube.droid.service.MemeService;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 27/03/2013
 */
@Controller
@RequestMapping("/meme")
public class MemeServlet {
	private static final String _namespace = "meme/meme_";
	private static final String editor = _namespace + "editor";
	private static final String index = _namespace + "index";
	private static final String viewer = _namespace + "viewer";

	@Autowired
	private MemeService service;

	@RequestMapping({ "/add", "/edit/add" })
	public @ResponseBody
	Response<Meme> add(@RequestBody final Meme meme) {
		if (meme.hasRequiredAttributes()) {
			return service.addOrUpdate(meme);
		} else {
			return null;
		}
	}

	@RequestMapping("/edit/{memeId}")
	public String edit(@PathVariable final long memeId, final Map<String, Object> map) {
		final Response<Meme> response = service.get(memeId);
		map.put("response", response);
		return editor;
	}

	@RequestMapping("/")
	public String index(final Map<String, Object> map) {
		final Response<Meme> response = service.listRecentFirst();
		map.put("response", response);
		return index;
	}

	@RequestMapping("/remove")
	public @ResponseBody
	Response<Meme> removememe(@RequestBody final Meme meme) {
		if (meme.hasRequiredAttributes()) {
			final Response<Meme> deletedMeme = service.delete(meme);
			return deletedMeme;
		} else {
			return null;
		}
	}

	@RequestMapping("/view/{x}x{y}/{memeId}")
	public String view(
		@PathVariable final long x,
		@PathVariable final long y,
		@PathVariable final long memeId,
		final Map<String, Object> map) {
		final Response<Meme> response = service.get(memeId);
		map.put("response", response);
		return viewer;
	}
}
