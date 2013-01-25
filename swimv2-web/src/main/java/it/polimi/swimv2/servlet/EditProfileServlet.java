package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.remote.UserBeanRemote;
import it.polimi.swimv2.session.remote.UserImageBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.ImageUtils;
import it.polimi.swimv2.webutils.MultipartFormProcesser;
import it.polimi.swimv2.webutils.Navigation;
import it.polimi.swimv2.webutils.exception.NavigationException;
import it.polimi.swimv2.webutils.exception.NoImageUploadedException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class EditProfileServlet extends Controller implements Servlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserBeanRemote userBean;
	
	@EJB
	private UserImageBeanRemote imageBean;

	private static final long MAX_ALLOWED_SIZE = 1024 * 1024 * 7; // 7 MB
	private static final int AVATAR_WIDTH_PX = 200;
	private static final int AVATAR_HEIGHT_PX = 200;

	private static final String EDITPROFILE_JSP = "WEB-INF/editprofile.jsp";
	
	public EditProfileServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		nav.fwd(EDITPROFILE_JSP);
	}

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {

		MultipartFormProcesser form = null;
		try {
			form = nav.getMultipart();
			form.process();
		} catch (NavigationException e) {
			throw new ServletException(e); // error HTTP 500
		}

		String name = form.getValue("name");
		String surname = form.getValue("surname");
		String website = form.getValue("website");
		String birthdate = form.getValue("birthdate");
		String location = form.getValue("location");
		String minibio = form.getValue("minibio");
		String description = form.getValue("description");
		boolean removeImage = form.getValue("removeImage") != null && form.getValue("removeImage").equals("yes");
		
		try {
			User updated = userBean.editProfile(nav.getLoggedUser().getId(), name, surname,
					website, birthdate, location, minibio, description);
			nav.setLogin(updated);
			if(removeImage) {
				updated = imageBean.unsetImage(updated);
			} else if(form.getFileSize() > 0) {
				updated = uploadUserImage(updated, form.getFile(), form.getFileSize());
			}
			nav.redirect("/");
		} catch (NoSuchUserException u) {
			nav.setAttribute("error", "form");
			nav.fwd(EDITPROFILE_JSP);
		} catch(NoImageUploadedException noimg) {
			nav.setAttribute("error", "imageupload");
			nav.fwd(EDITPROFILE_JSP);
		}
	}

	private User uploadUserImage(User user, InputStream file, long size)
			throws NoImageUploadedException {
		if(file == null || size > MAX_ALLOWED_SIZE || user == null) {
			throw new NoImageUploadedException();
		}
		try {
			BufferedImage img = ImageIO.read(file);
			if(img == null) {
				throw new NoImageUploadedException();
			}
			byte[] resized = ImageUtils.getScaledInstance(AVATAR_WIDTH_PX, AVATAR_HEIGHT_PX, img);
			return imageBean.setImage(user.getId(), resized);
		} catch (IOException e) {
			throw new NoImageUploadedException(e);
		}
	}

}
