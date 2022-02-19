#The Deeper Dark Mod
###Description
The Deep Dark Mod is a 1.19 mod that adds a completely new dimension with new structures, items and blocks! The mod is currently still in development so not much has been added just yet

###How to run
The mod is currently based on the 1.19 Deep Dark Expiremental Snapshot 1 which can be downloaded from the [Official Minecraft Website](https://www.minecraft.net/en-us/article/a-very-scary-snapshot "Official Minecraft Website"). The mod also uses the 0.13.2 version of Fabric Loader and the 0.46.4+1.18 version of the Fabric API. For Fabric API to work you need to create a file named "fabric_loader_dependencies.json" in the config folder and in it write the following
```json
{
  "version": 1,
  "overrides": {
    "fabric": {
      "depends": {
        "minecraft": "1.19-Deep.Dark.Experimental.Snapshot.1"
      }
    }
  }
}
```
