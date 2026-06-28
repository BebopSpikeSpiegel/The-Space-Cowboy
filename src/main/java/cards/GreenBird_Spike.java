package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class GreenBird_Spike extends AbstractSpikeCard {
    public static final String ID = "GreenBird_Spike";
    private static final String IMG = "img/cards_Spike/GreenBird.png";
    private static final int COST = 2;
    private static final int BLOCK = 12;
    private static final int UPGRADE_BLOCK = 4;
    private static final int DEX = 1;

    public GreenBird_Spike() {
        super(ID, IMG, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = DEX;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GreenBird_Spike();
    }
}
