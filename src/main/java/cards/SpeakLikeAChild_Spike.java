package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SpeakLikeAChild_Spike extends AbstractSpikeCard {
    public static final String ID = "SpeakLikeAChild_Spike";
    private static final String IMG = "img/cards_Spike/SpeakLikeAChild.png";
    private static final int COST = 1;
    private static final int DRAW = 2;
    private static final int UPGRADE_DRAW = 1;
    private static final int FLOW = 1;

    public SpeakLikeAChild_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(this.magicNumber);
        gainFlow(FLOW);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_DRAW);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpeakLikeAChild_Spike();
    }
}
