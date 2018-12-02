$(document).ready(function () {
    
    var domestic_chart = echarts.init(document.getElementById('order-amount'));

    var areaNameArr = [
        '东北区',
        '华北区',
        '华中区',
        '华东区',
        '华南区',
        '西南区',
        '西北区',
        '港澳台',
        '国外'
    ]

    function getOrderAmountData(startDate, endDate) {
        // 请求区域分析数据
        $.get('http://39.106.120.34:9027/areaFX', {
            bTime: startDate.getFullYear() + '/' + (startDate.getMonth() + 1) + '/' + startDate.getDate(),
            eTime: endDate.getFullYear() + '/' + (endDate.getMonth() + 1) + '/' + endDate.getDate()
        }, function(data) {
            // 获取月份区间
            var maxMonth = -1,
                minMonth = 13;
            data.forEach(item => {
                item['orderAmount'].forEach(item1 => {
                    var month = Number(item1['fDate']);
                    maxMonth = month > maxMonth ? month : maxMonth;
                    minMonth = month < minMonth ? month : minMonth;
                });
            });
            var monthList = [];
            for (var i = minMonth; i <= maxMonth; i++) {
                monthList.push(i);
            }
            
            // 用于生成每个月份数据
            var monthData = {};
            monthList.forEach(month => {
                monthData[month] = [0,0,0,0,0,0,0,0,0];
                areaNameArr.forEach(areaName => {
                    data.forEach(item => {
                        if (item['fName'] === areaName) {
                            item['orderAmount'].forEach(item1 => {
                                var m = Number(item1['fDate']);
                                monthData[month][areaNameArr.indexOf(areaName)] = month === m ? item1['fAmount'] : 0;
                            });
                        }
                    });
                });
            });
            domestic_chart.clear();
            domestic_chart.setOption({
                tooltip : {
                    trigger: 'axis',
                    axisPointer : { 
                        type : 'shadow'
                    }
                },
                legend: {
                    data: monthList.map( month => { return month  + '月' }),
                    bottom: '0'
                },
                xAxis:  {
                    type: 'value'
                },
                yAxis: {
                    type: 'category',
                    data: areaNameArr
                },
                series: monthList.map(month => {
                    return {
                        name: month + '月',
                        type: 'bar',
                        stack: '总量',
                        data: monthData[month]
                    }
                })
            });

        }, 'json')
    }

    var customer_order = echarts.init(document.getElementById('customer-order'));
    function getCustomerOrderData (startDate, endDate) {
        // 请求客户订单分析数据
        $.get('http://39.106.120.34:9027/khddfx', {
            bTime: startDate.getFullYear() + '/' + (startDate.getMonth() + 1) + '/' + startDate.getDate(),
            eTime: endDate.getFullYear() + '/' + (endDate.getMonth() + 1) + '/' + endDate.getDate()
        }, function (data) {
            customer_order.clear();
            customer_order.setOption({
                tooltip: {
                    trigger: 'item',
                    formatter: function (item) {
                        return '订单总金额：￥' + item.data[0] + '<br />订单数：' + item.data[1]
                    }
                },
                xAxis: {
                    name: '订单数量（求和）',
                    nameLocation: 'center'
                },
                yAxis: {
                    name: '订单金额（求和）'
                },
                series: [
                    {
                        symbolSize: 20,
                        data: data.map(item => { return [ item['allPrice'], item['orderSum'] ] }),
                        type: 'scatter',
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
                    }
                ]
            });
        }, 'json')
    }

    // 初始化区域分析日期控件
    $("#order-amount-datetimepicker").datepicker({
        language: "zh-CN",
        format: 'yyyy-mm',
        startView: 1,
        minViewMode: 1,
        maxViewMode: 1,
        autoclose: true
    });
    // 初始化用户订单分析日期控件
    $("#customer-order-datetimepicker").datepicker({
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

    $("#order-amount-datetimepicker-start").datepicker('setDate', previousYearDate);
    $("#order-amount-datetimepicker-end").datepicker('setDate', nowDate);
    $("#customer-order-datetimepicker-start").datepicker('setDate', previousYearDate);
    $("#customer-order-datetimepicker-end").datepicker('setDate', nowDate);
    // 获取一年内的数据
    getOrderAmountData(previousYearDate, nowDate);
    getCustomerOrderData(previousYearDate, nowDate);

    // 设置变化监听
    $("#order-amount-datetimepicker").on('changeDate', function () {
        getOrderAmountData($("#order-amount-datetimepicker-start").datepicker('getDate'),
        $("#order-amount-datetimepicker-end").datepicker('getDate'))
    })
    $("#customer-order-datetimepicker").on('changeDate', function () {
        getCustomerOrderData($("#customer-order-datetimepicker-start").datepicker('getDate'),
        $("#customer-order-datetimepicker-end").datepicker('getDate'))
    })


})