package events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

public class BigShotEvent extends AbstractImageEvent {
    public static final String ID = "BigShot_Spike";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String IMG = "img/events/BigShot_Spike.png";

    private int screenNum = 0;

    public BigShotEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);
        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        AbstractDungeon.player.gainGold(75);
                        this.screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[2]);
                        break;
                    case 1:
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[2]);
                        this.screenNum = 1;
                        break;
                }
                break;
            case 1:
                this.openMap();
                break;
        }
    }
}
