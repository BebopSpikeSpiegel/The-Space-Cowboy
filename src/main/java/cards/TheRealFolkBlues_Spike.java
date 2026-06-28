package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** The finale — a big AoE swing that refuels your hand and your Flow. */
public class TheRealFolkBlues_Spike extends AbstractSpikeCard {
    public static final String ID = "TheRealFolkBlues_Spike";
    private static final String IMG = "img/cards_Spike/TheRealFolkBlues.png";
    private static final int COST = 3;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DMG = 3;
    private static final int DRAW = 2;
    private static final int FLOW = 2;

    public TheRealFolkBlues_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = DRAW;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamageToAll(AbstractGameAction.AttackEffect.SLASH_HEAVY);
        drawCards(this.magicNumber);
        gainFlow(FLOW);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DMG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheRealFolkBlues_Spike();
    }
}
