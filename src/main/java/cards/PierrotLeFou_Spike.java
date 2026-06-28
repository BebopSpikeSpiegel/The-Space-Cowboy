package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Mad Pierrot — overwhelming burst, but the madness costs you. */
public class PierrotLeFou_Spike extends AbstractSpikeCard {
    public static final String ID = "PierrotLeFou_Spike";
    private static final String IMG = "img/cards_Spike/PierrotLeFou.png";
    private static final int COST = 2;
    private static final int DAMAGE = 20;
    private static final int UPGRADE_DMG = 6;
    private static final int SELF_DMG = 4;

    public PierrotLeFou_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = SELF_DMG;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));
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
        return new PierrotLeFou_Spike();
    }
}
