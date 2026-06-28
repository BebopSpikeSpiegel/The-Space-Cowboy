package cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SympathyForTheDevil_Spike extends AbstractSpikeCard {
    public static final String ID = "SympathyForTheDevil_Spike";
    private static final String IMG = "img/cards_Spike/SympathyForTheDevil.png";
    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_DMG = 3;
    private static final int HEAL = 2;
    private static final int BOOST_HEAL = 5;
    private static final int FLOW_THRESHOLD = 5;

    public SympathyForTheDevil_Spike() {
        super(ID, IMG, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = HEAL;
    }

    @Override
    public void applyPowers() {
        this.baseMagicNumber = this.magicNumber = getFlow() >= FLOW_THRESHOLD ? BOOST_HEAL : HEAL;
        super.applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealDamage(m, AbstractGameAction.AttackEffect.FIRE);
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
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
        return new SympathyForTheDevil_Spike();
    }
}
