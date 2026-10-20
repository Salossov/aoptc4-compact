package thaumcraft.viewers.tooltip;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.research.ResearchApi;

/** Read-only public contracts; no core implementation classes or chunk requests. */
public final class CoreTooltip {
    public static final String KEY = "thaumcraft_viewers:essentia";

    public static boolean supports(BlockEntity tile) {
        return tile != null && tile.getBlockState().getBlock().builtInRegistryHolder().key().location().getNamespace().equals("thaumcraft")
                && (tile instanceof IAspectContainer || tile instanceof IEssentiaTransport);
    }

    public static void write(CompoundTag data, BlockEntity tile, Direction face) {
        data.remove(KEY);
        if (!supports(tile)) return;
        var values = new CompoundTag();
        if (tile instanceof IAspectContainer container) {
            var contents = container.getAspects();
            if (contents != null) contents.asMap().forEach((aspect, amount) -> {
                if (amount > 0) values.putInt(aspect.getTag(), amount);
            });
        } else if (tile instanceof IEssentiaTransport transport) {
            var aspect = transport.getEssentiaType(face);
            int amount = transport.getEssentiaAmount(face);
            if (aspect != null && amount > 0) values.putInt(aspect.getTag(), amount);
        }
        // Even an empty authoritative snapshot overrides outdated local contents.
        data.put(KEY, values);
    }

    public static List<Component> readOrLocal(CompoundTag data, BlockEntity tile, Direction face) {
        if (!data.contains(KEY, Tag.TAG_COMPOUND)) {
            data = new CompoundTag();
            write(data, tile, face);
        }
        var result = new ArrayList<Component>();
        var values = data.getCompound(KEY);
        for (String key : values.getAllKeys().stream().sorted().toList()) {
            Aspect aspect = Aspect.getAspect(key);
            int amount = values.getInt(key);
            if (aspect == null || amount <= 0) continue;
            Component name = !ResearchApi.clientKnowledge().knows(aspect)
                    ? Component.translatable("thaumcraft_viewers.unknown_aspect")
                    : aspect.translationKey() == null ? Component.literal(aspect.getName()) : Component.translatable(aspect.translationKey());
            result.add(Component.translatable("thaumcraft_viewers.essentia_contents", name, amount));
        }
        return List.copyOf(result);
    }

    private CoreTooltip() {}
}
