package de.teamlapen.vampirism.client.render.entities;

import de.teamlapen.vampirism.client.model.BasicHunterModel;
import de.teamlapen.vampirism.client.model.HunterEquipmentModel;
import de.teamlapen.vampirism.client.render.LayerCloak;
import de.teamlapen.vampirism.client.render.LayerHunterEquipment;
import de.teamlapen.vampirism.entity.hunter.BasicHunterEntity;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


/**
 * There are differently looking level 0 hunters.
 * Hunter as of level 1 look all the same, but have different weapons
 */
@OnlyIn(Dist.CLIENT)
public class BasicHunterRenderer extends BipedRenderer<BasicHunterEntity, BasicHunterModel<BasicHunterEntity>> {
    private final ResourceLocation texture = new ResourceLocation(REFERENCE.MODID, "textures/entity/hunter_base1.png");
    private final ResourceLocation[] textures = {
            new ResourceLocation(REFERENCE.MODID, "textures/entity/hunter_base2.png"),
            new ResourceLocation(REFERENCE.MODID, "textures/entity/hunter_base3.png"),
            new ResourceLocation(REFERENCE.MODID, "textures/entity/hunter_base4.png"),
            new ResourceLocation(REFERENCE.MODID, "textures/entity/hunter_base5.png")
    };

    public BasicHunterRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BasicHunterModel<>(), 0.5F);
        this.addLayer(new LayerHunterEquipment<>(this, entity -> (entity.getLevel() < 2 || entity.isCrossbowInMainhand()) ? HunterEquipmentModel.StakeType.ONLY : HunterEquipmentModel.StakeType.FULL, entity -> entity.getLevel() == 0 ? entity.getEntityTextureType() % 4 : -1));
        this.addLayer(new LayerCloak<>(this, texture, entity -> entity.getLevel() > 0));
    }


    @Override
    protected ResourceLocation getEntityTexture(BasicHunterEntity entity) {
        int level = entity.getLevel();
        if (level > 0) return texture;
        return textures[entity.getEntityTextureType() % textures.length];
    }


}
