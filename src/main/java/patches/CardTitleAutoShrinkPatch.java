package patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

import java.lang.reflect.Field;

/**
 * Auto-shrink long card titles so they never overflow the banner.
 *
 * The base game's {@code renderTitle} draws the name with no width constraint
 * (only a static {@code useSmallTitleFont} 0.85x flag), so long Cowboy Bebop
 * titles like "Words That We Couldn't Say" clip past the plate. This prefix
 * re-renders the title with a scale that fits {@link #MAX_TITLE_LOCAL_WIDTH}
 * card-local pixels. The fit factor cancels out {@code Settings.scale} and
 * {@code drawScale}, so it is resolution- and zoom-independent.
 *
 * Locked / unseen cards fall through to the base game (it uses private
 * "?"/locked strings we don't need to replicate).
 */
@SpirePatch(clz = AbstractCard.class, method = "renderTitle")
public class CardTitleAutoShrinkPatch {
    /** How many card-local px the title may occupy before it gets scaled down. Tune in-game. */
    private static final float MAX_TITLE_LOCAL_WIDTH = 182.0F;
    private static Field renderColorField;

    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(AbstractCard __instance, SpriteBatch sb) {
        if (__instance.isLocked || !__instance.isSeen) {
            return SpireReturn.Continue();
        }

        BitmapFont font = FontHelper.cardTitleFont;
        float baseScale = __instance.drawScale;
        font.getData().setScale(baseScale);

        float width = FontHelper.getWidth(font, __instance.name, 1.0F);
        float maxWidth = MAX_TITLE_LOCAL_WIDTH * Settings.scale * __instance.drawScale;
        if (width > maxWidth && width > 0.0F) {
            font.getData().setScale(baseScale * (maxWidth / width));
        }

        Color rc = getRenderColor(__instance);
        Color color;
        if (__instance.upgraded) {
            color = Settings.GREEN_TEXT_COLOR.cpy();
            color.a = rc.a;
        } else {
            color = rc;
        }

        FontHelper.renderRotatedText(sb, font, __instance.name,
                __instance.current_x, __instance.current_y,
                0.0F, 175.0F * __instance.drawScale * Settings.scale,
                __instance.angle, false, color);

        font.getData().setScale(1.0F);
        return SpireReturn.Return(null);
    }

    private static Color getRenderColor(AbstractCard c) {
        try {
            if (renderColorField == null) {
                renderColorField = AbstractCard.class.getDeclaredField("renderColor");
                renderColorField.setAccessible(true);
            }
            Color col = (Color) renderColorField.get(c);
            if (col != null) {
                return col;
            }
        } catch (Exception ignored) {
        }
        return Color.WHITE;
    }
}
