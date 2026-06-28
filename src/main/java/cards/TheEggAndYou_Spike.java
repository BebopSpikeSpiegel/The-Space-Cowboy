package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.EggAndYouPower;

/** The Egg and You — incubate your momentum into armor at the end of each turn. */
public class TheEggAndYou_Spike extends AbstractSpikeCard {
    public static final String ID = "TheEggAndYou_Spike";
    private static final String IMG = "img/cards_Spike/TheEggAndYou.png";
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public TheEggAndYou_Spike() {
        super(ID, IMG, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new EggAndYouPower(p), 1));
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
        return new TheEggAndYou_Spike();
    }
}
