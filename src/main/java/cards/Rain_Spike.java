package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Spend Flow for Block. Boosted block still respects Dexterity/Frail. */
public class Rain_Spike extends AbstractSpikeCard {
    public static final String ID = "Rain_Spike";
    private static final String IMG = "img/cards_Spike/Rain.png";
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 2;
    private static final int MAX_SPEND = 3;
    private static final int UPGRADE_SPEND = 1;
    private static final int PER_FLOW_BLOCK = 3;

    public Rain_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        // magicNumber = the most Flow this card will spend (shown as !M!).
        this.baseMagicNumber = this.magicNumber = MAX_SPEND;
    }

    @Override
    public void applyPowers() {
        int base = this.baseBlock;
        int spend = Math.min(getFlow(), this.magicNumber);
        this.baseBlock = base + spend * PER_FLOW_BLOCK;
        super.applyPowers();
        this.baseBlock = base;
        this.isBlockModified = (this.block != base);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int saved = this.baseBlock;
        int spend = Math.min(getFlow(), this.magicNumber);
        this.baseBlock = saved + spend * PER_FLOW_BLOCK;
        applyPowersToBlock();
        gainBlock(this.block);
        this.baseBlock = saved;
        if (spend > 0) {
            spendFlow(spend);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = getFlow() > 0
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK);
            upgradeMagicNumber(UPGRADE_SPEND);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Rain_Spike();
    }
}
