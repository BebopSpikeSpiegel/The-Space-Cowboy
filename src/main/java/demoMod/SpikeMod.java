package demoMod;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import characters.Spike;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import pathes.AbstractCardEnum;
import pathes.ClassEnum;
import relics.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SpireInitializer
public class SpikeMod implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber {
    private static final String MOD_BADGE = "img/UI_Spike/badge.png";
    //攻击、技能、能力牌的图片(512)
    private static final String ATTACK_CC = "img/512/bg_attack_SPIKE_s.png";
    private static final String SKILL_CC = "img/512/bg_skill_SPIKE_s.png";
    private static final String POWER_CC = "img/512/bg_power_SPIKE_s.png";
    private static final String ENERGY_ORB_CC = "img/512/SPIKEOrb.png";
    //攻击、技能、能力牌的图片(1024)
    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_SPIKE.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_SPIKE.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_SPIKE.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/SPIKEOrb.png";
    public static final String CARD_ENERGY_ORB = "img/UI_Spike/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/SpikeButton.png";
    private static final String SPIKE_PORTRAIT = "img/charSelect/Spike_19-10art.png";
    public static final Color BLUE = CardHelper.getColor(0,39,127);

    public SpikeMod() {
        //构造方法，初始化各种参数
        BaseMod.subscribe((ISubscriber)this);
        BaseMod.addColor(AbstractCardEnum.Spike_COLOR, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, BLUE, ATTACK_CC, SKILL_CC, POWER_CC, ENERGY_ORB_CC, ATTACK_CC_PORTRAIT, SKILL_CC_PORTRAIT,POWER_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
    }

    @Override
    public void receiveEditCharacters() {
        //添加角色到MOD中
        BaseMod.addCharacter((AbstractPlayer)new Spike("Spike"), MY_CHARACTER_BUTTON, SPIKE_PORTRAIT, ClassEnum.Spike_CLASS);
    }
    //初始化整个MOD,一定不能删
    public static void initialize() {
        new SpikeMod();
    }

    @Override
    public void receiveEditCards() {
        //将卡牌批量添加
        for (AbstractCard card : loadCardsToAdd()) {
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }
    }

    @Override
    public void receivePostExhaust(AbstractCard c) {}

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner) {

    }


    @Override
    public void receivePowersModified() {}


    @Override
    public void receivePostDungeonInitialize() {}


    @Override
    public void receivePostDraw(AbstractCard arg0) {}

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }


    @Override
    public void receiveEditKeywords() {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            BaseMod.addKeyword("势", new String[]{"势"},
                    "你的连招势能。部分卡牌会获得或消耗势。");
        } else {
            BaseMod.addKeyword("Flow", new String[]{"flow", "Flow"},
                    "Your combat momentum. Some cards gain or spend Flow.");
        }
    }

    @Override
    public void receiveEditStrings() {
        boolean zh = Settings.language == Settings.GameLanguage.ZHS;
        String lang = zh ? "zh" : "en";

        BaseMod.loadCustomStrings(RelicStrings.class, loadJson("localization/Spike_relics-" + lang + ".json"));
        BaseMod.loadCustomStrings(CardStrings.class,  loadJson("localization/Spike_cards-"  + lang + ".json"));
        BaseMod.loadCustomStrings(PowerStrings.class, loadJson("localization/Spike_powers-" + lang + ".json"));
    }

    private ArrayList<AbstractCard> loadCardsToAdd() {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        // Basics
        cards.add(new StraightLead_Spike());
        cards.add(new Dodge_Spike());
        cards.add(new JeetKuneDo_Spike());
        cards.add(new Shoot_Spike());
        // Commons
        cards.add(new DontBotherNone_Spike());
        cards.add(new BadDogNoBiscuits_Spike());
        cards.add(new SpokeyDokey_Spike());
        cards.add(new FeltTipPen_Spike());
        cards.add(new Rain_Spike());
        cards.add(new Rush_Spike());
        cards.add(new Memory_Spike());
        cards.add(new FarewellBlues_Spike());
        cards.add(new TwentyFourHoursOpen_Spike());
        cards.add(new FantaisieSign_Spike());
        // Uncommons
        cards.add(new HeavyMetalQueen_Spike());
        cards.add(new WaltzForVenus_Spike());
        cards.add(new HonkyTonkWomen_Spike());
        cards.add(new SympathyForTheDevil_Spike());
        cards.add(new CowboyFunk_Spike());
        cards.add(new SpeakLikeAChild_Spike());
        cards.add(new MushroomHunting_Spike());
        cards.add(new GreenBird_Spike());
        cards.add(new JupiterJazz_Spike());
        cards.add(new AsteroidBlues_Spike());
        cards.add(new MyFunnyValentine_Spike());
        cards.add(new BohemianRhapsody_Spike());
        cards.add(new WordsWeCouldntSay_Spike());
        cards.add(new GoodnightJulia_Spike());
        cards.add(new CallMeCallMe_Spike());
        // Rares
        cards.add(new Bang_Spike());
        cards.add(new BalladOfFallenAngels_Spike());
        cards.add(new Tank_Spike());
        cards.add(new TheRealFolkBlues_Spike());
        cards.add(new SpaceLion_Spike());
        cards.add(new JammingWithEdward_Spike());
        cards.add(new TheEggAndYou_Spike());
        cards.add(new RoadToTheWest_Spike());
        cards.add(new AveMaria_Spike());
        cards.add(new Blue_Spike());
        // Knockin' on Heaven's Door (movie)
        cards.add(new GottaKnockALittleHarder_Spike());
        cards.add(new AskDNA_Spike());
        return cards;
    }
    @Override
    public void receiveEditRelics() {
        //将自定义的遗物添加到这里
        BaseMod.addRelicToCustomPool(new Marlboro(), AbstractCardEnum.Spike_COLOR);
        BaseMod.addRelicToCustomPool(new Jericho941(), AbstractCardEnum.Spike_COLOR);
        BaseMod.addRelicToCustomPool(new Ein(), AbstractCardEnum.Spike_COLOR);
        BaseMod.addRelicToCustomPool(new BigShot(), AbstractCardEnum.Spike_COLOR);
        BaseMod.addRelicToCustomPool(new SeeYouSpaceCowboy(), AbstractCardEnum.Spike_COLOR);
        BaseMod.addRelicToCustomPool(new SwordfishII(), AbstractCardEnum.Spike_COLOR);
    }

    @Override
    public void receiveRelicGet(AbstractRelic relic) {}

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostBattle(AbstractRoom r) {

    }

    @Override
    public void receivePostInitialize() {

    }

}