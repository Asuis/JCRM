var preUrl='http://localhost:9027';
// 设置默认时间
var nowDate = new Date();
var previousYearDate = new Date(nowDate - 24 * 365 * 3600 * 1000);

var cA = echarts.init(document.getElementById("customerAnalyse"));
var pA = echarts.init(document.getElementById("providerAnalyse"));
function fullFillCustomerSelector() {
    let html = "";
    $.ajax({
        url: preUrl+'/selectCuster',
        type: 'POST',
        dataType: 'json',
        async:false,
    })
    .done(function(res) {
        for(let r of res){
            html += `<option value="${r.value}">${r.name}</option>`
        }
    });
    $(".customerSelector").html(html);
}
function fullFillProviderSelector() {
    let html = "";
    $.ajax({
        url: preUrl+'/selectGYS',
        type: 'POST',
        dataType: 'json',
        async:false,
    })
        .done(function(res) {
            for(let r of res){
                html += `<option value="${r.value}">${r.name}</option>`
            }
        });
    $(".providerSelector").html(html);
}
fullFillCustomerSelector();
fullFillProviderSelector();
$('.selectpicker').selectpicker();
$('.customerSelector').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
    const data = $('.customerSelector').selectpicker('val');
    if(!data){
        loadCustomerAnalyseChart("");
        return;
    }
    let uid = "";
    for(let u of data){
        uid += u;
        uid += ",";
    }
    loadCustomerAnalyseChart(uid);
});
$('.providerSelector').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
    const data = $('.providerSelector').selectpicker('val');
    if(!data){
        loadProviderAnalyseChart("");
        return;
    }
    let uid = "";
    for(let u of data){
        uid += u;
        uid += ",";
    }
    loadProviderAnalyseChart(uid);
});
(function (){
    $('.customerSelector').selectpicker('selectAll');
    let uid = "";
    for(let u of $('.customerSelector').selectpicker('val')){
        uid += u;
        uid += ",";
    }
    loadCustomerAnalyseChart(uid);
})();
(function (){
    $('.providerSelector').selectpicker('selectAll');
    let uid = "";
    for(let u of $('.providerSelector').selectpicker('val')){
        uid += u;
        uid += ",";
    }
    loadProviderAnalyseChart(uid);
})();


//客户分析ajax
function loadCustomerAnalyseChart(
    uid
){
    $.ajax({
        url: preUrl+'/yfAnalysisKH',
        type: 'POST',
        dataType: 'json',
        data: {
            uid:uid
        },

    })
        .done(function(res) {
            res = res[0];
            customerAnalyseChart(res.customers,res.data)
        });
}
//客户分析
function customerAnalyseChart(customers,data) {
    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: {
            type: 'category',
            data: customers
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: data,
            barMaxWidth: 50,
            type: 'bar',
            itemStyle: {
                normal: {
                    color: function(params) {
                        var colorList = [
                            '#0FB64A','#92CE52','#65CCE5','#0377AE','#FFC001',
                            '#22BDBD','#334B5C','#DE9352','#D53A35','#6AB0B8',
                            '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                        ];
                        return colorList[params.dataIndex]
                    }
                }
            }
        }]
    };
    cA.setOption(option);
}


//供应商ajax
function loadProviderAnalyseChart(
    uid
){
    $.ajax({
        url: preUrl+'/yfAnalysisGYS',
        type: 'POST',
        dataType: 'json',
        data: {
            uid:uid
        },

    })
        .done(function(res) {
            res = res[0];
            providerAnalyseChart(res.providers,res.data)
        });
}

function providerAnalyseChart(providers,data) {
    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: {
            type: 'category',
            data: providers
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: data,
            barMaxWidth: 50,
            type: 'bar',
            itemStyle: {
                normal: {
                    color: function(params) {
                        var colorList = [
                            '#0FB64A','#92CE52','#65CCE5','#0377AE','#FFC001',
                            '#22BDBD','#334B5C','#DE9352','#D53A35','#6AB0B8',
                            '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                        ];
                        return colorList[params.dataIndex]
                    }
                }
            }
        }]
    };
    pA.setOption(option);
}
(function () {
    $.ajax({
        url: preUrl+'/amountCust',
        type: 'POST',
        dataType: 'json',
    })
        .done(function(res) {
            console.log(res[0].data)
            $("#amountCust").html(formatMoney(res[0].data))
        });
    $.ajax({
        url: preUrl+'/amountSupply',
        type: 'POST',
        dataType: 'json',
    })
        .done(function(res) {
            $("#amountSupply").html(formatMoney(res[0].data))
        });
})();//标题


function formatMoney(num){
    num = num.toFixed(2);
    num = parseFloat(num)
    num = num.toLocaleString();
    return num;//返回的是字符串23,245.12保留2位小数
}

// 轮询
// function loop() {
//     const data = $('.providerSelector').selectpicker('val');
//     if(!data){
//         loadProviderAnalyseChart("");
//         return;
//     }
//     let uid = "";
//     for(let u of data){
//         uid += u;
//         uid += ",";
//     }
//     loadProviderAnalyseChart(uid);
//     const data = $('.customerSelector').selectpicker('val');
//     if(!data){
//         loadCustomerAnalyseChart("");
//         return;
//     }
//     let uid = "";
//     for(let u of data){
//         uid += u;
//         uid += ",";
//     }
//     loadCustomerAnalyseChart(uid);
//     setTimeout(loop(),1000);
// }
// loop();