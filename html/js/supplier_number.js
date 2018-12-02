$(function () {
/*    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
    var lineoption = {
        title : {
            text: ''
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['客户数量']
        },
        grid:{
            x:40,
            x2:40,
            y2:24,
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['非洲','东南亚','南欧','北欧','澳洲','大洋洲']
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value}'
                }
            }
        ],
        series : [
            {
                name:'客户数量',
                type:'line',
                symbolSize: 5,
                itemStyle:{
                    normal:{
                        lineStyle:{
                            width:2,
                            type:'solid'  //'dotted'虚线 'solid'实线
                        },
                        color:'green',
                        borderColor:'green'
                    }
                },
                data:[8, 10, 12, 4, 3, 9],

            },

        ]
    };
    lineChart.setOption(lineoption);
    $(window).resize(lineChart.resize);*/

/*
    var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
    var pieoption = {
        title : {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'},
                    {value:234, name:'联盟广告'},
                    {value:135, name:'视频广告'},
                    {value:1548, name:'搜索引擎'}
                ]
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);
   */ 

    var regionArr = {
    	'东北区':0,
    	'华东区':0,
    	'华中区':0,
    	'华北区':0,
    	'华南区':0,
    	'西北区':0,
    	'西南区':0,
    	'港澳台':0,
    	'国外':0
    }   
	//在regionArr更新后调用
    function InitBar(){
		var Xregion = new Array();
		for(var item in regionArr){
			Xregion.push(item);
		}
    	
		var data = new Array();
		for(var item in Xregion){			
			data.push(regionArr[Xregion[item]]);
		}
		
			
	    var barChart = echarts.init(document.getElementById("echarts-bar-chart"));
	    var baroption = {
	        title : {
	            text: ''
	        },
	        tooltip : {
	            trigger: 'axis'
	        },
	        legend: {
	            data:['']
	        },
	        grid:{
	            x:30,
	            x2:40,
	            y2:24
	        },
	        calculable : true,
	        xAxis : [
	            {
	                type : 'category',
	                data : Xregion
	            }
	        ],
	        yAxis : [
	            {
	                type : 'value'
	            }
	        ],
	        series : [
	            {
	                name:'蒸发量',
	                type:'bar',
	                barWidth:20,
	                data:data,
	        		color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);}         
	            }
	        ]
	    };
	    barChart.setOption(baroption);
	    window.onresize = barChart.resize;
    }


/*
    var mapChart = echarts.init(document.getElementById("echarts-map-chart"));
    
    
var data = [
    
];
var geoCoordMap = {
   
};

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
};
*//*
option = {
    title: {
        text: '全国主要城市空气质量 - 百度地图',
        subtext: 'data from PM25.in',
        sublink: 'http://www.pm25.in',
        left: 'center'
    },
    tooltip : {
        trigger: 'item'
    },
    bmap: {
        center: [104.114129, 37.550339],
        zoom: 5,
        roam: true,
        mapStyle: {
            styleJson: [{
                'featureType': 'water',
                'elementType': 'all',
                'stylers': {
                    'color': '#d1d1d1'
                }
            }, {
                'featureType': 'land',
                'elementType': 'all',
                'stylers': {
                    'color': '#f3f3f3'
                }
            }, {
                'featureType': 'railway',
                'elementType': 'all',
                'stylers': {
                    'visibility': 'off'
                }
            }, {
                'featureType': 'highway',
                'elementType': 'all',
                'stylers': {
                    'color': '#fdfdfd'
                }
            }, {
                'featureType': 'highway',
                'elementType': 'labels',
                'stylers': {
                    'visibility': 'off'
                }
            }, {
                'featureType': 'arterial',
                'elementType': 'geometry',
                'stylers': {
                    'color': '#fefefe'
                }
            }, {
                'featureType': 'arterial',
                'elementType': 'geometry.fill',
                'stylers': {
                    'color': '#fefefe'
                }
            }, {
                'featureType': 'poi',
                'elementType': 'all',
                'stylers': {
                    'visibility': 'off'
                }
            }, {
                'featureType': 'green',
                'elementType': 'all',
                'stylers': {
                    'visibility': 'off'
                }
            }, {
                'featureType': 'subway',
                'elementType': 'all',
                'stylers': {
                    'visibility': 'off'
                }
            }, {
                'featureType': 'manmade',
                'elementType': 'all',
                'stylers': {
                    'color': '#d1d1d1'
                }
            }, {
                'featureType': 'local',
                'elementType': 'all',
                'stylers': {
                    'color': '#d1d1d1'
                }
            }, {
                'featureType': 'arterial',
                'elementType': 'labels',
                'stylers': {
                    'visibility': 'off'
                }
            }, {
                'featureType': 'boundary',
                'elementType': 'all',
                'stylers': {
                    'color': '#fefefe'
                }
            }, {
                'featureType': 'building',
                'elementType': 'all',
                'stylers': {
                    'color': '#d1d1d1'
                }
            }, {
                'featureType': 'label',
                'elementType': 'labels.text.fill',
                'stylers': {
                    'color': '#999999'
                }
            }]
        }
    },
    series : [
        {
            name: 'pm2.5',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: convertData(data),
            symbolSize: function (val) {
                return val[2] / 10;
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: 'green'
                }
            }
        }
    ]
};
    mapChart.setOption(option,true);
    $(window).resize(mapChart.resize);*/
    function updateBar(){
	    $.ajax({
	        url: local+'supplierQuantityAnalysis',
	        type: 'get',
	        dataType:'json',
	        error: function (a) {
	            console.log('返回错误')
	        },        
	        success: function (data) {
	        	console.log(data);
	        	for(var item in data){
	        		var jsonObject = data[item];
	        		regionArr[jsonObject.name] = jsonObject.value;
	        		console.log(regionArr);
	        	}
	        	InitBar(regionArr);
	        },
	
	    }); 
    }

	
	function updateSupplierAll(){
	    $.ajax({
	        url: local+'countSupplier',
	        type: 'get',
	        dataType:'json',//
	        error: function (a) {
	            console.log('返回错误')
	        },        
	        success: function (data) {
	        	console.log(data.supplierAll);
				$("#supplier-all").html(data.supplierAll);
	        },
	
	    }); 		
	}
    //var local = 'http://127.0.0.1:9090/';	
    var local = 'http://39.106.120.34:9027/';
	updateBar();	
	updateSupplierAll();
});
