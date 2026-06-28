package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StraightLead_Spike extends AbstractSpikeCard {
    public static final String ID = "StraightLead_Spike";
    private static final String IMG = "img/cards_Spike/StraightLead.png";
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_DMG = 3;

    public StraightLead_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) { upgradeName(); upgradeDamage(UPGRADE_DMG); }
    }

    @Override
    public AbstractCard makeCopy() { return new StraightLead_Spike(); }
}
