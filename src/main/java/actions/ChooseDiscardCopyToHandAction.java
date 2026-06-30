package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * Let the player choose one card from their discard pile and add a stat-equivalent copy
 * to their hand (the original stays in the discard pile). Auto-takes the only card if the
 * pile has exactly one; no-op on an empty pile. Used by upgraded Call Me Call Me.
 */
public class ChooseDiscardCopyToHandAction extends AbstractGameAction {
    private static final String TITLE =
            Settings.language == Settings.GameLanguage.ZHS ? "选择一张牌加入手牌" : "Choose a card to add to your hand";

    private final AbstractPlayer p;

    public ChooseDiscardCopyToHandAction(AbstractPlayer p) {
        this.p = p;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        // Opening frame.
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.discardPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (this.p.discardPile.size() == 1) {
                addToTop(new MakeTempCardInHandAction(
                        this.p.discardPile.group.get(0).makeStatEquivalentCopy(), 1));
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(this.p.discardPile, 1, TITLE, false);
            // Move out of the opening state so we don't re-open; then just wait for a pick.
            this.duration = Settings.ACTION_DUR_MED;
            return;
        }

        // Waiting for the player's selection.
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard chosen = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            addToTop(new MakeTempCardInHandAction(chosen.makeStatEquivalentCopy(), 1));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
    }
}
