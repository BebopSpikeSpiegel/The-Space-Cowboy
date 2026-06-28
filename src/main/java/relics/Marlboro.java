package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.FlowPower;

/** Starter relic: light up and get in the Flow at the start of each fight. */
public class Marlboro extends CustomRelic {
    public static final String ID = "Marlboro";
    private static final String IMG = "img/relics_Spike/Marlboro.png";
    private static final String IMG_OTL = "img/relics_Spike/outline/Marlboro.png";
    private static final int START_FLOW = 2;

    public Marlboro() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player, new FlowPower(AbstractDungeon.player, START_FLOW), START_FLOW));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Marlboro();
    }
}
