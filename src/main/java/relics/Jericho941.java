package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.FlowPower;

/** Spike's handgun: rewards leading with an Attack each turn. */
public class Jericho941 extends CustomRelic {
    public static final String ID = "Jericho941";
    private static final String IMG = "img/relics_Spike/Jericho941.png";
    private static final String IMG_OTL = "img/relics_Spike/outline/Jericho941.png";
    private static final int FLOW = 1;
    private boolean usedThisTurn = false;

    public Jericho941() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void atTurnStart() {
        this.usedThisTurn = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!this.usedThisTurn && card.type == AbstractCard.CardType.ATTACK) {
            this.usedThisTurn = true;
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    AbstractDungeon.player, AbstractDungeon.player, new FlowPower(AbstractDungeon.player, FLOW), FLOW));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Jericho941();
    }
}
