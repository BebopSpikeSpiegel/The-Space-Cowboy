package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Block that scales up once you're in the Flow. Boosted block still respects Dexterity/Frail. */
public class Rain_Spike extends AbstractSpikeCard {
    public static final String ID = "Rain_Spike";
    private static final String IMG = "img/cards_Spike/Rain.png";
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int BIG_BLOCK = 8;
    private static final int FLOW_THRESHOLD = 3;
    private static final int UPGRADE_BLOCK = 2;
    private static final int UPGRADE_BIG = 3;

    public Rain_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = BIG_BLOCK;
    }

    @Override
    public void applyPowers() {
        int base = this.baseBlock;
        if (getFlow() >= FLOW_THRESHOLD) {
            this.baseBlock = this.magicNumber;
        }
        super.applyPowers();
        this.baseBlock = base;
        this.isBlockModified = (this.block != base);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int saved = this.baseBlock;
        if (getFlow() >= FLOW_THRESHOLD) {
            this.baseBlock = this.magicNumber;
        }
        applyPowersToBlock();
        gainBlock(this.block);
        this.baseBlock = saved;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = getFlow() >= FLOW_THRESHOLD
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK);
            upgradeMagicNumber(UPGRADE_BIG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Rain_Spike();
    }
}
