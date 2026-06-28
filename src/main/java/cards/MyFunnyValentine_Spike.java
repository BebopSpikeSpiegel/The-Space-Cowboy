package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** My Funny Valentine — Faye's lost past. Retain it, then turn banked Flow into a wall. */
public class MyFunnyValentine_Spike extends AbstractSpikeCard {
    public static final String ID = "MyFunnyValentine_Spike";
    private static final String IMG = "img/cards_Spike/MyFunnyValentine.png";
    private static final int COST = 1;
    private static final int PER_FLOW = 2;
    private static final int UPGRADE_PER_FLOW = 1;

    public MyFunnyValentine_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 0;
        this.baseMagicNumber = this.magicNumber = PER_FLOW;
        this.selfRetain = true;
    }

    @Override
    public void applyPowers() {
        int base = this.baseBlock;
        this.baseBlock = getFlow() * this.magicNumber;
        super.applyPowers();
        this.baseBlock = base;
        this.isBlockModified = (this.block != base);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseBlock = getFlow() * this.magicNumber;
        applyPowersToBlock();
        gainBlock(this.block);
        this.baseBlock = 0;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PER_FLOW);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MyFunnyValentine_Spike();
    }
}
