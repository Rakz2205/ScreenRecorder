package recorder;

public class UpdatedPath {

	
	// UAT Changes - Updated return parameter to match string name
	// UAT Changes - Creating sub folder inside #CTRecordings
	public String ModifiedPathMain() {	
		ProgrammePath OriginalPath = new ProgrammePath();	
		String ModifiedPathCTOperation = (OriginalPath.workingDir().replace("\\", "/")) + "/CTRecordings/#CTOperation_Images/";
		
		return ModifiedPathCTOperation;
	}
	
	public String ModifiedPath() {	
	ProgrammePath OriginalPath = new ProgrammePath();	
	String ModifiedPathCTTemp = (OriginalPath.workingDir().replace("\\", "/")) + "/CTRecordings/#CTOperation_Images/Temp/";
	
	return ModifiedPathCTTemp;
	}

	public String ModifiedPathTemp() {	
		ProgrammePath OriginalPath = new ProgrammePath();	
		String ModifiedPathCTTempCompression = (OriginalPath.workingDir().replace("\\", "/")) + "/CTRecordings/#CTOperation_Images/#TempCompression/";		
		return ModifiedPathCTTempCompression;
		}

	
	public String RecordingPath() {	
		ProgrammePath OriginalPath = new ProgrammePath();	
		String RecordingPath = (OriginalPath.workingDir().replace("\\", "/")) + "/CTRecordings/";
		return RecordingPath;
		}


}
