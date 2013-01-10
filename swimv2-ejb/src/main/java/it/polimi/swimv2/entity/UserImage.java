package it.polimi.swimv2.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="UserImage.getByUserId", query="SELECT u.image FROM User u WHERE u.id = :userid")
})
public class UserImage implements Serializable{
	
	// argh...
	@Id @GeneratedValue
	private long id;
	
	private String mimeType;
	
	@Lob @Basic(fetch=FetchType.LAZY)
	private byte[] image;
	
	public UserImage() {}
	
	public UserImage(String mimeType, byte[] content) {
		this.image = content;
		this.mimeType = mimeType;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getMimeType() {
		return this.mimeType;
	}
	
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
