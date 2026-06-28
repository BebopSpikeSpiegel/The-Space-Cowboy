package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Quick attack that keeps cards flowing. */
public class StrayDogStrut_Spike extends AbstractSpikeCard {
    public static final String ID = "StrayDogStrut_Spike";
    private static final String IMG = "img/cards_Spike/StrayDogStrut.png";
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_DMG = 3;
    private static final int DRAW = 1;

    public StrayDogStrut_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        drawCards(this.magicNumber);
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
        return new StrayDogStrut_Spike();
    }
}
