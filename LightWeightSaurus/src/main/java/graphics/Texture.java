package graphics;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

public class Texture {

    private final int id;

    /**
     * Luo tekstuurin  kuvatiedostosta.
     *
     * @param path Tiedoston sijainti.
     */
    public Texture(String path) throws Exception {
        this(loadTexture(path));
    }

    /**
     * Apu konsturktori.
     *
     * @param id Tekstuurin id.
     */
    public Texture(int id) {
        this.id = id;
    }

    /**
     * Yhdistää tekstuurin tiettyyn osaan tekstuureja käsittelevään yksikköön.
     */
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getId() {
        return id;
    }

    /**
     * Lukee kuvan RGBA muotoon ja syöttää näytänohjaimelle odottamaan.
     *
     * @param path tekstuurin sijainti.
     * @return Tekstuurin id näytönohjaimella.
     */
    private static int loadTexture(String path) throws Exception {

        PNGDecoder decoder = new PNGDecoder(Texture.class.getResourceAsStream(path));

        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());

        decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
        buffer.flip();

        int textureId = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, textureId);

        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(),
                decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        glGenerateMipmap(GL_TEXTURE_2D);

        return textureId;
    }

    /**
     * Poistaa tekstuurin näytönohjaimelta.
     */
    public void destroy() {
        glDeleteTextures(id);
    }
}
