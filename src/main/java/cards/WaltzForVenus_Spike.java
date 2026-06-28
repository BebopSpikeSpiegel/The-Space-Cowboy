package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WaltzForVenus_Spike extends AbstractSpikeCard {
    public static final String ID = "WaltzForVenus_Spike";
    private static final String IMG = "img/cards_Spike/WaltzForVenus.png";
    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int UPGRADE_BLOCK = 3;
    private static final int DRAW = 1;

    public WaltzForVenus_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
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
        return new WaltzForVenus_Spike();
    }
}
