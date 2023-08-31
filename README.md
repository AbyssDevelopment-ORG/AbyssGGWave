# 🐳 AbyssGGWave 🐳
Free &amp; open source ggwave plugin.

# Download
https://discord.abyssdev.org

# Dependencies
- AbyssLib (found in Discord)

# Configuration Details

List of all possible gg replacements.
```
gg-messages:
    - '&a&lGG'
    - '&b&lGG'
    - '&c&lGG'
```

Commands to send to the player who donated.
```
commands:
    - "eco give %player% 1000"
```

Commands to send to players who respond with gg.
```
chat-activity-commands:
    - "eco give %player% 100"
```

Messages.
```
messages:
    "donate":
        sound:
            enabled: true
            value: "ORB_PICKUP"
            volume: 1
            pitch: 1
        message:
            enabled: true
            value:
                - "&3&m--------------------------------"
                - " "
                - "&b&l   GGWave Event Activated"
                - " "
                - "&7&o  Congratulate &b&o%player%"
                - "&7&o for purchasing &b&o%package%"
                - " "
                - "&3&m--------------------------------"
    "help":
        sound:
            enabled: true
            value: "ORB_PICKUP"
            volume: 1
            pitch: 1
        message:
            enabled: true
            value:
                - "&3&m--------------------------------"
                - " "
                - "&b&lAbyssDev &8&l┃ &bGGWave Help"
                - "&7&oMade by &f&oRelocation#0001&7&o."
                - " "
                - "&3&lCOMMANDS:"
                - "&3&l» &b/ggwave start <player> <duration> <package>"
                - " "
                - "&3&m--------------------------------"
    "no-permission":
        sound:
            enabled: true
            value: "ORB_PICKUP"
            volume: 1
            pitch: 1
        message:
            enabled: true
            value:
                - "&cNo permission"
```
