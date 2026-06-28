package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Spike's signature basic ("be like water") — teaches Flow. */
public class JeetKuneDo_Spike extends AbstractSpikeCard {
    public static final String ID = "JeetKuneDo_Spike";
    private static final String IMG = "img/cards_Spike/JeetKuneDo.png";
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DMG = 2;
    private static final int FLOW = 1;

    public JeetKuneDo_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
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
        return new JeetKuneDo_Spike();
    }
}
