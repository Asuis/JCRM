
$('.selectpicker').selectpicker();


// 初始化客户分析日期控件
$("#area-department-datetimepicker").datepicker({
    language: "zh-CN",
    format: 'yyyy-mm',
    startView: 1,
    minViewMode: 1,
    maxViewMode: 1,
    autoclose: true
});$("#depart-sale-datetimepicker").datepicker({
    language: "zh-CN",
    format: 'yyyy-mm',
    startView: 1,
    minViewMode: 1,
    maxViewMode: 1,
    autoclose: true
});$("#industry-datetimepicker").datepicker({
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
var preUrl='http://localhost:9027';
var SA = echarts.init(document.getElementById("saleApart"));
var IS = echarts.init(document.getElementById("industrySales"));

$("#area-department-datetimepicker-start").datepicker('setDate', previousYearDate);
$("#area-department-datetimepicker-end").datepicker('setDate', nowDate);
$("#depart-sale-datetimepicker-start").datepicker('setDate', previousYearDate);
$("#depart-sale-datetimepicker-end").datepicker('setDate', nowDate);
$("#industry-datetimepicker-start").datepicker('setDate', previousYearDate);
$("#industry-datetimepicker-end").datepicker('setDate', nowDate);
// 获取一年内的数据
//getOrderAmountData(previousYearDate, nowDate);
function loadAreaDepartmentCharts(
    startT,endT
){
    $.ajax({
        url: preUrl+'/saleAreaDepart',
        type: 'POST',
        dataType: 'json',
        data: {
            bTime: startT.getFullYear() + '-' + (startT.getMonth() + 1) + '-' + startT.getDate(),
            eTime: endT.getFullYear() + '-' + (endT.getMonth() + 1) + '-' + endT.getDate()
        },

    })
        .done(function(res) {
            var departments = [];
            for(let d of res.data){
                departments.push(d.name);
            }
            showAreaDepartement(departments,res.region,res.data)
        });
}

loadAreaDepartmentCharts(previousYearDate,nowDate);
// 设置变化监听
$("#area-department-datetimepicker").on('changeDate', function () {
    loadAreaDepartmentCharts(new Date($("#area-department-datetimepicker-start").datepicker('getDate')),
        new Date($("#area-department-datetimepicker-end").datepicker('getDate')))
})

function showAreaDepartement(departments,region,data) {
    var series = [];
    for(let d of data){
        const s ={
            name: d.name,
            type: 'bar',
            barMaxWidth: 50,
            //stack: '总量',
//                    label: {
//                        normal: {
//                            show: true,
//                            position: 'insideRight'
//                        }
//                    },
            data: d.data
        }
        series.push(s);
    }
    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: departments
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        yAxis:  {
            type: 'value'
        },
        xAxis: {
            type: 'category',
            data: region
        },
        series: series
    };

    SA.setOption(option);
}



function loadIndustrySalesCharts(
    startT,endT
){
    $.ajax({
        url: preUrl+'/hyxse',
        type: 'POST',
        dataType: 'json',
        data: {
            bTime: startT.getFullYear() + '-' + (previousYearDate.getMonth() + 1) + '-' + previousYearDate.getDate(),
            eTime: endT.getFullYear() + '-' + (nowDate.getMonth() + 1) + '-' + nowDate.getDate()
        },

    })
        .done(function(res) {
            res = res[0]
            showIndustryCharts(res.name,res.data1,res.data2)
        });
}

loadIndustrySalesCharts(previousYearDate,nowDate);
// 设置变化监听
$("#industry-datetimepicker").on('changeDate', function () {
    loadIndustrySalesCharts(new Date($("#industry-datetimepicker-start").datepicker('getDate')),
        new Date($("#industry-datetimepicker-end").datepicker('getDate')))
})
function showIndustryCharts(industries,data1,data2) {
    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: {
            type: 'category',
            data: industries
        },
        yAxis: [
            {
                type: 'value',
                name: '销售额',
            },
            {
                type: 'value',
                name: '销售重量  单位：万吨',

            }
        ],
        series: [
            {
                data: data1,
                barMaxWidth: 50,
                type: 'bar'
            },
            {
                data: data2,
                type: 'bar',
                barMaxWidth: 50,
                yAxisIndex: 1,
            }
        ],

    };

    IS.setOption(option);
}




$.ajax({
    url: preUrl+'/annualSales',
    type: 'POST',
    dataType: 'json',

})
    .done(function(res) {
        $("#totalSaleAmount").html(formatMoney(res.data));
        $("#totalSalePercent").html(res.percent);
    });
$("#depart-sale-datetimepicker").on('changeDate', function () {
    showApartmentSale(
        new Date($("#depart-sale-datetimepicker-start").datepicker('getDate'))
        ,new Date($("#depart-sale-datetimepicker-end").datepicker('getDate'))
    );
})
function showApartmentSale(startT,endT) {

    $.ajax({
        url: preUrl+'/gPartSale',
        type: 'POST',
        dataType: 'json',
        data: {
            bTime: startT.getFullYear() + '-' + (startT.getMonth() + 1) + '-' + startT.getDate(),
            eTime: endT.getFullYear() + '-' + (endT.getMonth() + 1) + '-' + endT.getDate()
        },
    })
        .done(function(res) {
            var sets = [
                {sets: ['销售总部'], size: 12},
                {sets: ['销售1'], size: 12},
                {sets: ['销售2'], size: 12},
                {sets: ['销售部','销售1'], size:12},
                //{sets: ['A','B'], size: 2}
            ];
            if(res){
                for(let r of res){
                    const set = {
                        sets: [r.name], size: r.data
                    }
                    sets.push(set)
                }
            }
            var chart = venn.VennDiagram();
            d3.select("#apartmentSales").datum(sets).call(chart);
        });



}
showApartmentSale(previousYearDate,nowDate);

function formatMoney(num){
    num = num.toFixed(2);
    num = parseFloat(num)
    num = num.toLocaleString();
    return num;//返回的是字符串23,245.12保留2位小数
}

//轮询
// function loop() {
//     loadAreaDepartmentCharts(new Date($("#area-department-datetimepicker-start").datepicker('getDate')),
//         new Date($("#area-department-datetimepicker-end").datepicker('getDate')))
//     loadIndustrySalesCharts(new Date($("#industry-datetimepicker-start").datepicker('getDate')),
//         new Date($("#industry-datetimepicker-end").datepicker('getDate')))
//     showApartmentSale(
//         new Date($("#depart-sale-datetimepicker-start").datepicker('getDate'))
//         ,new Date($("#depart-sale-datetimepicker-end").datepicker('getDate'))
//     );
//     setTimeout(loop(),1000);
// }
// loop();