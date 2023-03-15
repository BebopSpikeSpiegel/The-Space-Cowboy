package characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import pathes.AbstractCardEnum;
import pathes.ClassEnum;

import java.util.ArrayList;

public class Spike extends CustomPlayer {
    //Energy
    private static final int ENERGY_PER_TURN = 3;
    //imgs
    private static final String SPIKE_SHOULDER_2 = "img/char_Spike/shoulder2.png";
    private static final String SPIKE_SHOULDER_1 = "img/char_Spike/shoulder1.png";
    private static final String SPIKE_CORPSE = "img/char_Spike/fallen.png";
    private static final String SPIKE_STAND = "img/char_Spike/Spike.png";

    //stuff
    private static final String[] ORB_TEXTURES = new String[] { "img/UI_Spike/EPanel/layer5.png", "img/UI_Spike/EPanel/layer4.png", "img/UI_Spike/EPanel/layer3.png", "img/UI_Spike/EPanel/layer2.png", "img/UI_Spike/EPanel/layer1.png", "img/UI_Spike/EPanel/layer0.png", "img/UI_Spike/EPanel/layer5d.png", "img/UI_Spike/EPanel/layer4d.png", "img/UI_Spike/EPanel/layer3d.png", "img/UI_Spike/EPanel/layer2d.png", "img/UI_Spike/EPanel/layer1d.png" };
    private static final String ORB_VFX = "img/UI_Spike/energyBlueVFX.png";
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };

    //HP, Gold etc
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;

    //Color
    public static final Color BLUE = CardHelper.getColor(0,39,127);

    public Spike(String name) {
        super(name, ClassEnum.Spike_CLASS, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, (String)null,(String)null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        initializeClass(SPIKE_STAND, SPIKE_SHOULDER_2, SPIKE_SHOULDER_1,SPIKE_CORPSE,
                getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        // Initial Deck
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Bang_Spike");
        retVal.add("Bang_Spike");
        retVal.add("Bang_Spike");
        retVal.add("Bang_Spike");
        retVal.add("Bang_Spike");
        retVal.add("Bang_Spike");
        retVal.add("Bang_Spike");
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        // Initial Relics
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Marlboro");
        UnlockTracker.markRelicAsSeen("Marlboro");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        //text description
        String title="";
        String flavor="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title= "*星际牛仔*";
            flavor= "在某一次通过GATE的跨空间旅行中， NL " +
                    "这个来自西元2071年的太空赏金猎人意外的来到了这座尖塔。";
        } else {
            title = "The Space Cowboy";
            flavor = "After a mishap traveling through the GATE, NL " +
                    "the space cowboy finds himself at the spire.";
        }

        return new CharSelectInfo(
                title,
                flavor,
                STARTING_HP,
                MAX_HP,
                HAND_SIZE,
                STARTING_GOLD,
                ASCENSION_MAX_HP_LOSS,
                this,
                getStartingRelics(),
                getStartingDeck(),
                false
                );

    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        //Name on the top left
        String title="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "*星际牛仔*";
        } else {
            title = "The Space Cowboy";
        }

        return title;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        //Choose the color of the cards
        return AbstractCardEnum.Spike_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return BLUE;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return BLUE;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }
    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        String char_name;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            char_name = "*星际牛仔*";
        } else {
            char_name = "The Space Cowboy";
        }
        return char_name;
    }

    @Override
    public AbstractPlayer newInstance() {
        return (AbstractPlayer)new Spike(this.name);
    }
    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[10];
    }

    @Override
    public Color getSlashAttackColor() {
        return BLUE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {

        return null;
    }
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }

}
