package thaumcraft.viewers;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

/** Server installation supplies Jade/WTHIT data; recipe viewers stay client-side. */
@Mod(value = "thaumcraft_viewers", dist = Dist.DEDICATED_SERVER)
public final class Common {}
