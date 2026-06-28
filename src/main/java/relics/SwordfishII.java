package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.FlowPower;

/** Boss relic: his ship, his freedom. Open every fight with momentum and energy. */
public class SwordfishII extends CustomRelic {
    public static final String ID = "SwordfishII";
    private static final String IMG = "img/relics_Spike/SwordfishII.png";
    private static final String IMG_OTL = "img/relics_Spike/outline/SwordfishII.png";
    private static final int FLOW = 2;

    public SwordfishII() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player, new FlowPower(AbstractDungeon.player, FLOW), FLOW));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SwordfishII();
    }
}
