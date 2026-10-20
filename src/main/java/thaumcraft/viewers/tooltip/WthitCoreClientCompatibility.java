package thaumcraft.viewers.tooltip;

import mcp.mobius.waila.api.*;
import net.minecraft.world.level.block.Block;

public final class WthitCoreClientCompatibility implements IWailaClientPlugin {
    @Override public void register(IClientRegistrar registrar) {
        registrar.body(new IBlockComponentProvider() {
            @Override public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
                if (CoreTooltip.supports(accessor.getBlockEntity()))
                    CoreTooltip.readOrLocal(accessor.getData().raw(), accessor.getBlockEntity(), null).forEach(tooltip::addLine);
            }
        }, Block.class);
    }
}
