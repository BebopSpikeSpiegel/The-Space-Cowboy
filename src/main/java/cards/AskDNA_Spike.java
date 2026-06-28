package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;

/** "Ask DNA" — DNA replicates: your next Attack this turn is played twice. */
public class AskDNA_Spike extends AbstractSpikeCard {
    public static final String ID = "AskDNA_Spike";
    private static final String IMG = "img/cards_Spike/AskDNA.png";
    private static final int COST = 1;

    public AskDNA_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new DoubleTapPower(p, 1), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AskDNA_Spike();
    }
}
