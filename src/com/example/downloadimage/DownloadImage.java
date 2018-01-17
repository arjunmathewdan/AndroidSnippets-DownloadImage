package com.example.downloadimage;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.util.ByteArrayBuffer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
 
public class DownloadImage extends Activity {
 
    private final String PATH = "http://dl.dropbox.com/u/150409479/";
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
        tv = (TextView)findViewById(R.id.mytv);
        DownloadFromUrl(PATH+"example.kml");
    }
    public void DownloadFromUrl(String fileName) {
            try {
                    URL url = new URL("http://dropbox.com"); //you can write here any link
                    File file = new File(fileName);
 
                    long startTime = System.currentTimeMillis();
                    tv.setText("Starting download......from " + url);
                    URLConnection ucon = url.openConnection();
                    InputStream is = ucon.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    /*
                     * Read bytes to the Buffer until there is nothing more to read(-1).
                     */
                    ByteArrayBuffer baf = new ByteArrayBuffer(50);
                    int current = 0;
                    while ((current = bis.read()) != -1) {
                            baf.append((byte) current);
                    }
 
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(baf.toByteArray());
                    fos.close();
                    tv.setText("Download Completed in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");
            } catch (IOException e) {
                 tv.setText("Error: " + e);
            }
    }
}
