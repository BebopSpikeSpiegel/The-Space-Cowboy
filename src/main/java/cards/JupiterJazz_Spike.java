package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.JupiterJazzPower;

public class JupiterJazz_Spike extends AbstractSpikeCard {
    public static final String ID = "JupiterJazz_Spike";
    private static final String IMG = "img/cards_Spike/JupiterJazz.png";
    private static final int COST = 1;
    private static final int STRENGTH = 1;
    private static final int UPGRADE_STRENGTH = 1;

    public JupiterJazz_Spike() {
        super(ID, IMG, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = STRENGTH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new JupiterJazzPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_STRENGTH);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new JupiterJazz_Spike();
    }
}
