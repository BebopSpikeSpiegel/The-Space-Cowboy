package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/** The bounty hunter TV show: every win pays out. */
public class BigShot extends CustomRelic {
    public static final String ID = "BigShot";
    private static final String IMG = "img/relics_Spike/BigShot.png";
    private static final String IMG_OTL = "img/relics_Spike/outline/BigShot.png";
    private static final int GOLD = 25;

    public BigShot() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public void onVictory() {
        flash();
        AbstractDungeon.player.gainGold(GOLD);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BigShot();
    }
}
