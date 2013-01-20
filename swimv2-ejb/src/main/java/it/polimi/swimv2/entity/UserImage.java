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
	@NamedQuery(name="UserImage.getIDByUserId", query="SELECT u.image.id FROM User u WHERE u.id = :userid")
})
public class UserImage implements Serializable{
	
	private static final long serialVersionUID = -7569667778615385681L;

	@Id @GeneratedValue
	private long id;
	
	@Lob @Basic(fetch=FetchType.LAZY)
	private byte[] image;
	
	public UserImage() {}
	
	public UserImage(byte[] image) {
		this.image = image.clone();
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
