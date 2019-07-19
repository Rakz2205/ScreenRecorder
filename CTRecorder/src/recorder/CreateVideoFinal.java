package recorder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;


import javax.media.MediaLocator;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class CreateVideoFinal{

	public static String TypeResolution;
	
  public static final String[] extensions = new String[]{"jpeg"};
  public static final FilenameFilter imageFilter = new FilenameFilter() {
    @Override
    public boolean accept(final File dir, String name) {
        for (final String ext : extensions) {
            if (name.endsWith("." + ext)) {
                return (true);
            }
        }
        return (false);
    }
};

// Main function
public void CreateVideo (String resolutiontype) throws IOException {
	
	 TypeResolution = resolutiontype;
	//System.err.println(resolutiontype);
	Integer Pixel1 = null;
	Integer Pixel2 = null;
	if (resolutiontype == "High Resolution") {
		 Pixel1 = 1366;
		 Pixel2 = 768;	
	}
	if (resolutiontype == "Low Resolution") {
		 Pixel1 = 200;
		 Pixel2 = 75;	
	}
	if (resolutiontype == "Medium Resolution") {
		 Pixel1 = 1280;
		 Pixel2 = 720;	
	}
	
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	Date date = new Date(0);
	date.setTime(timestamp.getTime());
	UpdatedPath LatestPath = new UpdatedPath();
	String formattedDate = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(date);
	//System.out.println(formattedDate);
	final File dir = new File(LatestPath.ModifiedPath());
    File file = new File(LatestPath.RecordingPath() + "/Recordings_" + formattedDate + ".mp4");
    if (!file.exists()) {
        file.createNewFile();
    }
    
    
    Vector<String> imgLst = new Vector<>();
    
    if (dir.isDirectory()) {    
    	
        for (final File f : dir.listFiles(imageFilter)) {
        	
        if (resolutiontype == "Low Resolution") {
         new CompressJPEGFile(f.getAbsolutePath()+"",f.getAbsolutePath()+"#TempCompression");
       	 imgLst.add(f.getAbsolutePath()+"#TempCompression");
       	 
        }
       	else {
        imgLst.add(f.getAbsolutePath());        
        }	       
       }
    }
    
	makeVideo("file:\\" + file.getAbsolutePath(), imgLst,Pixel1,Pixel2);        
}

 @SuppressWarnings("static-access")
public static void makeVideo(String fileName, @SuppressWarnings("rawtypes") Vector imgLst, Integer Pixel1, Integer Pixel2) throws MalformedURLException {
    JpegImagesToMovie imageToMovie = new JpegImagesToMovie();
    MediaLocator oml;
    if ((oml = imageToMovie.createMediaLocator(fileName)) == null) {
        System.err.println("Cannot build media locator from: " + fileName);
        System.exit(0);
    }
    int interval = 6;
    if (TypeResolution == "Low Resolution") {
    	interval = 8;    	
    }
    imageToMovie.doIt(Pixel1, Pixel2,interval , imgLst, oml);
 }  
}
