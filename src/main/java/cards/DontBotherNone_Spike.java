package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DontBotherNone_Spike extends AbstractSpikeCard {
    public static final String ID = "DontBotherNone_Spike";
    private static final String IMG = "img/cards_Spike/DontBotherNone.png";
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DMG = 3;
    private static final int FLOW = 1;

    public DontBotherNone_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
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
        return new DontBotherNone_Spike();
    }
}
