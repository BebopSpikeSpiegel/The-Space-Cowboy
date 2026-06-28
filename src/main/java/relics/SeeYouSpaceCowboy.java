package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/** Insurance against carrying that weight: cheat death's first blow each fight. */
public class SeeYouSpaceCowboy extends CustomRelic {
    public static final String ID = "SeeYouSpaceCowboy";
    private static final String IMG = "img/relics_Spike/SeeYouSpaceCowboy.png";
    private static final String IMG_OTL = "img/relics_Spike/outline/SeeYouSpaceCowboy.png";
    private static final int BUFFER = 1;

    public SeeYouSpaceCowboy() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player, new BufferPower(AbstractDungeon.player, BUFFER), BUFFER));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SeeYouSpaceCowboy();
    }
}
