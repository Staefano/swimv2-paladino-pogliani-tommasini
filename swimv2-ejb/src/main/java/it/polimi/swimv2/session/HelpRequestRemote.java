package it.polimi.swimv2.session;

import java.util.List;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.Comment;
import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;

public interface HelpRequestRemote {

	HelpRequest askForHelp(User sender, User receiver, String subject, List<Ability> abilties);

	List<Comment> getComments(HelpRequest hr);

	void giveFeedback(int value, String comment, HelpRequest hr, User user)
			throws ClosedHelpRequestException;

	void addComment(HelpRequest hr, String comment, User sender)
			throws ClosedHelpRequestException;;
			//TODO aggiunto un user che invia il messaggio


	List<HelpRequest> getHelpRequest(User u);
}
