package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** The church shootout — AoE that also feeds your Flow. */
public class BalladOfFallenAngels_Spike extends AbstractSpikeCard {
    public static final String ID = "BalladOfFallenAngels_Spike";
    private static final String IMG = "img/cards_Spike/BalladOfFallenAngels.png";
    private static final int COST = 2;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_DMG = 3;
    private static final int FLOW = 2;

    public BalladOfFallenAngels_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = FLOW;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamageToAll(AbstractGameAction.AttackEffect.SLASH_HEAVY);
        gainFlow(this.magicNumber);
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
        return new BalladOfFallenAngels_Spike();
    }
}
