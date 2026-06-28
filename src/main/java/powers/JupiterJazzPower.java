package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

/** Jupiter Jazz — end-of-turn Strength ramp, sweeter when you're in the Flow. */
public class JupiterJazzPower extends AbstractPower {
    public static final String POWER_ID = "JupiterJazz_Spike";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String ICON_128 = "img/UI_Spike/powers/JupiterJazz_Spike.png";
    private static final String ICON_48  = "img/UI_Spike/powers/JupiterJazz_Spike48.png";
    private static final Texture TEX_128 = ImageMaster.loadImage(ICON_128);
    private static final Texture TEX_48  = ImageMaster.loadImage(ICON_48);
    private static final TextureAtlas.AtlasRegion IMG_128 = new TextureAtlas.AtlasRegion(TEX_128, 0, 0, TEX_128.getWidth(), TEX_128.getHeight());
    private static final TextureAtlas.AtlasRegion IMG_48  = new TextureAtlas.AtlasRegion(TEX_48,  0, 0, TEX_48.getWidth(),  TEX_48.getHeight());
    private static final int FLOW_THRESHOLD = 6;

    public JupiterJazzPower(AbstractCreature owner, int amount) {
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
    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer) {
            return;
        }
        int str = this.amount;
        if (this.owner.hasPower(FlowPower.POWER_ID)
                && this.owner.getPower(FlowPower.POWER_ID).amount >= FLOW_THRESHOLD) {
            str += 1;
        }
        flash();
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, str), str));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
