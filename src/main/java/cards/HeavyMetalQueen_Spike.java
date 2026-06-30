package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Damage that scales with your Flow (without consuming it). */
public class HeavyMetalQueen_Spike extends AbstractSpikeCard {
    public static final String ID = "HeavyMetalQueen_Spike";
    private static final String IMG = "img/cards_Spike/HeavyMetalQueen.png";
    private static final int COST = 2;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_DMG = 4;
    private static final int PER_FLOW = 3;

    public HeavyMetalQueen_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = PER_FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        loseAllFlow();
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = getFlow() > 0
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void applyPowers() {
        int base = this.baseDamage;
        this.baseDamage = base + getFlow() * this.magicNumber;
        super.applyPowers();
        this.baseDamage = base;
        this.isDamageModified = (this.damage != base);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = this.baseDamage;
        this.baseDamage = base + getFlow() * this.magicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = base;
        this.isDamageModified = (this.damage != base);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DMG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeavyMetalQueen_Spike();
    }
}
