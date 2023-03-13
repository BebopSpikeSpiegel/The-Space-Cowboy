package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Marlboro extends CustomRelic {
    public static final String ID = "Marlboro";
    private static final String IMG = "img/relics_Spike/Marlboro.png";
    private static final String IMG_OTL = "img/relics_Spike/outline/Marlboro.png";

    public Marlboro() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //if (card.type == AbstractCard.LOCKED_STRING.equals("Straight_Lead_Spike"))
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.counter++;
            if (this.counter % 5 == 0) {
                this.counter = 0;
                flash();
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player,
                        (AbstractRelic)this));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainEnergyAction(1));
            }
        }
    }

    @Override
    public void onVictory() {
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new Marlboro();
    }
}
