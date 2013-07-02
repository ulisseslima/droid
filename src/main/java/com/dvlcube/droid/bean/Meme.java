package com.dvlcube.droid.bean;

import static com.dvlcube.cuber.Cuber.$;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.dvlcube.reflection.FieldName;
import com.dvlcube.service.BasicInfo;

/**
 * 
 * @author wonka
 * @since 27/06/2013
 */
@Entity
public class Meme implements BasicInfo, Owned {
	public enum Field implements FieldName {
		caption, captionX, captionY, dateModified, description, id, image, name, owner;
		public String id() {
			return name() + ".id";
		}
	}

	private static final long serialVersionUID = 6362154457938757910L;
	private String caption;
	private Double captionX;
	private Double captionY;
	private Date dateModified; // timestamp
	private String description;
	@Id
	@GeneratedValue
	private Long id; // id
	private String image;
	private String name;
	@ManyToOne
	private User owner; // ref_many2one #not null

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Meme other = (Meme) obj;
		if (caption == null) {
			if (other.caption != null) {
				return false;
			}
		} else if (!caption.equals(other.caption)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (image == null) {
			if (other.image != null) {
				return false;
			}
		} else if (!image.equals(other.image)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @return the captionX
	 */
	public Double getCaptionX() {
		return captionX;
	}

	/**
	 * @return the captionY
	 */
	public Double getCaptionY() {
		return captionY;
	}

	/**
	 * @return the dateModified
	 */
	@Override
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public FieldName[] getFields() {
		return Field.values();
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	@Override
	public String getLabel() {
		return $(getName()).escapeHTML().string;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @return the owner
	 */
	@Override
	public User getOwner() {
		return owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (caption == null ? 0 : caption.hashCode());
		result = prime * result + (description == null ? 0 : description.hashCode());
		result = prime * result + (image == null ? 0 : image.hashCode());
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean hasRequiredAttributes() {
		if ($(name).isBlank()) {
			return false;
		}
		if ($(caption).isBlank()) {
			return false;
		}
		if ($(image).isBlank()) {
			return false;
		}
		return true;
	}

	/**
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @param captionX
	 *            the captionX to set
	 */
	public void setCaptionX(Double captionX) {
		this.captionX = captionX;
	}

	/**
	 * @param captionY
	 *            the captionY to set
	 */
	public void setCaptionY(Double captionY) {
		this.captionY = captionY;
	}

	/**
	 * @param dateModified
	 *            the dateModified to set
	 */
	@Override
	public void setDateModified(final Date dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	@Override
	public void setOwner(final User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Meme [caption=" + caption + ", description=" + description + ", id=" + id + ", image=" + image
				+ ", name=" + name + ", owner=" + owner + "]";
	}

	@Override
	public void touch() {
		dateModified = new Date();
	}
}
