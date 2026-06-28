package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.SpaceLionPower;

/** Space Lion — turn the weight you carry into strength. */
public class SpaceLion_Spike extends AbstractSpikeCard {
    public static final String ID = "SpaceLion_Spike";
    private static final String IMG = "img/cards_Spike/SpaceLion.png";
    private static final int COST = 1;
    private static final int STRENGTH = 2;
    private static final int UPGRADE_STRENGTH = 1;

    public SpaceLion_Spike() {
        super(ID, IMG, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = STRENGTH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new SpaceLionPower(p, this.magicNumber), this.magicNumber));
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
        return new SpaceLion_Spike();
    }
}
