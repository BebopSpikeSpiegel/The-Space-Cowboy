package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Retained setup card — bank block and Flow for the turn you go off. */
public class WildHorses_Spike extends AbstractSpikeCard {
    public static final String ID = "WildHorses_Spike";
    private static final String IMG = "img/cards_Spike/WildHorses.png";
    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int UPGRADE_BLOCK = 3;
    private static final int FLOW = 2;

    public WildHorses_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = FLOW;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        gainFlow(this.magicNumber);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new WildHorses_Spike();
    }
}
