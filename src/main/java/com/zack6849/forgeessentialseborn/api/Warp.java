package com.zack6849.forgeessentialseborn.api;


import com.zack6849.forgeessentialseborn.Main;

import java.io.File;
import java.util.List;

/**
 * Created by Rory on 8/1/2016.
 */

public class Warp {

    private static File file = new File(Main.getInstance().getServer().getDataDirectory().getAbsoluteFile() + File.separator + "config" + File.separator + "ForgeEssentialsReborn", "warps.json");

    List[] warplist;


}
