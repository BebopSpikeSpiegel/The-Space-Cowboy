package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/** The Egg and You — incubate your momentum overnight. At end of turn, gain Block equal to your Flow. */
public class EggAndYouPower extends AbstractPower {
    public static final String POWER_ID = "EggAndYou_Spike";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String ICON_128 = "img/UI_Spike/powers/EggAndYou_Spike.png";
    private static final String ICON_48  = "img/UI_Spike/powers/EggAndYou_Spike48.png";
    private static final Texture TEX_128 = ImageMaster.loadImage(ICON_128);
    private static final Texture TEX_48  = ImageMaster.loadImage(ICON_48);
    private static final TextureAtlas.AtlasRegion IMG_128 = new TextureAtlas.AtlasRegion(TEX_128, 0, 0, TEX_128.getWidth(), TEX_128.getHeight());
    private static final TextureAtlas.AtlasRegion IMG_48  = new TextureAtlas.AtlasRegion(TEX_48,  0, 0, TEX_48.getWidth(),  TEX_48.getHeight());

    public EggAndYouPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.region128 = IMG_128;
        this.region48  = IMG_48;
        updateDescription();
    }

    /** No stacking count — re-playing simply refreshes the effect. */
    @Override
    public void stackPower(int stackAmount) {
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer) {
            return;
        }
        int flow = this.owner.hasPower(FlowPower.POWER_ID) ? this.owner.getPower(FlowPower.POWER_ID).amount : 0;
        if (flow > 0) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, flow));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
