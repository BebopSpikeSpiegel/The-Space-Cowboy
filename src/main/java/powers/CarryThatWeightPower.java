package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * "You're gonna carry that weight." Applied by an un-upgraded Bang.
 * At the start of your next turn, lose a small fixed amount of HP. Upgraded Bang
 * removes the downside entirely.
 */
public class CarryThatWeightPower extends AbstractPower {
    public static final String POWER_ID = "CarryThatWeight_Spike";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String ICON_128 = "img/UI_Spike/powers/CarryThatWeight_Spike.png";
    private static final String ICON_48  = "img/UI_Spike/powers/CarryThatWeight_Spike48.png";
    private static final TextureAtlas.AtlasRegion IMG_128 = SpikeIcons.load(ICON_128);
    private static final TextureAtlas.AtlasRegion IMG_48  = SpikeIcons.load(ICON_48);

    private static final int HP_LOSS = 8;

    public CarryThatWeightPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1; // flag power: hide the number
        this.type = PowerType.DEBUFF;
        this.region128 = IMG_128;
        this.region48  = IMG_48;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.owner, HP_LOSS));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
