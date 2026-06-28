package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GottaKnockALittleHarder_Spike extends AbstractSpikeCard {
    public static final String ID = "GottaKnockALittleHarder_Spike";
    private static final String IMG = "img/cards_Spike/GottaKnockALittleHarder.png";
    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_DMG = 3;

    public GottaKnockALittleHarder_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
    }

    private boolean lowHp() {
        AbstractPlayer p = AbstractDungeon.player;
        return p != null && p.currentHealth * 2 <= p.maxHealth;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (lowHp()) { this.damage *= 2; this.isDamageModified = true; }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (lowHp()) { this.damage *= 2; this.isDamageModified = true; }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) { upgradeName(); upgradeDamage(UPGRADE_DMG); }
    }

    @Override
    public AbstractCard makeCopy() { return new GottaKnockALittleHarder_Spike(); }
}
