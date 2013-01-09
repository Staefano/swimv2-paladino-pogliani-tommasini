package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.ImageUtils;
import it.polimi.swimv2.webutils.MultipartFormProcesser;
import it.polimi.swimv2.webutils.Navigation;
import it.polimi.swimv2.webutils.exception.NavigationException;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class EditProfile extends Controller implements Servlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserBeanRemote ubr;
	
	private static final long MAX_ALLOWED_SIZE = 1024 * 1024 * 3; // 3 MB
	private static final int AVATAR_WIDTH_PX = 200;
	private static final int AVATAR_HEIGHT_PX = 200;
	private static final String MIMETYPE = "image/png";

	public EditProfile() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		nav.fwd("WEB-INF/editprofile.jsp");
	}

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {

		MultipartFormProcesser form = null;
		try {
			form = nav.getMultipart();
			form.process();
		} catch (NavigationException e1) {
			// for now just throw 500 error (servlet exception)
			throw new ServletException();
		}

		String name = form.getValue("name");
		String surname = form.getValue("surname");
		String website = form.getValue("website");
		String birthdate = form.getValue("birthdate"); // TODO gestire la data
		String location = form.getValue("location");
		String minibio = form.getValue("minibio");
		String description = form.getValue("description");

		try {
			nav.setLogin(ubr.editProfile(nav.getLoggedUser(), name, surname,
					website, birthdate, location, minibio, description));
			uploadUserImage(nav.getLoggedUser(), form.getFile(), form.getFileSize());
			// TODO TODO TODO HANDLE USER IMAGE UPLOAD FAILING (now it's too late to do so...)
		} catch (NoSuchUserException e) {
			// TODO Auto-generated catch block
		}
		nav.fwd(BASEPATH);
	}
	
	private void uploadUserImage(User user, InputStream file, long size) throws IOException {
		if(size <= MAX_ALLOWED_SIZE && user != null) {
			byte[] resized = ImageUtils.getScaledInstance(AVATAR_WIDTH_PX, AVATAR_HEIGHT_PX, file);
			ubr.setImage(user, resized, MIMETYPE);
		} else {
			// TODO do something...
		}
	}

}
