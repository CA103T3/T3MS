package example.imageutil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.common.util.ImageUtil;

public class ImageUtilTest {

    public ImageUtilTest() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        String filepath = "WebContent/img/01.jpg";
        File f = new File(filepath);
        if (f.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file doesn't exist");
        }

        byte[] pic = null;
        try {
            pic = ImageUtil.getPictureByteArray(filepath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int imageSize = 260; //pixel
        pic = ImageUtil.shrink(pic, imageSize);
        String fileStart = filepath.substring(0, filepath.lastIndexOf("."));
        System.out.println("fileStart : " + fileStart); //fileStart : WebContent/img/01
        String fileExt = filepath.substring(filepath.lastIndexOf("."));
        System.out.println("fileExt : " + fileExt); //fileExt : .jpg
        String target = fileStart + "-s" + fileExt;
        System.out.println("target : " + target);

        try {
            ImageUtil.outputPicture(pic, target);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
    // 使用byte[]方式
    public static byte[] getPictureByteArray(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int i;
        while ((i = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, i);
        }
        baos.close();
        fis.close();

        return baos.toByteArray();
    }

    // Handle with byte array data
    public static void outputPicture(byte[] bytes, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }
    */
}
