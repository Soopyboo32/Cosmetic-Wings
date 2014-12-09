package riskyken.cosmeticWings.client.particles;

import java.util.ArrayDeque;
import java.util.Queue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleManager {

	public static ParticleManager INSTANCE;
	public Queue<EntityButterflyFx> butterflyRenderQueue;
	private Minecraft mc;
	
    public static void init() {
        INSTANCE = new ParticleManager();
    }
	
	public ParticleManager() {
	    MinecraftForge.EVENT_BUS.register(this);
	    butterflyRenderQueue = new ArrayDeque();
	    mc = Minecraft.getMinecraft();
    }
	
	public void spawnParticle(World world, EntityFX particle) {
		spawnParticle(world, particle, false);
	}
	
	public void spawnParticle(World world, EntityFX particle, boolean must) {
		if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null) {

			if (!must) {
				int particleSetting = mc.gameSettings.particleSetting;
				
				if (particleSetting == 2 || (particleSetting == 1 && world.rand.nextInt(3) == 0)) {
					return;
				}
				
				double distanceX = mc.renderViewEntity.posX - particle.posX;
				double distanceY = mc.renderViewEntity.posY - particle.posY;
				double distanceZ = mc.renderViewEntity.posZ - particle.posZ;
				int maxDistance = 16;
				
				if (distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ > maxDistance * maxDistance) {
					return;
				}
			}
			
			if (particle != null) {
				mc.effectRenderer.addEffect(particle);
			}
		}
	}
	
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        mc.entityRenderer.enableLightmap(event.partialTicks);
        RenderHelper.disableStandardItemLighting();
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
        Tessellator tessellator = Tessellator.instance;

        for(EntityButterflyFx butterflyFx : butterflyRenderQueue) {
            butterflyFx.postRender(tessellator);
        }
        
        butterflyRenderQueue.clear();
        
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        
        mc.entityRenderer.disableLightmap(event.partialTicks);
    }
}
