package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.TankPower;

/** Tank! — the engine power: Flow every turn. */
public class Tank_Spike extends AbstractSpikeCard {
    public static final String ID = "Tank_Spike";
    private static final String IMG = "img/cards_Spike/Tank.png";
    private static final int COST = 2;
    private static final int FLOW = 2;
    private static final int UPGRADE_FLOW = 1;

    public Tank_Spike() {
        super(ID, IMG, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new TankPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_FLOW);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Tank_Spike();
    }
}
