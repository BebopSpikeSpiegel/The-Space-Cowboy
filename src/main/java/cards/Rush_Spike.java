package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Rush_Spike extends AbstractSpikeCard {
    public static final String ID = "Rush_Spike";
    private static final String IMG = "img/cards_Spike/Rush.png";
    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_DMG = 3;
    private static final int FLOW = 1;

    public Rush_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        gainFlow(this.magicNumber);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) { upgradeName(); upgradeDamage(UPGRADE_DMG); }
    }

    @Override
    public AbstractCard makeCopy() { return new Rush_Spike(); }
}
