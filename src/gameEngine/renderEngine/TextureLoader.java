package gameEngine.renderEngine;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TextureLoader {

    public static ByteBuffer LoadTexture(String filepath, Texture.TextureSize size){

        BufferedImage bi;
        try {
            bi = ImageIO.read(new File(filepath));
            size.width = bi.getWidth();
            size.height = bi.getHeight();

            int[] pixels_raw = new int[ size.width * size.height ];
            pixels_raw = bi.getRGB(0,0,size.width,size.height,null,0,size.width);

            ByteBuffer pixels = BufferUtils.createByteBuffer(size.width * size.height * 4);

            for(int i = 0; i < size.height; ++i)
            {
                for(int j = 0; j < size.width; ++ j)
                {
                    int pixel = pixels_raw[i*size.width + j];
                    pixels.put((byte)((pixel >> 16) & 0xFF));   //RED
                    pixels.put((byte)((pixel >> 8) & 0xFF));    //GREEN
                    pixels.put((byte)(pixel & 0xFF));           //BLUE
                    pixels.put((byte)((pixel >> 24) & 0xFF));   //ALPHA
                }
            }
            pixels.flip();
            return pixels;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void WriteTextureToTexFile(ByteBuffer pixels, Texture.TextureSize texSize, String destPath){
        try {
            File file = new File(destPath);
            FileChannel wChannel = new FileOutputStream(file).getChannel();
            ByteBuffer bf = BufferUtils.createByteBuffer(8);
            bf.putInt(0, texSize.width);
            bf.putInt(4, texSize.height);
            wChannel.write(bf);
            wChannel.write(pixels);
            wChannel.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ByteBuffer LoadTextureFromTexFile(String sourcePath, Texture.TextureSize size){
        try {
            File file = new File(sourcePath);
            FileInputStream inFile = new FileInputStream(file);
            FileChannel inChannel = inFile.getChannel();
            ByteBuffer sizeBuff = BufferUtils.createByteBuffer(16);
            sizeBuff.rewind();
            inChannel.read(sizeBuff);
            size.width = sizeBuff.getInt(0);
            size.height = sizeBuff.getInt(4);
            ByteBuffer pixels = BufferUtils.createByteBuffer(size.width * size.height * 4);
            inChannel.position(8);
            inChannel.read(pixels);
            inFile.close();
            return pixels;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
