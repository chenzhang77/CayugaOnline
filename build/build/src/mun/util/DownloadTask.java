/**
* The Cayuga Dictionary project is to keep the language vibrant and alive 
* through immersion courses for adults and language daycare for children.
* 
* The project is Led by Dr. Carrie Dyck
* Faculty of Humanities and Social Sciences
* Memorial University of Newfoundland
*
* @author  Chen Zhang
* @version online 2.0
* @since   2016-12-01 
*/
package mun.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import javafx.concurrent.Task;
 
public class DownloadTask extends Task<List<File>> {
 
    @Override
    protected List<File> call() throws Exception {
 
        File dir = new File(Constant.filePath);
        File[] files = dir.listFiles();
        int count = files.length; 
        List<File> copied = new ArrayList<File>();
        int i = 0;
        for (File file : files) {
        	 i++;
             this.updateProgress(i, count);
            if (file.isFile()) {
                this.downloadFile(file);
                copied.add(file);
            }          
        }     
        return copied;
    }
 
    private void downloadFile(File file) throws Exception {
        this.updateMessage(file.getName());
        String server = "www.cayugadictionary.ca";
        int port = 21;
        String user = "cjdyck";
        String pass = "thog3pi7";
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);    
//            System.out.println(Constant.filePath+"/"+file.getName());
//            System.out.println("/cayugaOnlineFiles/"+file.getName());
            
            FileOutputStream fos = new FileOutputStream(Constant.filePath+"/"+file.getName());            
            ftpClient.retrieveFile("/cayugaOnlineFiles/"+file.getName(), fos);

 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
 
}