package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MushroomHunting_Spike extends AbstractSpikeCard {
    public static final String ID = "MushroomHunting_Spike";
    private static final String IMG = "img/cards_Spike/MushroomHunting.png";
    private static final int COST = 0;
    private static final int FLOW = 3;
    private static final int UPGRADE_FLOW = 1;
    private static final int DRAW = 1;

    public MushroomHunting_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = FLOW;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainFlow(this.magicNumber);
        drawCards(DRAW);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) { upgradeName(); upgradeMagicNumber(UPGRADE_FLOW); }
    }

    @Override
    public AbstractCard makeCopy() { return new MushroomHunting_Spike(); }
}
