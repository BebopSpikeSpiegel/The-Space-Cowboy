package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Road to the West — the long road's payoff. Spend all your Flow in one blow (no death cost). */
public class RoadToTheWest_Spike extends AbstractSpikeCard {
    public static final String ID = "RoadToTheWest_Spike";
    private static final String IMG = "img/cards_Spike/RoadToTheWest.png";
    private static final int COST = 2;
    private static final int DAMAGE = 8;
    private static final int PER_FLOW = 2;
    private static final int UPGRADE_PER_FLOW = 1;

    public RoadToTheWest_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = PER_FLOW;
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
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        loseAllFlow();
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
        return new RoadToTheWest_Spike();
    }
}
