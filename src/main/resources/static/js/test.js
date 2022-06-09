var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var FPS = 120;

var player = {
    x : canvas.width/2-70/2,
    y : canvas.height-180,
    width : 70,
    height : 70,
    draw(){
        ctx.fillStyle = 'green';
        ctx.fillRect(this.x, this.y, this.width, this.height);
    }
}

class Land{
    constructor(){
        this.x = 400;
        this.y = canvas.height-canvas.height;
        this.width = 200;
        this.height = 50;
    }
    draw(){
        ctx.fillStyle = 'red';
        ctx.fillRect(this.x, this.y, this.width, this.height);
    }
}
var country=[];

var Fram = 1000/FPS;
var timer = 0;
var strat = setInterval(draw, Fram);
var score_timer = false


function draw() {
    if(score_timer == false){
        startClock();
        score_timer = true;
    }
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    player.draw();
    change_detect()
    land_control();
    timer++;  
    console.log(land_g);  
}

var change_x = 0, jump_timer = 0; jump_change = false, jump_detect = true;
document.onkeydown = checkKey_down;
function checkKey_down(e) {

    e = e || window.event;
    if(jump_detect == true){
        if (e.keyCode == '32') {
            jump_change = true;
           // up arrow
       }
    }
    if (e.keyCode == '37') {
        change_x = -1;
       // left arrow
    }
    if (e.keyCode == '39') {
        change_x = 1;
       // right arrow
    }

}

document.onkeyup = checkKey_up;
function checkKey_up(e) {

    e = e || window.event;

    if (e.keyCode == '37') {
        change_x = 0;
       // left arrow
    }
    else if (e.keyCode == '39') {
        change_x = 0;
       // right arrow
    }

}

var g = true, g_dect = false; 
var player_g = 2;
function change_detect(){
    
    if(change_x == 1){
        player.x++;
    }
    if(change_x == -1){
        player.x--;
    }
    if(jump_change == true){
        jump_timer++;
        player.y--;
        jump_detect = false;
        g = false;
        g_dect = false;
    }
    if(jump_timer > 200){
        jump_timer = 0;
        jump_change = false;
        g = true;
    }
    if(g_dect == false){
        if(g == true){
            player.y+=player_g+land_g;
        }
    }
}

var land_g = 0.2, num, i = 5;
function land_control(){

    if(country.length <= 4){
        var land_custom = new Land();
        country.push(land_custom);

        if(country.length == 5){
            country[0].x = canvas.width-400;
            country[0].y = canvas.height-100;
            country[1].x = canvas.width-600;
            country[1].y = canvas.height-300;
            country[2].x = canvas.width-200;
            country[2].y = canvas.height-400;
            country[3].x = canvas.width-400;
            country[3].y = canvas.height-600;
            country[4].x = canvas.width-600;
            country[4].y = canvas.height-700;
        }
    }

    country.forEach((a, i, o)=>{
        random_x();
        crash(player,a);
        a.y+=land_g;
        a.draw();
    })
}

var timer_conter = 1000
function random_x(){
    if(timer > timer_conter){
        var down = new Land();
        country.push(down);
        num=Math.floor(Math.random()*3);
        console.log(num);
        switch(num){
            case 0: 
                country[i].x = canvas.width-600;
                timer=0;
                i++;
                if(timer_conter > 300){
                    timer_conter -= 60;
                    land_g+=0.04;
                }
                console.log(timer_conter);
                break;
            case 1: 
                country[i].x = canvas.width-400;
                timer=0;
                i++;
                if(timer_conter > 300){
                    timer_conter -= 60;
                    land_g+=0.04;
                }
                console.log(timer_conter);
                break;
            default:
                country[i].x = canvas.width-200;
                timer=0;
                i++;
                if(timer_conter > 300){
                    timer_conter -= 60;
                    land_g+=0.04;
                }
                console.log(timer_conter);
                break;
        }
        
    }
}


function crash(player, land){
    var player_right = player.x + player.width;
    var player_left = player.x;
    var player_down = player.y + player.height;
    var player_up = player.y;

    var land_right = land.x + land.width;
    var land_left = land.x;
    var land_down = land.y + land.height;
    var land_up = land.y;

    if(land_right == player_left && land_up < player_down && player_up < land_down){
        player.x++
    }if(land_left == player_right && land_up < player_down && player_up < land_down){
        player.x--;
    }if(land_up < player_down && player_up < land_down && land_left < player_right && player_left < land_right){
        player.y-=player_g;
        player.y-=land_g;
        g_dect = true;
        jump_detect = true;
        
    }else{
        g_dect = false;
    }
    if(land_up < player_up && player_up < land_down  && land_left < player_right && player_left < land_right){
        player.y+= land_g +2;
        jump_timer = 0;
        jump_change = false;
        g_dect = false;
        g = true;
    }if(canvas.width <= player_right){
        player.x--;
    }if(canvas.width - canvas.width >= player_left){
        player.x++;
    }if(canvas.height <= player_down){
		stopClock()
        clearInterval(strat);
		getInnerText()
		var UP
		UP=confirm("다시 플래이 하시겠습니까?")
		if(UP){
			alert("다시시작합니다")
			history.go(0);
		}else{
			alert("매인 화면으로 나갑니다")
			var link = 'http://localhost:8000/'; 
			location.href=link;
			location.replace(link);
			window.open(link);
		}
    }if(canvas.height - canvas.height >= player_up){
        player.y++;
    }
    
}
let timerId;
let time = 0;
const stopwatch = document.getElementById("stopwatch");
let  hour, min, sec;

function printTime() {
    time++;
    stopwatch.innerText = getTimeFormatString();
}

//시계 시작 - 재귀호출로 반복실행
function startClock() {
    printTime();
    stopClock();
    timerId = setTimeout(startClock, 1000);
}

//시계 중지
 function stopClock() {
    if (timerId != null) {
        clearTimeout(timerId);
    }
}

// 시계 초기화
function resetClock() {
    stopClock()
    stopwatch.innerText = "00:00:00";
    time = 0;
}

// 시간(int)을 시, 분, 초 문자열로 변환
function getTimeFormatString() {
    hour = parseInt(String(time / (60 * 60)));
    min = parseInt(String((time - (hour * 60 * 60)) / 60));
    sec = time % 60;

    return String(hour).padStart(2, '0') + ":" + String(min).padStart(2, '0') + ":" + String(sec).padStart(2, '0');
}

function getInnerText() {
  const element = document.getElementById('score');
  alert("당신의 점수는 : " + element.innerText);
} 
