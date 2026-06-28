package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.BluePower;

/** Blue — the swan song. Every card you play feeds the Flow. */
public class Blue_Spike extends AbstractSpikeCard {
    public static final String ID = "Blue_Spike";
    private static final String IMG = "img/cards_Spike/Blue.png";
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    private static final int FLOW = 1;

    public Blue_Spike() {
        super(ID, IMG, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new BluePower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cost = UPGRADE_COST;
            this.costForTurn = UPGRADE_COST;
            this.upgradedCost = true;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Blue_Spike();
    }
}
