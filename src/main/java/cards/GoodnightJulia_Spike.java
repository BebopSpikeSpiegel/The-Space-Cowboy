package cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Goodnight Julia — the cost of love lost. Pay in blood for a Flow surge (feeds Space Lion). */
public class GoodnightJulia_Spike extends AbstractSpikeCard {
    public static final String ID = "GoodnightJulia_Spike";
    private static final String IMG = "img/cards_Spike/GoodnightJulia.png";
    private static final int COST = 1;
    private static final int HP_LOSS = 3;
    private static final int FLOW = 3;
    private static final int UPGRADE_FLOW = 1;
    private static final int DRAW = 1;

    public GoodnightJulia_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, HP_LOSS));
        gainFlow(this.magicNumber);
        drawCards(DRAW);
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
        return new GoodnightJulia_Spike();
    }
}
