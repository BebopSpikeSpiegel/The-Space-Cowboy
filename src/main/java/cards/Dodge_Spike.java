package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Dodge_Spike extends AbstractSpikeCard {
    public static final String ID = "Dodge_Spike";
    private static final String IMG = "img/cards_Spike/Dodge.png";
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BLOCK = 3;

    public Dodge_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { gainBlock(); }

    @Override
    public void upgrade() {
        if (!this.upgraded) { upgradeName(); upgradeBlock(UPGRADE_BLOCK); }
    }

    @Override
    public AbstractCard makeCopy() { return new Dodge_Spike(); }
}
