package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** 24 Hours Open — always-on. Block plus a draw to keep the hand stocked. */
public class TwentyFourHoursOpen_Spike extends AbstractSpikeCard {
    public static final String ID = "TwentyFourHoursOpen_Spike";
    private static final String IMG = "img/cards_Spike/TwentyFourHoursOpen.png";
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 3;
    private static final int DRAW = 1;

    public TwentyFourHoursOpen_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        drawCards(this.magicNumber);
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
        return new TwentyFourHoursOpen_Spike();
    }
}
