 $(function () {
 	
 	function upDataUITable(dom,data,columns){
		//dom.bootstrapTable('destroy');
		dom.bootstrapTable({
//              url: url,         //请求后台的URL（*）
//              method: type,                      //请求方式（*）
				data:data,
                striped: false,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                toolbar: '#toolbar',                //工具栏
                queryParams: function (params) {	//传递参数（*）
					            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
					                rows: params.limit,   //页面大小
					                page: params.offset/params.limit+1,
					                order: params.order
					            };
					            return temp;
					        },
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                //contentType: "text",				//返回格式
                strictSearch: false,				//是否开启搜索
                contentType: "application/x-www-form-urlencoded",//设置请求头 类型为 form-date提交
                clickToSelect:false,
                radio:true,
                showColumns: true,                  //是否显示所有的列
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                showColumns: false,
                showToggle: false,
                columns: columns,
			});
	} 
 	
 	var columns = [
    {
        field: 'materalCode',
        title: '物料代码',
        width: '15%'
    },  {
        field: 'materalName', 
        title: '物料名称',
        width: '10%'
    }, {
        field: 'materalModel', 
        title: '规格型号',
        width: '10%'
    }, {
        field: 'materalNumber',
        title: '数量',
        width: '10%'
    }, {
        field: 'materalWeight', 
        title: '重量',
        width: '10%'
    }, {
        field: 'materalWarehouse', 
        title: '仓库',
        width: '15%'
    }, {
        field: 'materalLocation', 
        title: '仓位',
        width: '15%'
        
    }, {
        field: 'materalBrand', 
        title: '牌号',
        width: '10%'
        
    }, {
        field: 'materalTechnicalStandards', 
        title: '技术标准',
        width: '15%'
        
    }];
 	
 	
 	var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
	    $.ajax({
	        url: 'json/materialdemo.json',
	        type: 'get',
	        dataType:'text',
	        error: function (a) {
	            console.log('返回错误')
	        },        
	        success: function (a) {
	        	var data=eval("("+a+")");
	        	console.log(data.total);
	      		upDataUITable($("#materal-table"), data.total, columns);
	      		initLine(data);
	        },
	
	    }); 
            
        }
        return oTableInit;
    }
 	
 	var oTable = new TableInit();
    oTable.Init();
    function initLine(data){
    	var xAxisArr = new Array();
    	var seriesData = new Array();
    	for(var item in data.total){
    		var total = data.total[item];
			xAxisArr.push(total.materalName);
			seriesData.push(total.materalNumber);
		}
	    var lineChart = echarts.init(document.getElementById("materal-line-chars"));
	    var lineoption = {
	        tooltip : {
	            trigger: 'axis',
	            axisPointer : {
	                type : 'shadow'
	            }            
	        },
	        legend: {
	            data:['重量']
	        },
	        grid:{
	            x:40,
	            x2:40,
	            y2:24,
	        },
	        xAxis : [
	            {
	                type : 'category',
	                data : xAxisArr
	            }
	        ],
	        yAxis : [
	            {	
	            	name:'重量',
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
	                itemStyle:{
	                    normal:{
	                        color:'#00FF66',
	                    }
	                },
	                data:seriesData,
	
	            }
				
	        ]
	    };
	    lineChart.setOption(lineoption);
	    $(window).resize(lineChart.resize);
    }
    $('.selectpicker').selectpicker();
})