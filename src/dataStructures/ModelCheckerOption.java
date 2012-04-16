package dataStructures;

public class ModelCheckerOption extends NameKeyObject<ModelChecker>{
	
	// identifiers used to reprsent diferent engines
	public static final int MODEL_CHECKER_0 = 0;
	public static final int MODEL_CHECKER_1 = 1;
	public static final String[] modelCheckerNames = { "MODEL CHECKER 0", "MODEL CHECKER 1" };
	
	public static ModelCheckerOption[] getAllOptions(){
		ModelCheckerOption[] allOptions = new ModelCheckerOption[modelCheckerNames.length];
		for(int i=0;i<allOptions.length;i++){
			allOptions[i]=new ModelCheckerOption(i);
		}
		return allOptions;
	}

	public ModelCheckerOption(Integer key) {
		super(modelCheckerNames[key], key, null);
	}
	
	@Override
	public String toString(){
		return getName();
	}
	
	@Override
	public ModelChecker getObject(){
		//this will return a static model Checker or...?
		return null;
	}

}
