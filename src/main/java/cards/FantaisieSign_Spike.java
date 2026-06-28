package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Fantaisie Sign — upbeat and light on its feet. Cheap Flow accelerant. */
public class FantaisieSign_Spike extends AbstractSpikeCard {
    public static final String ID = "FantaisieSign_Spike";
    private static final String IMG = "img/cards_Spike/FantaisieSign.png";
    private static final int COST = 0;
    private static final int FLOW = 2;
    private boolean drawUp = false;

    public FantaisieSign_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainFlow(this.magicNumber);
        if (this.drawUp) {
            drawCards(1);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.drawUp = true;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FantaisieSign_Spike();
    }
}
