package patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;

/**
 * Safety net: {@code GenericEventDialog.updateBodyText(null)} feeds a null into a Scanner and
 * crashes the whole game on any event whose body text is null. We never want a single event to
 * hard-crash the run, so swap null for empty text — and log which event did it so it can be fixed.
 */
@SpirePatch(clz = GenericEventDialog.class, method = "updateBodyText", paramtypez = { String.class })
public class EventBodyNullGuard {
    public static SpireReturn<Void> Prefix(GenericEventDialog __instance, String text) {
        if (text == null) {
            String who = "unknown";
            try {
                if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().event != null) {
                    who = AbstractDungeon.getCurrRoom().event.getClass().getName();
                }
            } catch (Throwable ignored) {
            }
            System.out.println("[Spike_Spiegel] WARNING: null event body text — Event = " + who);
            __instance.updateBodyText(""); // re-enters this method with "" (non-null) → normal path, no recursion
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
