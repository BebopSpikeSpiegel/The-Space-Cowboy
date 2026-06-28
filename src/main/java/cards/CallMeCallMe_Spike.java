package cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Call Me Call Me — longing for what's gone. Recall a card you've already played. */
public class CallMeCallMe_Spike extends AbstractSpikeCard {
    public static final String ID = "CallMeCallMe_Spike";
    private static final String IMG = "img/cards_Spike/CallMeCallMe.png";
    private static final int COST = 1;
    private static final int FLOW = 1;

    public CallMeCallMe_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.discardPile.group.isEmpty()) {
            AbstractCard c = p.discardPile.getRandomCard(AbstractDungeon.cardRandomRng).makeStatEquivalentCopy();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
        }
        gainFlow(this.magicNumber);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cost = 0;
            this.costForTurn = 0;
            this.upgradedCost = true;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CallMeCallMe_Spike();
    }
}
