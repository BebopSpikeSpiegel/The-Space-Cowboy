package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * "You're gonna carry that weight." Applied by Bang.
 *  - baseline: at the start of your next turn, lose half your current HP.
 *  - lethal (upgraded Bang.): at the start of your next turn, if any enemy is alive, you die.
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

    private final boolean lethal;

    public CarryThatWeightPower(AbstractCreature owner) {
        this(owner, false);
    }

    public CarryThatWeightPower(AbstractCreature owner, boolean lethal) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1; // flag power: hide the number
        this.lethal = lethal;
        this.type = PowerType.DEBUFF;
        this.region128 = IMG_128;
        this.region48  = IMG_48;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        flash();
        if (this.lethal) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.addToBottom(
                        new LoseHPAction(this.owner, this.owner, this.owner.currentHealth));
            }
        } else {
            int loss = this.owner.currentHealth / 2;
            if (loss < 1) {
                loss = 1;
            }
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.owner, loss));
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = this.lethal ? DESCRIPTIONS[1] : DESCRIPTIONS[0];
    }
}
