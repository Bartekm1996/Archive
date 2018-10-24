-----------------------------------------------------------------------------------------
--
-- menu.lua
--
-----------------------------------------------------------------------------------------

local composer = require( "composer" )
local scene = composer.newScene()
print("poop")
-- include Corona's "widget" library
local widget = require "widget"

--------------------------------------------

-- forward declarations and other locals
local playBtn

-- 'onRelease' event listener for playBtn
local function onPlayBtnRelease()
	
	composer.gotoScene( "level2", "fade", 500 )
	
	return true
end
local function unlock()
local path = system.pathForFile( "Data.txt", system.DocumentsDirectory )
local fh, reason = io.open( path, "w" )
if fh==nil then
	fh:write(3)
	else
	fh:write(3)
end
	io.close()
end
local function onQuitBtnRelease()
	
	-- go to level1.lua scene
	native.requestExit()
end
local function onMenuBtnRelease()
	
	-- go to level1.lua scene
	composer.gotoScene( "menu", "fade", 500 )
	
	return true	-- indicates successful touch
end

function scene:create( event )
	local sceneGroup = self.view

	-- Called when the scene's view does not exist.
	-- 
	-- INSERT code here to initialize the scene
	-- e.g. add display objects to 'sceneGroup', add touch listeners, etc.

	-- display a background image
local background = display.newImageRect("Restartw.png", display.contentWidth, display.actualContentHeight)
    background.x = display.contentCenterX
    background.y = display.contentCenterY
	
	-- create/position logo/title image on upper-half of the screen
	
	-- create a widget button (which will loads level1.lua on release)
	playBtn = widget.newButton{
		defaultFile = "play.png",
		overFile = "play2.png",
		width=154, height=40,
		onRelease = onPlayBtnRelease	-- event listener function
	}
	playBtn.x = display.contentWidth*0.5
	playBtn.y = display.contentHeight - 200
	MenuBtn= widget.newButton{
		defaultFile = "MenuB.png",
		overFile = "MenuB2.png",
		width=154, height=40,
		onRelease = onMenuBtnRelease	-- event listener function
	}
	MenuBtn.x = display.contentWidth*0.5
	MenuBtn.y = display.contentHeight - 155
	quitBtn= widget.newButton{
		defaultFile = "quitb.png",
		overFile = "quitb2.png",
		width=154, height=40,
		onRelease = onQuitBtnRelease	-- event listener function
	}
	quitBtn.x = display.contentWidth*0.5
	quitBtn.y = display.contentHeight - 110
	
	-- all display objects must be inserted into group
	sceneGroup:insert( background )
	sceneGroup:insert( playBtn )
	sceneGroup:insert( MenuBtn )
	sceneGroup:insert( quitBtn)
end

function scene:show( event )
	local sceneGroup = self.view
	local phase = event.phase
	
	if phase == "will" then
		-- Called when the scene is still off screen and is about to move on screen
		
	elseif phase == "did" then
		-- Called when the scene is now on screen
		unlock()
		-- INSERT code here to make the scene come alive
		-- e.g. start timers, begin animation, play audio, etc.
	end	
end

function scene:hide( event )
	local sceneGroup = self.view
	local phase = event.phase
	if event.phase == "will" then
		-- Called when the scene is on screen and is about to move off screen
		--
		-- INSERT code here to pause the scene
		-- e.g. stop timers, stop animation, unload sounds, etc.)
	elseif phase == "did" then
		-- Called when the scene is now off screen
	end	
end

function scene:destroy( event )
	local sceneGroup = self.view
	
	-- Called prior to the removal of scene's "view" (sceneGroup)
	-- 
	-- INSERT code here to cleanup the scene
	-- e.g. remove display objects, remove touch listeners, save state, etc.
	
	if playBtn then
		playBtn:removeSelf()	-- widgets must be manually removed
		playBtn = nil
	end
end

---------------------------------------------------------------------------------

-- Listener setup
scene:addEventListener( "create", scene )
scene:addEventListener( "show", scene )
scene:addEventListener( "hide", scene )
scene:addEventListener( "destroy", scene )

-----------------------------------------------------------------------------------------

return scene