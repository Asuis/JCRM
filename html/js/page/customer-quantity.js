$(document).ready(function () {

    $.get('http://39.106.120.34:9027/khslgl', {}, function(data) {
        console.log(data)
        var customerQuantity = data[0].amount + data[1].amount;
        $('#customer-quantity').text(customerQuantity);
        $('#first-class-distributor-quantity').text(data[0].amount);
        $('#second-class-distributor-quantity').text(data[1].amount);
        $('#first-class-distributor-percent').text(((data[0].amount / customerQuantity) * 100).toFixed(0) + '%');
        $('#second-class-distributor-percent').text(((data[1].amount / customerQuantity) * 100).toFixed(0) + '%');
    }, 'json')

    $.get('http://39.106.120.34:9027/areaFB', {}, function(data) {
        var domestic_chart = echarts.init(document.getElementById('domestic'));
        domestic_chart.setOption({
            tooltip: {
                trigger: 'item',
                formatter: '{b}: {c}'
            },
            xAxis: {
                type: 'category',
                data: data.map(item => { return item['fName'] })
            },
            yAxis: {
                type: 'value',
                name: '客户数量'
            },
            series: [{
                data:  data.map(item => { return item['amount'] }),
                type: 'bar',
                barMaxWidth: 50,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
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
        });
    }, 'json')

    $.get('http://39.106.120.34:9027/xsxsfx', {}, function(data) {
        console.log(data)
        var sales_form_distribution_chart = echarts.init(document.getElementById('sales-form-distribution'));
        sales_form_distribution_chart.setOption({
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b}: {c} ({d}%)'
            },
            // legend: {
            //     orient: 'vertical',
            //     x: 'left',
            //     data: data.map(item => { return item['fName'] })
            // },
            series: [
                {
                    name:'销售形式',
                    type:'pie',
                    roseType : 'radius',
                    radius: ['50%', '80%'],
                    label: {
                        formatter: '{b}\n{d}%',
                        position: 'inner'
                    },
                    data: data.map(item => {
                        return {
                            value: item['amount'],
                            name: item['fName']
                        }
                    })
                }
            ]
        });
    
    }, 'json')

})