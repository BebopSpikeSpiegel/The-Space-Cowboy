package events;

import cards.JupiterJazz_Spike;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class JupiterJazzEvent extends AbstractImageEvent {
    public static final String ID = "JupiterJazz_Spike";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String IMG = "img/events/JupiterJazz_Spike.png";

    private int screenNum = 0;

    public JupiterJazzEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);
        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[1], AbstractDungeon.player.gold < 50);
        imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0: // Listen — lose 8 HP, gain 2 Strength
                        AbstractDungeon.player.damage(
                                new com.megacrit.cardcrawl.cards.DamageInfo(
                                        null, 8, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.HP_LOSS));
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                        new StrengthPower(AbstractDungeon.player, 2), 2));
                        this.screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[3]);
                        break;
                    case 1: // Buy a round — lose 50 gold, gain JupiterJazz card
                        AbstractDungeon.player.loseGold(50);
                        AbstractDungeon.topLevelEffectsQueue.add(
                                new ShowCardAndObtainEffect(new JupiterJazz_Spike(),
                                        Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                        this.screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[3]);
                        break;
                    case 2: // Move on
                        this.screenNum = 1;
                        imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[3]);
                        break;
                }
                break;
            case 1:
                this.openMap();
                break;
        }
    }
}
