package johnny.problem;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class ImageCache {
    private LRU lru;
    public void process(List<String> urls,  int capacity, int quantity) throws IOException, InterruptedException {
        lru = new LRU(capacity, quantity);

        for (String url : urls) {
            if (lru.contains(url)) {
                System.out.println(url + " IN_CACHE " + lru.get(url).length);
            } else {
                byte[] image = download(url);
                System.out.println(url + " DOWNLOADED " + image.length);
                lru.add(url, image);
            }
            //System.out.println("size:" +  lru.size());
            //System.out.println("count:" +  lru.count());
        }
    }

    private byte[] download(String imageUrl) throws IOException, InterruptedException {
        URL url = new URL(imageUrl);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while (-1 != (len = in.read(buf)))
        {
            out.write(buf, 0, len);
        }
        out.close();
        in.close();

        byte[] response = out.toByteArray();
        //Thread.sleep(1000);
        return response;
    }

    public void print() {
        System.out.println(lru);
    }
}