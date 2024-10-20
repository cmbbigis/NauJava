import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.HttpURLConnection;

public class Task5 implements Task {
    private final String fileURI;
    private final String fileName;
    private final String filePath;
    private final Thread downloadThread;
    private boolean isStopped = false;

    public Task5(String fileURI, String fileName, String saveDir) {
        this.fileURI = fileURI;
        this.fileName = fileName;
        filePath = saveDir + "/" + fileName;
        downloadThread = new Thread(() -> {
            try {
                downloadFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start() {
        downloadThread.start();
    }

    @Override
    public void stop() {
        isStopped = true;
        if (downloadThread != null) {
            downloadThread.interrupt();
        }
    }

    private void downloadFile() throws IOException, URISyntaxException {
        var uri = new URI(fileURI);
        var httpConn = (HttpURLConnection) uri.toURL().openConnection();
        var responseCode = httpConn.getResponseCode();
        var file = new File(filePath);
        file.createNewFile();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedInputStream in = new BufferedInputStream(httpConn.getInputStream());
                 var out = new FileOutputStream(filePath)) {

                var buffer = new byte[4096];
                var bytesRead = 0;
                while ((bytesRead = in.read(buffer)) != -1) {
                    if (isStopped) {
                        out.close();
                        in.close();
                        System.out.println("Download stopped.");
                        return;
                    }
                    out.write(buffer, 0, bytesRead);
                }
                System.out.println("File downloaded: " + fileName);
            }
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }

    public static void main(String[] args) {
        var task = new Task5(
                "https://drive.google.com/file/d/1_8Sd2mYQmLwVXjdW6WHRXBoZJE-xzR6a/view?usp=sharing",
                "file.zip",
                "F:\\Downloads");

        task.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.stop();
    }
}
