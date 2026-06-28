package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/** Blue — the swan song. Every card you play stokes the Flow. */
public class BluePower extends AbstractPower {
    public static final String POWER_ID = "Blue_Spike";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String ICON_128 = "img/UI_Spike/powers/Blue_Spike.png";
    private static final String ICON_48  = "img/UI_Spike/powers/Blue_Spike48.png";
    private static final TextureAtlas.AtlasRegion IMG_128 = SpikeIcons.load(ICON_128);
    private static final TextureAtlas.AtlasRegion IMG_48  = SpikeIcons.load(ICON_48);

    public BluePower(AbstractCreature owner, int amount) {
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
    public void onUseCard(AbstractCard card, UseCardAction action) {
        flash();
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(this.owner, this.owner, new FlowPower(this.owner, this.amount), this.amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
