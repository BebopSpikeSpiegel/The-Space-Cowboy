package powers;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;

/**
 * Null-safe power-icon loader. Power classes load their icons in static
 * initializers; if {@link ImageMaster#loadImage} returns null (missing or
 * unreadable file), building an AtlasRegion from it throws inside the static
 * init and permanently breaks the power — crashing the game the moment it is
 * used. This wraps the load so a bad icon degrades to an invisible placeholder
 * instead of taking down the run.
 */
public final class SpikeIcons {
    private SpikeIcons() {}

    public static TextureAtlas.AtlasRegion load(String path) {
        Texture tex = ImageMaster.loadImage(path);
        if (tex == null) {
            Pixmap pm = new Pixmap(48, 48, Pixmap.Format.RGBA8888);
            pm.setColor(1f, 1f, 1f, 0f);
            pm.fill();
            tex = new Texture(pm);
            pm.dispose();
        }
        return new TextureAtlas.AtlasRegion(tex, 0, 0, tex.getWidth(), tex.getHeight());
    }
}
