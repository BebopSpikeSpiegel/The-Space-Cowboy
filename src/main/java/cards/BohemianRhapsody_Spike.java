package cards;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Bohemian Rhapsody — Session 13, the chessmaster's gambit. See ahead, build Flow. */
public class BohemianRhapsody_Spike extends AbstractSpikeCard {
    public static final String ID = "BohemianRhapsody_Spike";
    private static final String IMG = "img/cards_Spike/BohemianRhapsody.png";
    private static final int COST = 1;
    private static final int SCRY = 3;
    private static final int UPGRADE_SCRY = 1;
    private static final int FLOW = 2;

    public BohemianRhapsody_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = SCRY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ScryAction(this.magicNumber));
        gainFlow(FLOW);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_SCRY);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BohemianRhapsody_Spike();
    }
}
