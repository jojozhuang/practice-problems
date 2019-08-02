package johnny.problem.imagecache;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class ImageCache {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int num = sc.nextInt();

        List<String> urls = new ArrayList<>();
        while (sc.hasNext()) {
            urls.add(sc.next());
        }
        sc.close();

        LRU lru = new LRU(size);

        for (String url : urls) {
            if (lru.contains(url)) {
                System.out.println(url + " IN_CACHE " + lru.get(url).length);
            } else {
                byte[] image = download(url);
                lru.add(url, image);
                System.out.println(url + " DOWNLOADED " + image.length);
            }
            //System.out.println("size:" +  lru.size());
            //System.out.println("count:" +  lru.count());
        }
    }

    public static byte[] download(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
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

        return response;
    }
}