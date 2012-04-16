package dataStructures;

import java.util.Date;
import java.util.HashMap;

public class MetaData {

	// metadata variables
	private String filename;
	private String projectName;
	private Date creationDate;
	private ModelCheckerOption selectedModelChecker;
	private HashMap<String, Integer> displayOptions;

	public MetaData() {
		this("untitled", "unnamed", new ModelCheckerOption(ModelCheckerOption.MODEL_CHECKER_0),
				new Date());
	}

	public MetaData(String filename, String projectName,
			ModelCheckerOption selectedModelChecker, Date creationDate) {
		this.filename = filename;
		this.projectName = projectName;
		this.selectedModelChecker = selectedModelChecker;
		this.creationDate = creationDate;
		this.displayOptions = new HashMap<String, Integer>();
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		//System.out.println("filename set to "+filename);
		this.filename = filename;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		//System.out.println("project name set to "+projectName);
		this.projectName = projectName;
	}

	public ModelCheckerOption getSelectedModelChecker() {
		return selectedModelChecker;
	}

	public void setSelectedModelChecker(ModelCheckerOption selectedModelChecker) {
		//System.out.println("set! "+selectedModelChecker.getName());
		this.selectedModelChecker = selectedModelChecker;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public HashMap<String, Integer> getDisplayOptions() {
		return displayOptions;
	}
}
