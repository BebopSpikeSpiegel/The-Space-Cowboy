package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Zero-cost chain fuel. */
public class SpokeyDokey_Spike extends AbstractSpikeCard {
    public static final String ID = "SpokeyDokey_Spike";
    private static final String IMG = "img/cards_Spike/SpokeyDokey.png";
    private static final int COST = 0;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_DMG = 2;

    public SpokeyDokey_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
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
        return new SpokeyDokey_Spike();
    }
}
