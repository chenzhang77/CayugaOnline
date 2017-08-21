package mun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javafx.concurrent.Task;
 
// Copy all file in C:/Windows
public class CopyTask extends Task<List<File>> {
 
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
                this.uploadFile(file);
                copied.add(file);
            }
           
        }
        return copied;
    }
 
    private void uploadFile(File file) throws Exception {
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
            String dictionaryFileRemoteFile = "cayugaOnlineFiles/"+file.getName();
            InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = ftpClient.storeFileStream(dictionaryFileRemoteFile);
            byte[] bytesIn = new byte[4096];
            int read = 0; 
            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close(); 
            boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }
 
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
    
//    public void upload() {
//        String server = "www.cayugadictionary.ca";
//        int port = 21;
//        String user = "cjdyck";
//        String pass = "thog3pi7";
// 
//        FTPClient ftpClient = new FTPClient();
//        try {
// 
//            ftpClient.connect(server, port);
//            ftpClient.login(user, pass);
//            ftpClient.enterLocalPassiveMode();
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
// 
//            File dictionaryFile = new File("/Users/cz5670/Desktop/words_without_affixes.txt");
//            String dictionaryFileRemoteFile = "Report.doc";
//            InputStream inputStream = new FileInputStream(dictionaryFile);
// 
//            System.out.println("Start uploading second file");
//            OutputStream outputStream = ftpClient.storeFileStream(dictionaryFileRemoteFile);
//            byte[] bytesIn = new byte[4096];
//            int read = 0;
// 
//            while ((read = inputStream.read(bytesIn)) != -1) {
//            	System.out.println("read = " + read);
//                outputStream.write(bytesIn, 0, read);
//            }
//            inputStream.close();
//            outputStream.close();
// 
//            boolean completed = ftpClient.completePendingCommand();
//            if (completed) {
//                System.out.println("The second file is uploaded successfully.");
//            }
// 
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
 
}