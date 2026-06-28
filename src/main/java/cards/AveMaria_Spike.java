package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Ave Maria — the assassin's hymn (a nod to a certain barcoded hitman). A clean kill once you're flowing. */
public class AveMaria_Spike extends AbstractSpikeCard {
    public static final String ID = "AveMaria_Spike";
    private static final String IMG = "img/cards_Spike/AveMaria.png";
    private static final int COST = 2;
    private static final int DAMAGE = 18;
    private static final int BIG_DAMAGE = 30;
    private static final int FLOW_THRESHOLD = 5;
    private static final int UPGRADE_DMG = 6;
    private static final int UPGRADE_BIG = 6;

    public AveMaria_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = BIG_DAMAGE;
        this.exhaust = true;
    }

    @Override
    public void applyPowers() {
        int base = this.baseDamage;
        if (getFlow() >= FLOW_THRESHOLD) {
            this.baseDamage = this.magicNumber;
        }
        super.applyPowers();
        this.baseDamage = base;
        this.isDamageModified = (this.damage != base);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = this.baseDamage;
        if (getFlow() >= FLOW_THRESHOLD) {
            this.baseDamage = this.magicNumber;
        }
        super.calculateCardDamage(mo);
        this.baseDamage = base;
        this.isDamageModified = (this.damage != base);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DMG);
            upgradeMagicNumber(UPGRADE_BIG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AveMaria_Spike();
    }
}
