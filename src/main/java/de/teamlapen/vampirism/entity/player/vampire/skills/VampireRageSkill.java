package de.teamlapen.vampirism.entity.player.vampire.skills;

import de.teamlapen.vampirism.api.entity.player.vampire.DefaultSkill;
import de.teamlapen.vampirism.api.entity.player.vampire.ILastingVampireSkill;
import de.teamlapen.vampirism.api.entity.player.vampire.IVampirePlayer;
import de.teamlapen.vampirism.config.Balance;
import de.teamlapen.vampirism.entity.player.vampire.SkillHandler;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class VampireRageSkill extends DefaultSkill implements ILastingVampireSkill {

    public VampireRageSkill() {
        super(null);
    }

    @Override
    public boolean canBeUsedBy(IVampirePlayer vampire) {
        return !vampire.getSkillHandler().isSkillActive(SkillHandler.batSkill);
    }

    @Override
    public int getCooldown() {
        return Balance.vps.RAGE_COOLDOWN * 20;
    }

    @Override
    public int getDuration(int level) {
        return 20 * (Balance.vps.RAGE_MIN_DURATION + Balance.vps.RAGE_DUR_PL * (level - getMinLevel()));
    }

    @Override
    public int getMinLevel() {
        return Balance.vps.RAGE_MIN_LEVEL;
    }

    @Override
    public int getMinU() {
        return 32;
    }

    @Override
    public int getMinV() {
        return 0;
    }

    @Override
    public String getUnlocalizedName() {
        return "skill.vampirism.vampire_rage";
    }

    @Override
    public boolean onActivated(IVampirePlayer vampire) {
        vampire.getRepresentingPlayer().addPotionEffect(new PotionEffect(Potion.moveSpeed.id, getDuration(vampire.getLevel()), 2));
        return true;
    }

    @Override
    public void onActivatedClient(IVampirePlayer vampire) {

    }

    @Override
    public void onDeactivated(IVampirePlayer vampire) {
        vampire.getRepresentingPlayer().removePotionEffect(Potion.moveSpeed.id);
    }

    @Override
    public void onReActivated(IVampirePlayer vampire) {

    }

    @Override
    public boolean onUpdate(IVampirePlayer vampire) {
        return false;
    }
}