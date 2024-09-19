package chcs.common.util;

import java.io.*;
import netscape.util.*;

public class INIFile {
	private File iniFile;
	private FileOutputStream iniOutput;
	private FileInputStream iniInput;
	private boolean writeable;
	
	public INIFile(String iniFile) {
		this.iniFile = new File(iniFile);
		this.iniOutput = new FileOutputStream(this.iniFile);
		if (this.iniFile.exists()) {
			this.iniInput = new FileInputStream(this.iniFile);
			this.writeable = true;
		}
		else
			this.writeable = false;
	}
	
	public boolean isWriteable() {
		return(this.writeable);
	}
	
	public File getFile() {
		return(this.iniFile);
	}
	
	public String getName() {
		return(this.iniFile.getName());
	}
	
	public String getPath() {
		return(this.iniFile.getPath());
	}
	
	public String getAbsolutePath() {
		return(this.iniFile.getAbsolutePath());
	}

	public void close() {
		iniOutput.close()
	}
	
}
