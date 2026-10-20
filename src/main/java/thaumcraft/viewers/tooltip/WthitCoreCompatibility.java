package thaumcraft.viewers.tooltip;

import mcp.mobius.waila.api.*;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class WthitCoreCompatibility implements IWailaCommonPlugin {
    @Override public void register(ICommonRegistrar registrar) {
        registrar.blockData((data, accessor, config) -> CoreTooltip.write(data.raw(), accessor.getTarget(), null), BlockEntity.class);
    }
}
