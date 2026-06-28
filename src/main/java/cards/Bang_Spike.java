package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.CarryThatWeightPower;

/**
 * "Bang." — the signature finisher. Scales hard with Flow, dumps all your Flow,
 * and makes you carry the weight: baseline loses half your HP next turn; upgraded,
 * if any enemy survives you die next turn (see {@link CarryThatWeightPower}).
 */
public class Bang_Spike extends AbstractSpikeCard {
    public static final String ID = "Bang_Spike";
    private static final String IMG = "img/cards_Spike/Bang.png";
    private static final int COST = 2;
    private static final int DAMAGE = 9;
    private static final int PER_FLOW = 3;
    private static final int UPGRADE_PER_FLOW = 2;

    public Bang_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = PER_FLOW;
        this.exhaust = true;
    }

    @Override
    public void applyPowers() {
        int saved = this.baseDamage;
        this.baseDamage = DAMAGE + getFlow() * this.magicNumber;
        super.applyPowers();
        this.baseDamage = saved;
        this.isDamageModified = (this.damage != saved);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int saved = this.baseDamage;
        this.baseDamage = DAMAGE + getFlow() * this.magicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = saved;
        this.isDamageModified = (this.damage != saved);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        loseAllFlow();
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new CarryThatWeightPower(p, this.upgraded), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PER_FLOW);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bang_Spike();
    }
}
