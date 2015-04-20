package riskyken.cosmeticWings.client.render.wings;

import net.minecraft.entity.player.EntityPlayer;
import riskyken.cosmeticWings.client.model.wings.ModelKuroyukihimeWings;
import riskyken.cosmeticWings.common.wings.WingsData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWingsKuroyukihime implements IWingRenderer {

    public final ModelKuroyukihimeWings kuroyukihimeWings = new ModelKuroyukihimeWings();
    
    @Override
    public void render(EntityPlayer player, WingsData wingData, int layer, float partialRenderTick) {
        kuroyukihimeWings.render(player, layer, wingData);
    }

    @Override
    public void postRender(EntityPlayer player, WingsData wingData, int layer, float partialRenderTick) {
    }

    @Override
    public void onTick(EntityPlayer player, WingsData wingData) {
        kuroyukihimeWings.onTick(player, wingData);
    }
}
