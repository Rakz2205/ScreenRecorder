package recorder;

import java.io.File;

public class CreateFolder {

    public void FolderCreation (String Path) {
        File	 file = new File(Path);
        
        if (!file.exists()) {
            if (file.mkdir()) {
                //System.out.println("Directory is created!");
            } else {
                //System.out.println("Failed to create directory!");
            }
        }

    }

	
}