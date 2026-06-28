package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Jamming with Edward — hack your whole hand into Flow. */
public class JammingWithEdward_Spike extends AbstractSpikeCard {
    public static final String ID = "JammingWithEdward_Spike";
    private static final String IMG = "img/cards_Spike/JammingWithEdward.png";
    private static final int COST = 1;
    private static final int DRAW = 2;
    private static final int UPGRADE_DRAW = 1;

    public JammingWithEdward_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = DRAW;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int handCount = AbstractDungeon.player.hand.size();
        if (handCount > 0) {
            gainFlow(handCount);
        }
        drawCards(this.magicNumber);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_DRAW);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new JammingWithEdward_Spike();
    }
}
