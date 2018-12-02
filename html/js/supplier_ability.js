$(function () {
	// 初始化日期控件
    $("#order-amount-weight-datetimepicker").datepicker({
        language: "zh-CN",
        format: 'yyyy-mm',
        startView: 1,
        minViewMode: 1,
        maxViewMode: 1,
        autoclose: true
    });



    // 设置默认时间
    var nowDate = new Date();
    var previousYearDate = new Date(nowDate - 24 * 365 * 3600 * 1000);

    $("#order-amount-weight-datetimepicker-start").datepicker('setDate', previousYearDate);
    $("#order-amount-weight-datetimepicker-end").datepicker('setDate', nowDate);


    // 设置变化监听
    $("#order-amount-weight-datetimepicker").on('changeDate', function () {
        $("#order-amount-weight-datetimepicker-end").datepicker('getDate')
    })
    $("#order-amount-weight-datetimepicker").on('changeDate', function () {
    })	
	
	function initOrderAmountAndWeight(data){
		
		var monthToNum = {
			"1月":0,
			"2月":1,
			"3月":2,
			"4月":3,
			"5月":4,
			"6月":5,
			"7月":6,
			"8月":7,
			"9月":8,
			"10月":9,
			"11月":10,
			"12月":11,
		};
		
		var orderAmountData = new Array(12);
		var orderWeightData = new Array(12);
		
		for(var i in data){
			var jsonObject = data[i];
			var num = monthToNum[jsonObject.month];
			orderWeightData.splice(num,0,jsonObject.weight)
			orderAmountData.splice(num,0,jsonObject.amount);
		}
		
	    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
	    var lineoption = {
	        tooltip : {
	            trigger: 'axis',
	            axisPointer : {
	                type : 'shadow'
	            }            
	        },
	        legend: {
	            data:['重量','金额']
	        },
	        grid:{
	            x:40,
	            x2:40,
	            y2:24,
	        },
	        xAxis : [
	            {
	                type : 'category',
	                data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
	            }
	        ],
	        yAxis : [
	            {	
	            	name:'重量',
	                type : 'value',
	                axisLabel : {
	                    formatter: '{value}'
	                }
	            },
	            {
	            	name:'金额',
	                type : 'value',
	                axisLabel : {
	                    formatter: '{value}'
	                }             	
	                	
	            }
	               
	            
	        ],
	        series : [
	            {
	                name:'重量',
	                type:'line',
	                symbolSize: 5,
	                yAxisIndex:0,
	                itemStyle:{
	                    normal:{
	                        color:'#00FF66',
	                    }
	                },
	                data:orderWeightData,
	
	            }, {
	                name:'金额',
	                type:'line',
	                symbolSize: 5,
	                yAxisIndex:1,
	                itemStyle:{
	                    normal:{
	                        color:'green',
	                        borderColor:'green',
	                    }
	                },
	                data:orderAmountData,
	
	            }
				
	        ]
	    };
	    lineChart.setOption(lineoption);
	    $(window).resize(lineChart.resize);
    }
    function initOrderNumAndamount(data){
    	console.log(data);
    	var itemDate = new Array();
    	var oredrDateToName = new Array();
    	for(var i in data){
    		var jsonObject = data[i];  
    		var item = new Array();
    		item.push(jsonObject.orderNumber);
    		item.push(jsonObject.oredrMon);
    		itemDate.push(item);
    		oredrDateToName[jsonObject.oredrMon+''+jsonObject.orderNumber] = jsonObject.SupplyName;
    	}
    	
	    var scatterChart = echarts.init(document.getElementById("echarts-scatter-chart"));
	    var scatteroption = {
	        title : {
	            text: '',
	            subtext: ''
	        },
	        tooltip : {
	            trigger: 'axis',
	            showDelay : 0,
	
	        },
	        legend: {
	            data:['']
	        },
	        grid:{
	            x:80,
	            x2:100,
	            y2:54
	        },
	        xAxis : [
	            {	
	            	name:'采购订单数量',
	            	nameLocation:"end",
	                type : 'value',
	                scale:true,
	                axisLabel : {
	                    formatter: '{value}'
	                }
	            }
	        ],
	        yAxis : [
	            {	
	            	name:'采购订单金额(求和)',
	                type : 'value',
	                scale:true,
	                axisLabel : {
	                    formatter: '{value} '
	                }
	            }
	        ],
	        series : [
	            {
	                name:'供应商',
	                type:'scatter',
	                tooltip: {
	                    trigger: 'item',
	                    formatter : function (params) {
	                            return oredrDateToName[params.value[1]+""+params.value[0]] + ' :<br/>'
	                               + '订单数总和: '+params.value[0] + ' 个 <br/>'
	                               + '订单金额总和: '+params.value[1] + ' 元 ';
	                    }
	                },
					itemStyle: {
	                    normal: {
	                    	color:'green',
	                        opacity: 0.8
	                    },
	
	                },
	                symbolSize:10,
	                data: itemDate
	            }
	            
	        ]
	    };
	    scatterChart.setOption(scatteroption);
	    $(window).resize(scatterChart.resize);
    }
    
    function initPointChar (data){
    	
     var AS = echarts.init(document.getElementById("echarts-point-chart"));
     option = {
         series:{
             category:{
                 name:['h1','h2'],
                 symbol:'circle',
                 layout:'force',
                 symbolSize:50,
                 itemSytle:{
                     color: {
                         type: 'linear',
                         x: 0,
                         y: 0,
                         x2: 0,
                         y2: 1,
                         colorStops: [{
                             offset: 0, color: 'red' // 0% 处的颜色
                         }, {
                             offset: 1, color: 'blue' // 100% 处的颜色
                         }],
                         globalCoord: false // 缺省为 false
                     }
                 }
             },
             type:'graph',
             data: [
                 {
                     name: '1',
                     x: 98,
                     y: 98,
                     value: 129,
                     symbolSize: 60,
                     category:0
                 },
                 {
                     name: '2',
                     x: 100,
                     y: 100,
                     value: 129,
                     symbolSize: 50 ,
                     itemStyle: {
                         color: 'red'
                     },
                     category:1
                 },
                 {
                     name: '3',
                     x: 99,
                     y: 109,
                     value: 20,
                     symbolSize: 20,
                     itemStyle: {
                         color: 'red'
                     },

                     category:1
                 }
             ]
         },
         graphic: {
             type: 'circle',
             left:'center',
             //top:'center',
             shape: {
                 cx: 99,
                 cy: 100,
                 r: 100
             },
             style:{
                 fill:'#ccc'
             }
         },
     }
     AS.setOption(option);
    }
    function initCycle(data){
    	var yAxisData = new Array();
    	var avgData = new Array();
    	var maxData = new Array();
    	var minData = new Array();
    	
    	for(var a in data){
    		var jsonObject = data[a];
    		avgData.push(jsonObject.cycleAvg);
    		maxData.push(jsonObject.cycleMax);
    		minData.push(jsonObject.cycleMin);
    		yAxisData.push(jsonObject.name);
    	}
    	
    	
	    var barChart = echarts.init(document.getElementById("echarts-bar-chart"));
	    var baroption = {
	         title: {
	        text: ''
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    legend: {
	        data: ['最长周期', '最短周期', '平均周期']
	    },
	    grid: {
	        left: 100
	    },
	    toolbox: {
	        show: true,
	    },
	    xAxis: {
	        type: 'value',
	        name: 'Days',
	        axisLabel: {
	            formatter: '{value}'
	        }
	    },
	    yAxis: {
	        type: 'category',
	        inverse: true,
	        data: yAxisData,
	        axisLabel: {
	            formatter: function (value) {
	                return  value ;
	            },
	            margin: 20,
	
	        }
	    },
	    series: [
	            {
	            name: '平均周期',
	            type: 'bar',
	            itemStyle:{
	                normal:{
	                    color:'#00CCFF',
	                }
	            },            
	            
	            data: avgData
		        },
		        {
		        name: '最短周期',
		        type: 'bar',
		        itemStyle:{
		            normal:{
		                color:'#00FF00',
		            }
		        },            
		        data: minData
		        },
		        {
		            name: '最长周期',
		            type: 'bar',
		            data: maxData,
		            itemStyle:{
		                normal:{
		                    color:'#009900',
		                }
		            },
		            markPoint: {
		                symbolSize: 1,
		                symbolOffset: [0, '50%'],
		                label: {
		                   normal: {
		                        formatter: '{a|{a}\n}{b|{b} }{c|{c}}',
		                        backgroundColor: 'rgb(242,242,242)',
		                        borderColor: '#aaa',
		                        borderWidth: 1,
		                        borderRadius: 4,
		                        padding: [4, 10],
		                        lineHeight: 26,
		                        // shadowBlur: 5,
		                        // shadowColor: '#000',
		                        // shadowOffsetX: 0,
		                        // shadowOffsetY: 1,
		                        position: 'right',
		                        distance: 20,
		                        rich: {
		                            a: {
		                                align: 'center',
		                                color: '#fff',
		                                fontSize: 18,
		                                textShadowBlur: 2,
		                                textShadowColor: '#000',
		                                textShadowOffsetX: 0,
		                                textShadowOffsetY: 1,
		                                textBorderColor: '#333',
		                                textBorderWidth: 2
		                            },
		                            b: {
		                                 color: '#333'
		                            },
		                            c: {
		                                color: '#ff8811',
		                                textBorderColor: '#000',
		                                textBorderWidth: 1,
		                                fontSize: 22
		                            }
		                        }
		                   }
		                },
		                data: [
	
		                ]
		            }
		        }
			]
	    };
	    barChart.setOption(baroption);
	
	    window.onresize = barChart.resize;
    }
    
    function updateCycle(){
	    $.ajax({
	        url: local+'avgTimeJH',
	        type: 'get',
	        dataType:'json',//
	        error: function (data) {
	            console.log('返回错误')
	        },        
	        success: function (data) {
	        	initCycle(data);
	        },
	
	    }); 		
	}
    
    function updateOrderNumAndamount(){
	    $.ajax({
	        url: local+'SupplylineAnalysis',
	        type: 'get',
	        dataType:'json',//
	        error: function (a) {
	            console.log('返回错误')
	        },        
	        success: function (data) {
	        	initOrderNumAndamount(data);
	        },
	
	    });     	
    }
    
    function updateOrderAmountAndWeight(){
	    $.ajax({
	        url: local+'supplyWeightandSum/1',
	        type: 'get',
	        dataType:'json',//
	        error: function (a) {
	            console.log('返回错误')
	        },        
	        success: function (data) {
	        	initOrderAmountAndWeight(data);
	        },
	
	    });     	
    }
    
    function initSupplierSelect(){
    	$.ajax({
	        url: local+'supplier_select',
	        type: 'get',
	        dataType:'json',//
	        error: function (a) {
	            console.log('返回错误')
	        },        
	        success: function (a) {
	        	console.log(a);
	        	var optionHtml;
	        	for(var item in a){
	        		optionHtml += '<option value="'+a[item].value+'">'+a[item].name+'</option>'
	        	}
	        	console.log(a);
	        	$("#supplier_select").html(optionHtml);
	        	$('.selectpicker').selectpicker();
	        },
	
	    });  	
    	
    }
    
    var local = 'http://127.0.0.1:9027/';
    //var local = 'http://39.106.120.34:9027/';
    updateCycle();
    updateOrderNumAndamount();
    updateOrderAmountAndWeight();
    initPointChar();
	initSupplierSelect()
   });