# Plywood

**Plywood** is a comprehensive implementation of the Bukkit, Spigot, Paper, Purpur, and Folia plugin APIs for Fabric. It is a fork of [Cardboard](https://github.com/CardboardPowered/cardboard), extended to provide full coverage of all major Bukkit-derived plugin APIs.

Plywood lets you run plugins made for Bukkit, Spigot, Paper, Purpur, and Folia on a Fabric modded server with complete API compatibility.

## Features

- **Bukkit API** – Full implementation of the core Bukkit plugin API  
- **Spigot API** – Full implementation of all Spigot extensions (`org.spigotmc`, nested `Spigot` classes)  
- **Paper API** – Full Paper API support (`io.papermc.paper`, `com.destroystokyo.paper`)  
- **Purpur API** – Full Purpur-specific features (`org.purpurmc.purpur` - events, ridables, language, permissions)  
- **Folia Plugin API** – Full Folia scheduler and region-threaded plugin API support (`io.papermc.paper.threadedregions.*`)  
- **Plugin Loading** – Load and run `.jar` plugins built for any of the above APIs seamlessly  
- **NMS Remapping** – Automatic remapping of `net.minecraft.server` classes for plugin compatibility  

All of the Bukkit, Spigot, Paper, Purpur, and Folia plugin APIs are fully implemented 1:1.

## Supported Versions

| Support | Minecraft       | Status    |
|---------|----------------|-----------|
| Yes     | Fabric 1.21.11 | Active    |

## License

We inherit the license from Paper. See [Paper's License](https://github.com/PaperMC/Paper/blob/master/LICENSE.md) for full details.

## Credits

* **Anarxyyyy** - Plywood author and maintainer  
* [CardboardPowered](https://github.com/CardboardPowered/) - Original Cardboard mod and contributors  
* [BukkitTeam](https://bukkit.org/), [Spigot](https://spigotmc.org/), [Paper](https://papermc.io/), [Purpur](https://purpurmc.org/), and [Folia](https://github.com/PaperMC/Folia) for their work on the APIs  
* [Glowstone](https://glowstone.net) for the library loader  
* [md_5's SpecialSource](https://github.com/md-5/SpecialSource), [SrgLib](https://github.com/OrionMinecraft/SrgLib)
