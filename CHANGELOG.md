# Changelog

## v0.7.1
- Score now correctly updates when a player completes a box
- A player can now repeat their turn after completing a box
- Join requests now get added to a queue, this way the host can receive more than one request at a time (very buggy, but it's a start)

## v0.7.0
- Rough implementation for player turns in game (a lot of room for improvement and optimization)
- Not having enough players in a match now notifies the host and automatically closes the server
- General bugfixes
- (Experimental) Removed unused panel in the game room and moved the players' panel down to the center
- (Experimental) A small border of the complimentary color of the player's around their avatar has been added as an indicator for the player to know who they are amongst everyone else (host still shows up with a yellow border around their avatar, only shown to everyone but the host themselves)

## v0.6.9
- `Sound system` completely rewritten to allow for more dynamic music functions and better resource management
- Code cleanup
- Several bugfixes

## v0.6.8
- Code massively reordered and cleaned up
- Most variables renamed to be more clear and so it's more obvious where they belong to
- Updated FlatLaF from 1.6 to 1.6.3
- UITheme now selectable (attribute uiTheme can make the program use Light Theme or Dark Theme)
- Several tweaks to the UI
- Made Server say 'threads' if there is more than one thread, otherwise it just says 'thread' (VERY IMPORTANT) 
- Ow ouch owie ears hurtie (Tweaked sound volume, anything higher than 0.15 is actually way too much for some reason)

## v0.6.7
- Made Main Menu not resizable
- Renamed jars folder to libs
- Code clean-up
- Constructors clean-up
- Added some more keybinds
- Player setup no longer stays always on top, only on initial setup
- Added functional game
- ACTUALLY fixed server not closing this time
- Initial color is now randomized to make same colors in match less common
- There's only 1 JFrame now that handles everything. "Having multiple JFrame instances in one application is bad usability", so I had to do this. Now the program is almost as fast as the JavaFX version!
- Renamed mostly everything
- Added fucking bookmarks cuz I keep getting lost in the only JFrame class
- Several bug fixes

## v0.6.6
- SoundManager and GraphicsManager are now completely static as they are just utility classes
- DiscordHandler (static too) now handles Discord Rich Presence instead of making every frame/dialog class handle it by themselves
- Server now properly closes and kicks every player after host disconnects (this also fixes a bug where it wasn't possible to host a new server after "disconnecting" from the previous hosted server)
- Minimizing the window pauses the current song, and resumes slowly fading in once the window is brought back up (Inspired by TETR.IO minus the fade-in)
- Lots of cleaning up of code
- Replaced most Thread-related code with Executors (a bit more clean, performance impact whether positive or negative is unknown)
- Tweaks in Discord Rich Presence
- Quality of Life improvements for working with SoundManager
- Several bugfixes
- TinySound was replaced by JavaFX MediaPlayer, it's way faster in comparison but for it to work a single JXPanel had to be created which cannot be removed (it's just exists there, it doesn't even show up on screen)
- Implemented a bit of JavaFX which helps a lot, but brings a little problem with a quick solution. Right Click on Project > Properties > Run > VM Options: Change Path in --module-path so it points to \jars\Sound in the project (path must be absolute)
- Implemented Key Bindings for mouse-less navigation