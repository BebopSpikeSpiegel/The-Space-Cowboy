package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;
import powers.FlowPower;

/**
 * Shared base for all Spike cards. Centralizes the Spike color, the localized
 * NAME/DESCRIPTION lookup (matching the pattern in the original Bang_Spike), and
 * the common Flow / damage / block / draw helpers so individual cards stay tiny.
 */
public abstract class AbstractSpikeCard extends CustomCard {

    public static final String PLACEHOLDER_IMG = "img/cards_Spike/StraightLead.png";

    public AbstractSpikeCard(String id, String imgPath, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(id,
                CardCrawlGame.languagePack.getCardStrings(id).NAME,
                imgPath,
                cost,
                CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION,
                type, AbstractCardEnum.Spike_COLOR, rarity, target);
    }

    // ---- helpers -------------------------------------------------------------

    protected void dealDamage(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), fx));
    }

    protected void dealDamageToAll(AbstractGameAction.AttackEffect fx) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,
                this.multiDamage, this.damageTypeForTurn, fx));
    }

    protected void gainBlock() {
        gainBlock(this.block);
    }

    protected void gainBlock(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, amount));
    }

    protected void drawCards(int n) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, n));
    }

    // ---- Flow ----------------------------------------------------------------

    protected void gainFlow(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlowPower(p, amount), amount));
    }

    public static int getFlow() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.hasPower(FlowPower.POWER_ID)) {
            return p.getPower(FlowPower.POWER_ID).amount;
        }
        return 0;
    }

    protected void spendFlow(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.hasPower(FlowPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, FlowPower.POWER_ID, amount));
        }
    }

    protected void loseAllFlow() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.hasPower(FlowPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, FlowPower.POWER_ID));
        }
    }
}
