package it.polimi.swimv2.enums;

/**
 * The status of an HelpRequest object
 * - WAITING: the request was sent, but it hasn't been accepted or denied yet
 * - ACCEPTED: the request has been accepted 
 * - DENIED: the request is closed because it was refused
 * - ZOMBIE: the request is closed (by the one who started it as the job is finished), but the helper has not left a feedback on the asker yet
 * - CLOSED: the request is closed. Both users left a feedback.
 */
public enum RequestStatus {
	WAITING, ACCEPTED, DENIED, ZOMBIE, CLOSED
}
