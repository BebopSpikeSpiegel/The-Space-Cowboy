package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/** Asteroid Blues — Session 1, the explosive debut. Lead with it for a Flow burst. */
public class AsteroidBlues_Spike extends AbstractSpikeCard {
    public static final String ID = "AsteroidBlues_Spike";
    private static final String IMG = "img/cards_Spike/AsteroidBlues.png";
    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_DMG = 3;
    private static final int FLOW = 2;

    public AsteroidBlues_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = FLOW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= 1) {
            gainFlow(this.magicNumber);
        }
    }

    /** Gold glow while it would still be the first card played this turn (bonus active). */
    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
        }
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
        return new AsteroidBlues_Spike();
    }
}
