package riskyken.cosmeticWings.common.wings.types;

import riskyken.cosmeticWings.client.render.wings.IWingRenderer;
import riskyken.cosmeticWings.client.render.wings.RenderWingsFlandre;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WingsFlandre extends AbstractWings {
    
    @Override
    public String getName() {
        return "flandre";
    }
    
    @Override
    public String getAuthorName() {
        return null;
    }
    
    @Override
    public int getNumberOfRenderLayers() {
        return 2;
    }
    
    @Override
    public boolean isGlowing(int layer) {
        if (layer == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Class<? extends IWingRenderer> getRendererClass() {
        return RenderWingsFlandre.class;
    }
}
