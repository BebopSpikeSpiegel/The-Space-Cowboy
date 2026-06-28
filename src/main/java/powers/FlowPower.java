package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Flow ("be like water") — Spike's core momentum resource.
 * Builders grant Flow; payoff cards scale with or consume it.
 * EN keyword "Flow", zh keyword "势".
 */
public class FlowPower extends AbstractPower {
    public static final String POWER_ID = "Flow_Spike";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    // TODO: replace placeholder icon with dedicated 48x48 / 128x128 Flow art.
    private static final String ICON_128 = "img/UI_Spike/powers/Flow_Spike.png";
    private static final String ICON_48  = "img/UI_Spike/powers/Flow_Spike48.png";
    private static final Texture TEX_128 = ImageMaster.loadImage(ICON_128);
    private static final Texture TEX_48  = ImageMaster.loadImage(ICON_48);
    private static final TextureAtlas.AtlasRegion IMG_128 = new TextureAtlas.AtlasRegion(TEX_128, 0, 0, TEX_128.getWidth(), TEX_128.getHeight());
    private static final TextureAtlas.AtlasRegion IMG_48  = new TextureAtlas.AtlasRegion(TEX_48,  0, 0, TEX_48.getWidth(),  TEX_48.getHeight());

    public FlowPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.region128 = IMG_128;
        this.region48  = IMG_48;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
