-----------------------------------------------------------------------------------------
--
-- level1.lua
--
-----------------------------------------------------------------------------------------

local composer = require( "composer" )
local scene = composer.newScene()
local physics = require "physics"


-- include Corona's "physics" library


--------------------------------------------

-- forward declarations and other locals
local screenW, screenH, halfW = display.contentWidth, display.contentHeight, display.contentWidth*0.5

function scene:create( event )

	-- Called when the scene's view does not exist.
	-- 
	-- INSERT code here to initialize the scene
	-- e.g. add display objects to 'sceneGroup', add touch listeners, etc.

	local sceneGroup = self.view

	local sheetOptions =
{
    width = 107,
    height = 107,
    numFrames = 8
}
local sheetOptions1 =
{
    width = 107,
    height = 107,
    numFrames = 10
}
local sheet_Mouse = graphics.newImageSheet( "RatSP.png", sheetOptions )
local sheet_Snake = graphics.newImageSheet( "SnakeSP2.png", sheetOptions1 )

local sequences_Mouse = {
    -- consecutive frames sequence
    {
        name = "normalRun",
        start = 1,
        count = 8,
        time = 400,
        loopCount = 0,
        loopDirection = "forward"
    }
}
local sequences_Snake = {
    -- consecutive frames sequence
    {
        name = "normalRun1",
        start = 1,
        count = 10,
        time = 1000,
        loopCount = 1,
        loopDirection = "forward"
    }
}
local Wall = display.newRect(500, 50, 0, 0)
local scoreText = display.newText( "0000", display.contentCenterX, -10, native.systemFontBold, 30 )
local endScore = 1000000


local function lerp( v0, v1, t )
    return v0 + t * (v1 - v0)
end
 
local function showScore( target, value, duration, fps )
    if value == 0 then
        return
    end
    score = 0
    local passes = duration * 1.5
    local increment = lerp( 0, value, 1/passes )
 
    local count = 0
    local function updateText()
        if count < passes then
            score = score + increment
            target.text = string.format( "%07d", score )
            count = count + 1
        else
            target.text = string.format( "%07d", value )
            Runtime:removeEventListener( "enterFrame", updateText )
         end
    end

    Runtime:addEventListener( "enterFrame", updateText )
end
local duration = 1000000
local fps = 30
showScore( scoreText, endScore, duration, fps )
local function save()
local path = system.pathForFile( "HS.txt", system.DocumentsDirectory )
local fh= io.open( path, "w" )
local temp2=score
		fh:write(temp2)
		print(temp2)
io.close( fh )
end


local animation = display.newSprite(sheet_Mouse,sequences_Mouse)
animation.x= display.contentWidth/2
animation.y= display.contentHeight/2
animation:play()

local animation1 = display.newSprite(sheet_Snake,sequences_Snake)
animation1.x= display.contentWidth/2 -70
animation1.y= display.contentHeight
animation1:play()

local animation2 = display.newSprite(sheet_Snake,sequences_Snake)
animation2.x= display.contentWidth/2 -70
animation2.y= display.contentHeight - 50
animation2:play()

local animation3 = display.newSprite(sheet_Snake,sequences_Snake)
animation3.x= display.contentWidth/2 -70
animation3.y= display.contentHeight - 100
animation3:play()

local obstacle = display.newImage("Obstacle.png")
obstacle.x= -40 + display.contentWidth/2
local obstacle1 = display.newImage("Obstacle.png")
obstacle1.x= -40 + display.contentWidth/2
local obstacle2 = display.newImage("Obstacle.png")
obstacle2.x= -40 + display.contentWidth/2

local blueCar = display.newImage("blueCar.png")
blueCar.x= 40 + display.contentWidth/2
local blueCar1 = display.newImage("blueCar.png")
blueCar1.x= 40 + display.contentWidth/2
local blueCar2 = display.newImage("blueCar.png")
blueCar2.x= 40 + display.contentWidth/2


local Cactus=display.newImageRect("C.png",90, 110)
Cactus.x=display.contentWidth/2 -140
Cactus.y=display.contentHeight/2
local Cactus2=display.newImageRect("C.png",90, 110)
Cactus2.x=display.contentWidth/2 -140
Cactus2.y=display.contentHeight
local Cactus3=display.newImageRect("C.png",90, 110)
Cactus3.x=display.contentWidth/2 +140
Cactus3.y=display.contentHeight/2-70
local bg1  
local bg2  
local runtime = 0  
local scrollSpeed = 1
local scrollSpeed2= 6
local function addScrollableBg()  
    local bgImage = { type="image", filename="road.png" }
    -- Add First bg image
    bg1 = display.newRect(0, 0, display.contentWidth, display.actualContentHeight)
    bg1.fill = bgImage
    bg1.x = display.contentCenterX
    bg1.y = display.contentCenterY
    -- Add Second bg image
    bg2 = display.newRect(0, 0, display.contentWidth, display.actualContentHeight)
    bg2.fill = bgImage
    bg2.x = display.contentCenterX
    bg2.y = display.contentCenterY - display.actualContentHeight
end
local function moveBg(dt)
		Cactus3.y = Cactus3.y + scrollSpeed * dt
		Cactus2.y = Cactus2.y + scrollSpeed * dt
		Cactus.y = Cactus.y + scrollSpeed * dt
		obstacle.y = obstacle.y + scrollSpeed2 * dt
		obstacle1.y = obstacle1.y + scrollSpeed2 * dt 
		obstacle2.y = obstacle2.y + scrollSpeed2 * dt 
		blueCar.y = blueCar.y + scrollSpeed2 * dt
		blueCar1.y = blueCar1.y + scrollSpeed2 * dt 
		blueCar2.y = blueCar2.y + scrollSpeed2 * dt   
		animation1.y = animation1.y + scrollSpeed * dt 
		animation2.y = animation2.y + scrollSpeed * dt 
		animation3.y = animation3.y + scrollSpeed * dt 
	bg1.y = bg1.y + scrollSpeed * dt
    bg2.y = bg2.y + scrollSpeed * dt
    if (bg1.y - display.contentHeight/2) > display.actualContentHeight then
        bg1:translate(0, -bg1.contentHeight * 2)
    end
    if (bg2.y - display.contentHeight/2) > display.actualContentHeight then
        bg2:translate(0, -bg2.contentHeight * 2)
    end
end

local function getDeltaTime()  
   local temp = system.getTimer()
   local dt = (temp-runtime) / (1000/60)
   runtime = temp
   return dt
end
local function enterFrame()  
    local dt = getDeltaTime()
    moveBg(dt)
end
function init()
    addScrollableBg()
    Runtime:addEventListener("enterFrame", enterFrame)
end
init()  

animation:addEventListener( "touch", animation)
local SnakeChaser = display.newImage("SnakeChaser.PNG")
SnakeChaser.x = 150 SnakeChaser.y = 500
local dead = audio.loadSound("dead.mp3")
	sceneGroup:insert( bg1)
	sceneGroup:insert( bg2)
	sceneGroup:insert( animation)
	sceneGroup:insert( animation2)
	sceneGroup:insert( animation3)
	sceneGroup:insert( animation1)
	sceneGroup:insert( SnakeChaser)
	sceneGroup:insert( scoreText)
	sceneGroup:insert(obstacle)
	sceneGroup:insert(obstacle1)
	sceneGroup:insert(obstacle2)
	sceneGroup:insert(blueCar)
	sceneGroup:insert(blueCar1)
	sceneGroup:insert(blueCar2)
	sceneGroup:insert(Cactus)
	sceneGroup:insert(Cactus2)
	sceneGroup:insert(Cactus3)
	
function scene:show( event )
	physics.start()
	physics.setGravity( 0, 0 )
	physics.addBody(animation, "dynamic", {bounce = 0, radius=4, friction=4})
	physics.addBody(animation1, "static", {bounce = 0, radius=29, friction = 5000})
	physics.addBody(animation2, "static", {bounce = 0, radius=29, friction = 5000})
	physics.addBody(animation3, "static", {bounce = 0, radius=29, friction = 5000})
	physics.addBody(obstacle, "static", {bounce = 0, radius = 38, friction = -12})
	physics.addBody(obstacle1, "static", {bounce = 0, radius = 38, friction = -12})
	physics.addBody(obstacle2, "static", {bounce = 0, radius = 38, friction = -12})
	physics.addBody(blueCar, "static", {bounce = 0, radius = 38, friction = -12})
	physics.addBody(blueCar2, "static", {bounce = 0, radius = 38, friction = -12})
	physics.addBody(blueCar1, "static", {bounce = 0, radius = 38, friction = -12})
	physics.addBody(Cactus, "static", {bounce = 0, radius = 38, friction = -12})
	physics.addBody(Cactus2, "static", {bounce = 0, radius = 38, friction = -12})
	physics.addBody(Cactus3, "static", {bounce = 0, radius = 38, friction = -12})
	score=0
	local death = 0
	local set = -70
	local spawnMult = 0
	local spawnGen
	local countDownTimer = 50
	SnakeChaser.x = 150 SnakeChaser.y = 500
	animation.x = 150 animation.y = 250	
	animation3.x= display.contentWidth/2 -70
	animation2.y= display.contentHeight + 50
	animation3.y= display.contentHeight + 100
	local sceneGroup = self.view
	local phase = event.phase
	
	if phase == "will" then
		-- Called when the scene is still off screen and is about to move on screen
	elseif phase == "did" then
	composer.removeScene("Restart3");
	score = 0
	scrollSpeed2=13
	scrollSpeed = 6
	local function spawner( event )
		countDownTimer = -1 + countDownTimer
		if countDownTimer == 1 then
			spawnGen = math.random(0,3)
			if spawnMult == 0 then
				if spawnGen == 0 then
						obstacle.x= -40 + display.contentWidth/2
						blueCar.x= 40 + display.contentWidth/2
						blueCar.y = set
						obstacle.y = set
						Cactus.x = display.contentWidth - 30
						Cactus.y = set
				elseif spawnGen == 1 then
						obstacle.x= -40 + display.contentWidth/2
						obstacle.y = set
						animation1.y = set
						animation1.x= -40 + display.contentWidth/2 -70
						Cactus.x = display.contentWidth - 30
						Cactus.y = set
				elseif spawnGen == 2 then
						blueCar.x= 40 + display.contentWidth/2
						blueCar.y = set
						animation1.y = set
						animation1.x= -40 + display.contentWidth/2 -70
						Cactus.x = display.contentWidth - 30
						Cactus.y = set
				elseif spawnGen == 3 then
						obstacle.x= -40 + display.contentWidth/2
						blueCar.x= 40 + display.contentWidth/2
						blueCar.y = set
						obstacle.y = set
						animation1.y = set
						animation1.x= -40 + display.contentWidth/2 -70
				end
				spawnMult = 1
			elseif spawnMult == 1 then
				if spawnGen == 0 then
						obstacle1.x= -40 + display.contentWidth/2
						blueCar1.x= 40 + display.contentWidth/2
						blueCar1.y = set
						animation2.x= -40 + display.contentWidth/2 -70
						obstacle1.y = set
						animation2.y = set
						Cactus3.x = display.contentWidth - 30
						Cactus3.y = set
				elseif spawnGen == 1 then
						obstacle1.x= -40 + display.contentWidth/2
						obstacle1.y = set
						animation2.y = set
						animation2.x= -40 + display.contentWidth/2 -70
						Cactus3.x = display.contentWidth - 30
						Cactus3.y = set
				elseif spawnGen == 2 then
						blueCar1.x= 40 + display.contentWidth/2
						blueCar1.y = set
						animation2.y = set
						animation2.x= -40 + display.contentWidth/2 -70
						Cactus3.x = display.contentWidth - 30
						Cactus3.y = set
				elseif spawnGen == 3 then
						obstacle1.x= -40 + display.contentWidth/2
						blueCar1.x= 40 + display.contentWidth/2
						blueCar1.y = set
						obstacle1.y = set
						animation2.y = set
						animation2.x= -40 + display.contentWidth/2 -70
				
				end
				spawnMult = 2
			elseif spawnMult == 2 then
				if spawnGen == 0 then
						obstacle2.x= -40 + display.contentWidth/2
						blueCar2.x= 40 + display.contentWidth/2
						blueCar2.y = set
						animation3.x= -40 + display.contentWidth/2 -70
						obstacle2.y = set
						animation3.y = set
						Cactus2.x = display.contentWidth - 30
						Cactus2.y = set
				elseif spawnGen == 1 then
						obstacle2.x= -40 + display.contentWidth/2
						obstacle2.y = set
						animation3.y = set
						animation3.x= -40 + display.contentWidth/2 -70
						Cactus2.x = display.contentWidth - 30
						Cactus2.y = set
				elseif spawnGen == 2 then
						blueCar2.x= 40 + display.contentWidth/2
						blueCar2.y = set
						animation3.y = set
						animation3.x= -40 + display.contentWidth/2 -70
						Cactus2.x = display.contentWidth - 30
						Cactus2.y = set
				elseif spawnGen == 3 then
						obstacle2.x= -40 + display.contentWidth/2
						blueCar2.x= 40 + display.contentWidth/2
						blueCar2.y = set
						obstacle2.y = set
						animation3.y = set
						animation3.x= -40 + display.contentWidth/2 -70
				
				end
				spawnMult = 0
			end
			countDownTimer = 25
		end
	end
	Runtime:addEventListener("enterFrame", spawner)
	
	local function itemReset(event)
		if death == 1 then
			physics.stop()
			audio.play(dead)
			countDownTimer = 0
			scrollSpeed2 = 100
			scrollSpeed = 100
			animation1.y = 520
			animation2.y = 580
			animation3.y = 660
			obstacle.y = 0
			obstacle1.y = 0
			obstacle2.y = 0
			Cactus.y = 0
			Cactus2.y = 0
			Cactus3.y = 0
			blueCar.y = 0
			blueCar1.y = 0
			blueCar2.y = 0
			spawnMult = 0
			death = 0
			save() 
						composer.gotoScene("Restart3", "fade", 500)
		end
	end
	Runtime:addEventListener("enterFrame", itemReset)
	local function onLocalCollision( event)
			death = 1
		end
		animation:addEventListener("collision", onLocalCollision)
	local function snakeMove( event )	
			if self.y < (animation1.y + 10)then
				if self.y > (animation1.y - 10) then
					animation1:play()
				end
			elseif self.y < (animation2.y + 10)then
				if self.y > (animation2.y - 10) then
					animation2:play()
				end
			elseif self.y < (animation3.y + 10)then
				if self.y > (animation3.y - 10) then
					animation3:play()
				end
			end
		end
		
		function animation:touch(event)
			if event.phase == "began" then
			
				self.markX = self.x
				self.markY = self.y
			
			elseif event.phase == "moved" then
				
				local x = (event.x - event.xStart) + self.markX
				local y = (event.y - event.yStart) + self.markY
				
				
				if animation.y >= 3  or event.y > self.markY then
					if animation.y  > 450 then
					if score>5000 then 
						composer.gotoScene("Restart2", "fade", 500)
						else
						composer.gotoScene("Restart12", "fade", 500)
						end
						save()
						
					else
						self.y = y; --Block theobject at the top
				end
				end
				if animation.x > 20 and animation.x < 290 then
					self.x = x;
					SnakeChaser.x = x
				elseif animation.x <= 20 then
					if event.x > self.markX then
						self.x = x;
						SnakeChaser.x = x
					end
				elseif animation.x >= 290 then 
					if event.x < self.markX then
						self.x = x;
						SnakeChaser.x = x
					end
				end
			end
	return true
	end
	local function snakeMove( event )	
			if animation.y <  (80 + animation1.y)then
				if animation.y > (-80 + animation1.y ) then
					animation1:play()
				end
			end
			if animation.y <  (80 + animation2.y)then
				if animation.y > (-80 + animation2.y ) then
					animation2:play()
				end
			end
			if animation.y <  (80 + animation3.y)then
				if animation.y > (-80 + animation3.y ) then
					animation3:play()
				end
			end
		end
Runtime:addEventListener("enterFrame", snakeMove)
local function health( event )
			if animation.y  > 450 then
			animation.x = 150 animation.y = 250
				death = 1
			end
end
		Runtime:addEventListener("enterFrame", health)
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
end



function scene:destroy( event )

	-- Called prior to the removal of scene's "view" (sceneGroup)
	-- 
	-- INSERT code here to cleanup the scene
	-- e.g. remove display objects, remove touch listeners, save state, etc.
	local sceneGroup = self.view
	
	
	package.loaded[physics] = nil
	physics = nil

end

---------------------------------------------------------------------------------

-- Listener setup
scene:addEventListener( "create", scene )
scene:addEventListener( "show", scene )
scene:addEventListener( "hide", scene )
scene:addEventListener( "destroy", scene )

-----------------------------------------------------------------------------------------

return scene