package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

/** Space Lion — pay in blood, grow stronger. Lose HP, gain Strength. */
public class SpaceLionPower extends AbstractPower {
    public static final String POWER_ID = "SpaceLion_Spike";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String ICON_128 = "img/UI_Spike/powers/SpaceLion_Spike.png";
    private static final String ICON_48  = "img/UI_Spike/powers/SpaceLion_Spike48.png";
    private static final Texture TEX_128 = ImageMaster.loadImage(ICON_128);
    private static final Texture TEX_48  = ImageMaster.loadImage(ICON_48);
    private static final TextureAtlas.AtlasRegion IMG_128 = new TextureAtlas.AtlasRegion(TEX_128, 0, 0, TEX_128.getWidth(), TEX_128.getHeight());
    private static final TextureAtlas.AtlasRegion IMG_48  = new TextureAtlas.AtlasRegion(TEX_48,  0, 0, TEX_48.getWidth(),  TEX_48.getHeight());

    public SpaceLionPower(AbstractCreature owner, int amount) {
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
        this.amount += stackAmount;
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
