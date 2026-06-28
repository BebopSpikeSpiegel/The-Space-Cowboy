package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/** The data dog: a peek at what's coming each fight. */
public class Ein extends CustomRelic {
    public static final String ID = "Ein";
    private static final String IMG = "img/relics_Spike/Ein.png";
    private static final String IMG_OTL = "img/relics_Spike/outline/Ein.png";
    private static final int SCRY = 3;

    public Ein() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ScryAction(SCRY));
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Ein();
    }
}
