package de.teamlapen.vampirism.player.vampire.actions;

import de.teamlapen.vampirism.api.entity.player.actions.ILastingAction;
import de.teamlapen.vampirism.api.entity.player.vampire.DefaultVampireAction;
import de.teamlapen.vampirism.api.entity.player.vampire.IVampirePlayer;
import de.teamlapen.vampirism.config.VampirismConfig;
import de.teamlapen.vampirism.core.ModEffects;
import net.minecraft.potion.EffectInstance;

/**
 * Adds sunscreen
 */
public class SunscreenVampireAction extends DefaultVampireAction implements ILastingAction<IVampirePlayer> {

    public SunscreenVampireAction() {
        super();
    }

    @Override
    public boolean activate(IVampirePlayer vampire) {
        vampire.getRepresentingPlayer().addPotionEffect(new EffectInstance(ModEffects.sunscreen, getDuration(vampire.getLevel()), 3, false, false));
        return true;
    }

    @Override
    public int getCooldown() {
        return VampirismConfig.BALANCE.vaSunscreenCooldown.get() * 20;
    }

    @Override
    public int getDuration(int level) {
        return 20 * (VampirismConfig.BALANCE.vaSunscreenDuration.get());
    }

    @Override
    public boolean isEnabled() {
        return VampirismConfig.BALANCE.vaSunscreenEnabled.get();
    }

    @Override
    public void onActivatedClient(IVampirePlayer vampire) {

    }

    @Override
    public void onDeactivated(IVampirePlayer vampire) {
        vampire.getRepresentingPlayer().removePotionEffect(ModEffects.sunscreen);
    }

    @Override
    public void onReActivated(IVampirePlayer vampire) {

    }

    @Override
    public boolean onUpdate(IVampirePlayer vampire) {
        return false;
    }
}
