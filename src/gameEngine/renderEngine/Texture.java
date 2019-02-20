package gameEngine.renderEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

public class Texture {
    private int id;
    private TextureSize size = new TextureSize();

    public static class TextureSize{
        public int width;
        public int height;
    }

    public Texture(){
        size.width = 1;
        size.height = 1;
        ByteBuffer pixels = BufferUtils.createByteBuffer(size.width * size.height * 4);
        for(int i = 0; i < pixels.capacity(); ++i) {
            pixels.put((byte)0xFF);
        }
        pixels.flip();
        loadTextureWithByteBuffer(pixels);
    }

    public Texture(ByteBuffer pixels, int width, int height){
        this.size.width = width;
        this.size.height = height;
        pixels.flip();
        loadTextureWithByteBuffer(pixels);
    }

    public Texture(ByteBuffer pixels, TextureSize texSize){
        this.size.width = texSize.width;
        this.size.height = texSize.height;
        pixels.flip();
        loadTextureWithByteBuffer(pixels);
    }

    public Texture(String filepath) {
        ByteBuffer pixels = TextureLoader.LoadTexture(filepath, size);
        pixels.flip();
        loadTextureWithByteBuffer(pixels);
    }

    private void loadTextureWithByteBuffer(ByteBuffer pixels){
        id = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, id);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,size.width,size.height,0,GL_RGBA,GL_UNSIGNED_BYTE,pixels);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void bind(int sampler){
        if(sampler >= 0 && sampler < 32) {
            glBindTexture(GL_TEXTURE_2D, id);
            glActiveTexture(GL_TEXTURE0 + sampler);
        }
    }

    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
