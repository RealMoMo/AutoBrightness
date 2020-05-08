package com.newline.autobrightness


//当前亮度
var currentBrightness :Int = 100
//时间间隔
var timeInterval : Long = 2000
//渐变方式



/**
 * new file content: 0819
 * [
{ "value":36,"min":0, "max":0 },
{ "value":15,"min":0, "max":10 },
{ "value":42,"min":10, "max":50 },
{ "value":62,"min":50, "max":100 },
{ "value":80,"min":100, "max":200 },
{ "value":100,"min":200, "max":70000 }
]
 */

//亮度值档位
var level1 :Int = 1
var level2 :Int = 15
var level3 :Int = 42
var level4 :Int = 62
var level5 :Int = 80
var level6 :Int = 100
//亮度范围档位
var levelRange1Min :Float= 0F
var levelRange1Max :Float= 0F
var levelRange2Min :Float= 0F
var levelRange2Max :Float= 10F
var levelRange3Min :Float= 10F
var levelRange3Max :Float= 50F
var levelRange4Min :Float= 50F
var levelRange4Max :Float= 100F
var levelRange5Min :Float= 100F
var levelRange5Max :Float= 200F
var levelRange6Min :Float= 200F
//var levelRange6Max :Float= 70000F
var levelRange6Max :Float= 500F



