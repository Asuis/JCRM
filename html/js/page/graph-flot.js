// 设置默认时间
var nowDate = new Date();
var previousYearDate = new Date(nowDate - 24 * 365 * 3600 * 1000);
var preUrl='http://localhost:9027';
var type = 1;
const potSizeParam = 4.0;

var hots = echarts.init(document.getElementById("hotProducts"));
var pSale = echarts.init(document.getElementById("productsSale"));
var pSaleTI = echarts.init(document.getElementById("productsSaleTargetInfo"));
var dynamicC = echarts.init(document.getElementById("dynamicCharts"));
//ajax获取Selector内容
function fullFillProductSelector() {
    let html = "";
    $.ajax({
        url: preUrl+'/selectProduct',
        type: 'POST',
        dataType: 'json',
        async:false,
    })
        .done(function(res) {
            for(let r of res){
                html += `<option value="${r.value}">${r.name}</option>`
            }
        });
    $(".productPercentSelector").html(html);
}
fullFillProductSelector();
$('.selectpicker').selectpicker();

$("#productSale-datetimepicker").datepicker({
    language: "zh-CN",
    format: 'yyyy-mm',
    startView: 1,
    minViewMode: 1,
    maxViewMode: 1,
    autoclose: true
});
$("#hot10-datetimepicker").datepicker({
    language: "zh-CN",
    format: 'yyyy-mm',
    startView: 1,
    minViewMode: 1,
    maxViewMode: 1,
    autoclose: true
});
$("#productSale-datetimepicker-start").datepicker('setDate', previousYearDate);
$("#productSale-datetimepicker-end").datepicker('setDate', nowDate);
$("#hot10-datetimepicker-start").datepicker('setDate', previousYearDate);
$("#hot10-datetimepicker-end").datepicker('setDate', nowDate);

function getHot10ChartsData(type,startT,endT) {
    $.ajax({
        url: preUrl+'/top10HotSales',
        type: 'POST',
        dataType: 'json',
        data: {
            id:type,
            bTime: startT.getFullYear() + '-' + (startT.getMonth() + 1) + '-' + startT.getDate(),
            eTime: endT.getFullYear() + '-' + (endT.getMonth() + 1) + '-' + endT.getDate()
        },

    })
        .done(function(res) {
            showHot10Charts(res.product,res.year,res.data)
        });
}
function getSalePercentChartsData(products,startT,endT) {
    $.ajax({
        url: preUrl+'/productSalesOccupation',
        type: 'POST',
        dataType: 'json',
        data: {
            products:products,
            bTime: startT.getFullYear() + '-' + (startT.getMonth() + 1) + '-' + startT.getDate(),
            eTime: endT.getFullYear() + '-' + (endT.getMonth() + 1) + '-' + endT.getDate()
        },

    })
        .done(function(res) {
            showProductsSaleCharts(res.year,res.data)
        });
}
//init
(function () {
    $('.productPercentSelector').selectpicker('selectAll');
    let pid = "";
    for(let u of $('.productPercentSelector').selectpicker('val')){
        pid += u;
        pid += ",";
    }
    getHot10ChartsData(type,previousYearDate,nowDate);
    getSalePercentChartsData(pid,previousYearDate,nowDate);
})();



function showHot10Charts(products,years,data) {

    option = {
        tooltip: {
            position: 'top',
            //position: 'bottom',
            formatter: function (params) {
                //return `<div id="hotDetails" style="height: 400px;width: 600px"></div>`
                return products[params.value[0]] +'在 "'+ years[params.value[1]] +'" 销量为'+params.value[2];
            }
        },
        grid: {
            left: 2,
            bottom: 10,
            right: 10,
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: products,
            boundaryGap: true,
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#999',
                    type: 'dashed'
                }
            },
            axisLine: {
                show: true
            }
        },
        yAxis: {
            triggerEvent:true,
            type: 'category',
            data: years,
            axisLine: {
                show: true
            },
            axisLabel:{
                clickable:true
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#999',
                    type: 'dashed'
                }
            },
        },
        series: [{
            name: 'Punch Card',
            type: 'scatter',
            symbolSize: function (val) {
                if(val[2]/potSizeParam<5)return 5;
                if(val[2]/potSizeParam >140)return 140;
                return val[2]/potSizeParam;
            },
            data: data,
            animationDelay: function (idx) {
                return idx * 5;
            }
        }]
    };
    hots.setOption(option,true);

}

function showProductsSaleCharts(year,data) {
    let formatData = [];
    if(data){
        for(let d of data){
            const item =  {
                name:d.name,
                type:'line',
                stack: '总量',
                areaStyle: {},
                data:d.data
            }
            formatData.push(item);
        }
    }
    option = {
        // title: {
        //     text: '产品销售占比图'
        // },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        // legend: {
        //     data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
        // },
        // toolbox: {
        //     feature: {
        //         saveAsImage: {}
        //     }
        // },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : year
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : formatData
    };

    pSale.setOption(option);
}




option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['预期', '实际']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: ['产品1','产品2','产品3','产品4','产品5']
    },
    series: [
        {
            name: '预期',
            barMaxWidth: 50,
            type: 'bar',
            data: [18203, 23489, 29034, 104970, 131744]
        },
        {
            name: '实际',
            barMaxWidth: 50,
            type: 'bar',
            data: [19325, 23438, 31000, 121594, 134141]
        }
    ]
};

pSaleTI.setOption(option);



$('.productPercentSelector').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
    const data = $('.productPercentSelector').selectpicker('val');
    if(!data){
        getSalePercentChartsData(""
            ,new Date($("#productSale-datetimepicker-start").datepicker('getDate'))
            ,new Date($("#productSale-datetimepicker-end").datepicker('getDate')));
        return;
    }
    let pid = "";
    for(let u of data){
        pid += u;
        pid += ",";
    }
    getSalePercentChartsData(pid
        ,new Date($("#productSale-datetimepicker-start").datepicker('getDate'))
        ,new Date($("#productSale-datetimepicker-end").datepicker('getDate')));
});
$("#hot10-datetimepicker").on('changeDate', function () {
    getHot10ChartsData(type
        ,new Date($("#hot10-datetimepicker-start").datepicker('getDate'))
        ,new Date($("#hot10-datetimepicker-end").datepicker('getDate')))
})

function changeType(el) {
    $(el).parent().children('button').removeAttr('disabled')
    $(el).attr({'disabled':'true'});
    type = $(el).val()
    getHot10ChartsData(type
        ,new Date($("#hot10-datetimepicker-start").datepicker('getDate'))
        ,new Date($("#hot10-datetimepicker-end").datepicker('getDate')));
}


//子表
hots.on('click', function (params) {
    if(params.componentType == 'yAxis'){
        getHotListInYear(params.value);
    }
});

function getHotListInYear(year) {

    $.ajax({
        url: preUrl+'/hotSale10byYear',
        type: 'POST',
        dataType: 'json',
        data:{
            year1:year
        }
    })
        .done(function(res) {
            let html = `<tr >
                        <td>排名</td>
                        <td>产品名称</td>
                        <td>重量</td>
                        <td>金额</td>
                    </tr>`;
            if(res.data){
                for(let s of res.data){
                    html += `
                        <tr onmouseover="getChartData(this)" value="${s.id}" onmouseleave="dynamicChartHide()">
                            <td>${s.ranking}</td>
                            <td>${s.name}</td>
                            <td>${s.weight}</td>
                            <td>${s.money}</td>
                        </tr>
                    `
                }
            }
            $("#modalContent").html(html);
        });
    $('#myModalLabel').html(year+'年热销品排名');

    $('#myModal').modal('show');

}

$('#myModal').on('hide.bs.modal', function (e) {
    dynamicChartHide();
})
function getChartData(el) {
    $.ajax({
        url: preUrl+'/salesTrend',
        type: 'POST',
        dataType: 'json',
        data:{id1:$(el).attr('value')},
    })
        .done(function(res) {
            dynamicChartShow(res.year,res.data)
        });
}
function dynamicChartShow(years, data) {
    $("#dynamicCharts").show()
    option = {
        xAxis: {
            type: 'category',
            data: years
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: data,
            type: 'line'
        }]
    };

    dynamicC.setOption(option);
}
function dynamicChartHide() {
    $("#dynamicCharts").hide()
}
//轮询
// function loop() {
//     const data = $('.productPercentSelector').selectpicker('val');
//     if(!data){
//         getSalePercentChartsData(""
//             ,new Date($("#productSale-datetimepicker-start").datepicker('getDate'))
//             ,new Date($("#productSale-datetimepicker-end").datepicker('getDate')));
//         return;
//     }
//     let pid = "";
//     for(let u of data){
//         pid += u;
//         pid += ",";
//     }
//     getSalePercentChartsData(pid
//         ,new Date($("#productSale-datetimepicker-start").datepicker('getDate'))
//         ,new Date($("#productSale-datetimepicker-end").datepicker('getDate')));
//     getHot10ChartsData(type
//         ,new Date($("#hot10-datetimepicker-start").datepicker('getDate'))
//         ,new Date($("#hot10-datetimepicker-end").datepicker('getDate')))
//     setTimeout(loop(),1000);
// }
// loop();