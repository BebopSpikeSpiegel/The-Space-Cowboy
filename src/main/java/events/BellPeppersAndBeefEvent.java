package events;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

/** "Bell Peppers and Beef (no beef)" — Spike's broke-day diner staple. A small heal, or pay for the real thing. */
public class BellPeppersAndBeefEvent extends AbstractImageEvent {
    public static final String ID = "BellPeppersAndBeef_Spike";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String IMG = "img/events/BellPeppersAndBeef_Spike.png";

    private static final int GOLD_COST = 30;
    private static final float EAT_PCT = 0.20f;
    private static final float BEEF_PCT = 0.40f;

    private final int eatHeal;
    private final int beefHeal;
    private int screenNum = 0;

    public BellPeppersAndBeefEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);
        this.eatHeal = MathUtils.round(AbstractDungeon.player.maxHealth * EAT_PCT);
        this.beefHeal = MathUtils.round(AbstractDungeon.player.maxHealth * BEEF_PCT);

        imageEventText.setDialogOption(OPTIONS[0] + this.eatHeal + OPTIONS[1]);
        imageEventText.setDialogOption(OPTIONS[2] + GOLD_COST + OPTIONS[3] + this.beefHeal + OPTIONS[4],
                AbstractDungeon.player.gold < GOLD_COST);
        imageEventText.setDialogOption(OPTIONS[5]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0: // Eat the peppers (free heal)
                        AbstractDungeon.player.heal(this.eatHeal);
                        endScreen(DESCRIPTIONS[1]);
                        break;
                    case 1: // Splurge for the beef
                        AbstractDungeon.player.loseGold(GOLD_COST);
                        AbstractDungeon.player.heal(this.beefHeal);
                        endScreen(DESCRIPTIONS[2]);
                        break;
                    case 2: // Leave
                        endScreen(DESCRIPTIONS[3]);
                        break;
                }
                break;
            case 1:
                this.openMap();
                break;
        }
    }

    private void endScreen(String text) {
        this.screenNum = 1;
        imageEventText.updateBodyText(text);
        imageEventText.clearAllDialogs();
        imageEventText.setDialogOption(OPTIONS[6]);
    }
}
