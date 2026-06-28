package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Memory — the gentle opening-track ED. Defensive Flow builder. */
public class Memory_Spike extends AbstractSpikeCard {
    public static final String ID = "Memory_Spike";
    private static final String IMG = "img/cards_Spike/Memory.png";
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BLOCK = 3;
    private static final int FLOW = 1;

    public Memory_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        gainFlow(FLOW);
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
        return new Memory_Spike();
    }
}
