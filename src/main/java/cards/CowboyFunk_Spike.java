package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Cowboy Andy duel — more hits once you're flowing. */
public class CowboyFunk_Spike extends AbstractSpikeCard {
    public static final String ID = "CowboyFunk_Spike";
    private static final String IMG = "img/cards_Spike/CowboyFunk.png";
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DMG = 2;
    private static final int HITS = 2;
    private static final int FLOW_THRESHOLD = 3;

    public CowboyFunk_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = HITS;
    }

    @Override
    public void applyPowers() {
        this.baseMagicNumber = this.magicNumber = getFlow() >= FLOW_THRESHOLD ? HITS + 1 : HITS;
        super.applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            dealDamage(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = getFlow() >= FLOW_THRESHOLD
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
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
        return new CowboyFunk_Spike();
    }
}
