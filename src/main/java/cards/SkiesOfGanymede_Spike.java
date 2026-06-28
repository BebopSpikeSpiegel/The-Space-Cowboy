package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

/** Skies of Ganymede — Faye draws first. A precise shot that throws the mark off balance. */
public class SkiesOfGanymede_Spike extends AbstractSpikeCard {
    public static final String ID = "SkiesOfGanymede_Spike";
    private static final String IMG = "img/cards_Spike/SkiesOfGanymede.png";
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DMG = 3;
    private static final int WEAK = 2;
    private static final int FLOW = 1;

    public SkiesOfGanymede_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = WEAK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        gainFlow(FLOW);
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
        return new SkiesOfGanymede_Spike();
    }
}
