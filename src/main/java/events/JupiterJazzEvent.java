package events;

import cards.JupiterJazz_Spike;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class JupiterJazzEvent extends AbstractImageEvent {
    public static final String ID = "JupiterJazz_Spike";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String IMG = "img/events/JupiterJazz_Spike.png";

    private static final int HP_LOSS = 8;
    private static final int HEAL = 15;
    private static final int GOLD_COST = 50;

    private int screenNum = 0;

    public JupiterJazzEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);
        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1], AbstractDungeon.player.gold < GOLD_COST);
        imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0: // Listen — lose HP, obtain the Jupiter Jazz card
                        AbstractDungeon.player.damage(
                                new DamageInfo(null, HP_LOSS, DamageInfo.DamageType.HP_LOSS));
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
                                new JupiterJazz_Spike(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        endScreen(DESCRIPTIONS[1]);
                        break;
                    case 1: // Buy a round — lose gold, heal
                        AbstractDungeon.player.loseGold(GOLD_COST);
                        AbstractDungeon.player.heal(HEAL);
                        endScreen(DESCRIPTIONS[2]);
                        break;
                    case 2: // Move on
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
        imageEventText.setDialogOption(OPTIONS[3]);
    }
}
