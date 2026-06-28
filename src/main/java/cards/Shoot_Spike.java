package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Shoot_Spike extends AbstractSpikeCard {
    public static final String ID = "Shoot_Spike";
    private static final String IMG = "img/cards_Spike/Shoot.png";
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_DMG = 3;
    private static final int VULN = 1;

    public Shoot_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = VULN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
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
        return new Shoot_Spike();
    }
}
