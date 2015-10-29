# ChatExtender


This is a simple plugin that I wrote that listens for certain commands, then displays them to people with the permissions who are currently listening. I made this for the server I play on, and it is meant to be simple and lightweight, but I am open to feature suggestions.

Features:
    Listen to commands and PMs of players that are online, which is good for catching grievers and advertisers early.
    Configurable
    Different permissions (probably don't want all your mods eavesdropping on players).

Commands:
    /celisten -- Toggles whether or not you want to receive listener notifications

Permissions:
    chatextender.listen -- allows the use of /celisten
    chatextender.conversations -- player will receive notifications for player conversations (not recommended for all mods)
    chatextender.commands -- player will receive notifications when a player tries to use watched commands that are listed in the config (like /op, /version, etc.)

For compiled JARs, see the page on SpigotMC: https://www.spigotmc.org/resources/chatextender.13922/
