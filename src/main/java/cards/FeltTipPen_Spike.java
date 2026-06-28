package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Cheap cycle that keeps a chain going. */
public class FeltTipPen_Spike extends AbstractSpikeCard {
    public static final String ID = "FeltTipPen_Spike";
    private static final String IMG = "img/cards_Spike/FeltTipPen.png";
    private static final int COST = 0;
    private static final int DRAW = 1;
    private static final int FLOW = 1;
    private static final int UPGRADE_FLOW = 1;

    public FeltTipPen_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(DRAW);
        gainFlow(this.magicNumber);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_FLOW);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FeltTipPen_Spike();
    }
}
