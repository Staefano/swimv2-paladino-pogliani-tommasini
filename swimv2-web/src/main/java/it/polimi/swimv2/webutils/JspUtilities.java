package it.polimi.swimv2.webutils;

import java.util.Collection;

public final class JspUtilities {
	
	private JspUtilities() {  }
	
	public static boolean contains(Collection<?> coll, Object obj) {
		return coll.contains(obj);
	}
	
}
