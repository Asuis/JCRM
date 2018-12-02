$(document).ready(function () {
    // var domestic_map_chart = echarts.init(document.getElementById('domestic-map'));

    // domestic_map_chart.setOption({
    //     series: [{
    //         type: 'map',
    //         map: 'china'
    //     }]
    // });

    // 请求选择框数据
    $.get('http://localhost:9027/selectCuster', {}, function (data) {
        var options = '';
        data.forEach(item => {
            options += `<option value="${ item.value }">${ item.name }</option>`
        })
        $('#customer-selectpicker').html(`
        <optgroup label="客户">
            ${ options }
        </optgroup>`)
        $("#customer-selectpicker").selectpicker('refresh')
        $("#customer-selectpicker").selectpicker('selectAll')
    }, 'json');
    $.get('http://localhost:9027/selectProduct', {}, function (data) {
        var options = '';
        data.forEach(item => {
            options += `<option value="${ item.value }">${ item.name }</option>`
        })
        $('#product-selectpicker').html(`
        <optgroup label="产品">
            ${ options }
        </optgroup>`)
        $("#product-selectpicker").selectpicker('refresh')
        $("#product-selectpicker").selectpicker('selectAll')
    }, 'json');


    var oldCustomerData = null;
    var customer_chart = echarts.init(document.getElementById('customer'));
    function getCustomerAnalysisData (trendRadio) {
        var startDate = $('#customer-datetimepicker-start').datepicker('getDate');
        var endDate = $('#customer-datetimepicker-end').datepicker('getDate');
        var customerIds = $('#customer-selectpicker').val();
        if (customerIds === null) {
            customer_chart.clear();
            return;
        }
        var uid = '';
        customerIds.forEach(id => { uid += id + ','})
        // 请求客户分析数据
        $.get('http://localhost:9027/khfx', {
            uid: uid.substring(0, uid.length - 1),
            bTime: (startDate.getFullYear() - 5) + '/01/01',
            eTime: endDate.getFullYear() + '/12/31'
        }, function (data) {
            oldCustomerData = data
            var yearList = []
            for (i = startDate.getFullYear(); i <= endDate.getFullYear(); i++) {
                yearList.push(i)
            }

            var itemAmountArr = []
            var seriesData = []
            data.forEach(item => {
                var seriesItem = {
                    name: item['itemName'],
                    data: yearList.map(year => { return 0 }),
                    type: 'line'
                };
                // amountList数据只有一个时，amountList不是数组结构
                if (item.amountList instanceof Array) {
                    item.amountList.forEach(amountItem => {
                        seriesItem.data[yearList.indexOf(amountItem['fDate'])] = amountItem['fAmount']
                    });
                    var amount = 0
                    item.amountList.forEach(i => { amount += i['fAmount']})
                    itemAmountArr.push({
                        itemName: item['itemName'],
                        amount
                    })
                } else {
                    seriesItem.data[yearList.indexOf(item.amountList['fDate'])] = item.amountList['fAmount']
                    itemAmountArr.push({
                        itemName: item['itemName'],
                        amount: item.amountList['fAmount']
                    })
                }
                seriesData.push(seriesItem)
            })
            
            // 根据数量排序
            itemAmountArr = itemAmountArr.sort(function(a, b) {
                return b['amount'] - a['amount']
            })

            // 限制为数量前十
            var limitLength = 10
            itemAmountArr.splice(limitLength, itemAmountArr.length - limitLength + 1)
            var limitSeriesData = []

            var elseData = {
                name: '其他',
                data: yearList.map(year => { return 0 }),
                type: 'line'
            }
            var allData = {
                name: '总和',
                data: yearList.map(year => { return 0 }),
                type: 'line'
            }

            seriesData.forEach(item => {
                var isElse = true
                for (var i = 0; i < itemAmountArr.length; i++) {
                    if (item['name'] === itemAmountArr[i]['itemName']) {
                        limitSeriesData.push(item)
                        isElse = false
                    }
                }
                allData['data'] = item['data'].map(function(v, i) {
                    return v + allData['data'][i];
                })
                if (isElse) {
                    elseData['data'] = item['data'].map(function(v, i) {
                        return v + elseData['data'][i];
                    })
                }
            })
            limitSeriesData.push(elseData)
            limitSeriesData.push(allData)

            customer_chart.clear();
            customer_chart.setOption({
                tooltip: {
                    trigger: 'axis',
                    axisPointer : {
                        type : 'shadow'
                    }
                },
                legend: {
                    data: itemAmountArr.map(item =>{ return item['itemName'] }).concat(['其他', '总和']),
                    bottom: '0'
                },
                xAxis: {
                    type: 'category',
                    data: yearList
                },
                yAxis: {
                    type: 'value'
                },
                series: limitSeriesData
            });
        },'json');
    }

    var product_chart = echarts.init(document.getElementById('product'));
    function getProductAnalysisData (startDate, endDate) {
        var startDate = $('#product-datetimepicker-start').datepicker('getDate');
        var endDate = $('#product-datetimepicker-end').datepicker('getDate');
        var productIds = $('#product-selectpicker').val();
        if (productIds === null) {
            product_chart.clear();
            return;
        }
        var products = '';
        productIds.forEach(id => { products += id + ','})
        // 请求客户分析数据
        $.get('http://localhost:9027/productAnalysis', {
            products: products.substring(0, products.length - 1),
            bTime: (startDate.getFullYear() - 5) + '/01/01',
            eTime: endDate.getFullYear() + '/12/31',
        }, function (data) {
            var yearList = [];
            for (i = startDate.getFullYear(); i <= endDate.getFullYear(); i++) {
                yearList.push(i);
            }

            var itemAmountArr = []
            var seriesData = [];
            data.forEach(item => {
                var seriesItem = {
                    name: item['fCustName'],
                    data: yearList.map(year => { return 0 }),
                    type: 'line'
                };
                // amountList数据只有一个时，amountList不是数组结构
                if (item.amountList instanceof Array) {
                    item.amountList.forEach((amountItem, ind) => {
                        seriesItem.data[yearList.indexOf(amountItem['fDate'])] = amountItem['amount'];
                    });
                    var amount = 0
                    item.amountList.forEach(i => { amount += i['amount']})
                    itemAmountArr.push({
                        fCustName: item['fCustName'],
                        amount
                    })
                } else {
                    seriesItem.data[yearList.indexOf(amountItem['fDate'])] = item.amountList['amount'];
                    itemAmountArr.push({
                        fCustName: item['fCustName'],
                        amount: item.amountList['amount']
                    })
                }
                seriesData.push(seriesItem);
            })

             // 根据数量排序
             itemAmountArr = itemAmountArr.sort(function(a, b) {
                return b['amount'] - a['amount']
            })

            // 限制为数量前十
            var limitLength = 10
            itemAmountArr.splice(limitLength, itemAmountArr.length - limitLength + 1)
            var limitSeriesData = []

            var elseData = {
                name: '其他',
                data: yearList.map(year => { return 0 }),
                type: 'line'
            }
            var allData = {
                name: '总和',
                data: yearList.map(year => { return 0 }),
                type: 'line'
            }

            seriesData.forEach(item => {
                var isElse = true
                for (var i = 0; i < itemAmountArr.length; i++) {
                    if (item['name'] === itemAmountArr[i]['fCustName']) {
                        limitSeriesData.push(item)
                        isElse = false
                    }
                }
                allData['data'] = item['data'].map(function(v, i) {
                    return v + allData['data'][i];
                })
                if (isElse) {
                    elseData['data'] = item['data'].map(function(v, i) {
                        return v + elseData['data'][i];
                    })
                }
            })
            limitSeriesData.push(elseData)
            limitSeriesData.push(allData)

            product_chart.clear();
            product_chart.setOption({
                tooltip: {
                    trigger: 'axis',
                    axisPointer : {
                        type : 'shadow'
                    }
                },
                legend: {
                    data: data.map(item =>{ return item['fCustName'] }).concat(['其他', '总和']),
                    bottom: '0'
                },
                xAxis: {
                    type: 'category',
                    data: yearList
                },
                yAxis: {
                    type: 'value'
                },
                series: limitSeriesData
            });
        },'json');
    }

    // 设置默认时间
    var nowDate = new Date();

    // 初始化客户分析日期控件
    $('#customer-datetimepicker').datepicker({
        language: 'zh-CN',
        format: 'yyyy',
        startView: 2,
        endDate: new Date(nowDate.getFullYear() + '/12/31'),
        minViewMode: 2,
        maxViewMode: 2,
        autoclose: true
    });
    // 初始化产品分析日期控件
    $('#product-datetimepicker').datepicker({
        language: 'zh-CN',
        format: 'yyyy',
        endDate: new Date(nowDate.getFullYear() + '/12/31'),
        startView: 2,
        minViewMode: 2,
        maxViewMode: 2,
        autoclose: true
    });

    $('#product-datetimepicker-start').datepicker('setDate', new Date((nowDate.getFullYear() - 5) + '/01/01'));
    $('#product-datetimepicker-end').datepicker('setDate',  new Date(nowDate.getFullYear() + '/12/31'));
    $('#customer-datetimepicker-start').datepicker('setDate',  new Date((nowDate.getFullYear() - 5) + '/01/01'));
    $('#customer-datetimepicker-end').datepicker('setDate',  new Date(nowDate.getFullYear() + '/12/31'));

    // 设置变化监听
    $('#customer-selectpicker').on('change', function () {
        getCustomerAnalysisData()
    })
    $('#customer-datetimepicker').on('changeDate', function () {
        getCustomerAnalysisData()
    })
    $('#product-selectpicker').on('change', function () {
        getProductAnalysisData()
    })
    $('#product-datetimepicker').on('changeDate', function () {
        getProductAnalysisData()
    })

    $('#trend-ratio-btn').on('click', function () {
        var startDate = $('#customer-datetimepicker-start').datepicker('getDate');
        var endDate = $('#customer-datetimepicker-end').datepicker('getDate');
        var trendRatio = $('#trend-ratio').val();
        var data = oldCustomerData;
        var yearList = [];
        for (i = startDate.getFullYear(); i <= endDate.getFullYear(); i++) {
            yearList.push(i);
        }
        if (trendRatio) {
            yearList.push(Number(yearList[yearList.length - 1] + 1));
        }

        var seriesData = [];
        data.forEach(item => {
            var seriesItem = {
                name: item['fCustName'],
                data: yearList.map(year => { return 0 }),
                type: 'line'
            };
            // amountList数据只有一个时，amountList不是数组结构
            if (item.amountList instanceof Array) {
                item.amountList.forEach(amountItem => {
                    seriesItem.data[yearList.indexOf(amountItem['fDate'])] = amountItem['fAmount'];
                });
            } else {
                seriesItem.data[yearList.indexOf(item.amountList['fDate'])] = item.amountList['fAmount'];
            }
            if (trendRatio) {
                seriesItem.data[yearList.length - 1] = Number(seriesItem.data[yearList.length - 2] * trendRatio / 100).toFixed(0);
            }
            seriesData.push(seriesItem)
        })

        customer_chart.clear();
        customer_chart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer : {
                    type : 'shadow'
                }
            },
            legend: {
                data: data.map(item =>{ return item['fCustName'] }),
                bottom: '0'
            },
            xAxis: {
                type: 'category',
                data: yearList
            },
            yAxis: {
                type: 'value'
            },
            series: seriesData
        });
    })

    $('.selectpicker').selectpicker();

})