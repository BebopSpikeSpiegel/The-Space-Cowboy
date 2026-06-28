package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Multi-hit common — great Flow/energy with Strength. */
public class BadDogNoBiscuits_Spike extends AbstractSpikeCard {
    public static final String ID = "BadDogNoBiscuits_Spike";
    private static final String IMG = "img/cards_Spike/BadDogNoBiscuits.png";
    private static final int COST = 1;
    private static final int DAMAGE = 3;
    private static final int UPGRADE_DMG = 1;
    private static final int HITS = 2;

    public BadDogNoBiscuits_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = HITS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
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
        return new BadDogNoBiscuits_Spike();
    }
}
