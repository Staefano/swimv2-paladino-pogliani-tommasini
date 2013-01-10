package it.polimi.swimv2.webutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

public class MultipartFormProcesser {
	
	private Map<String, String> map;
	private List<FileItem> files;
	private InputStream imageIs;
	private long imageSize;
	
	public MultipartFormProcesser(List<FileItem> files) {
		this.map = new HashMap<String, String>();
		this.files = files;
	}
	
	/**
	 * Process the multipart form
	 * @param nav The Navigation object
	 */
	public void process() {
		for(FileItem item : files) {
		    if (item.isFormField()) {
		    	map.put(item.getFieldName(), item.getString());
		    } else {
		    	processFile(item);
		    }
		}
	}

	private void processFile(FileItem item) {
		imageSize = item.getSize();
		try {
			imageIs = item.getInputStream();
		} catch (IOException e) {
			imageIs = null;
			imageSize = 0;
		}
	}
	
	/**
	 * return the InputStream associated with the file
	 * stored into the form. As a simplification, only ONE file for form is allowed
	 * Support for more than one file for each form will be added later if needed
	 */
	public InputStream getFile() {
		return this.imageIs;
	}
	
	public long getFileSize() {
		return this.imageSize;
	}
	
	public String getValue(String key) {
		return map.get(key);
	}
	
}
