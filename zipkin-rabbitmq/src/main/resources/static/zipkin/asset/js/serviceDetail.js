/**
 * Created by weichunhe on 2016/8/29.
 */
require("app").register.controller("ServiceDetailController", function ($scope, $timeout, $myhttp, $location) {
    var searchParam = $location.search();
    $scope.serviceId = searchParam.serviceId;
    $scope.startDate = new Date(searchParam.startDate - 0 || (new Date().getTime() - 7 * 24 * 3600000));
    $scope.endDate = searchParam.endDate ? new Date(searchParam.endDate - 0) : new Date();
    $scope.callCostStatistics = {total: 0};
    $scope.callCostDetail = {};
    $scope.callCountStatistics = {total: 0, top5: []};
    $scope.callCostLevel = [
        {
            gte: 0,
            lt: 3000,
            name: '0-3000',
            color: '#096'
        },
        {
            gte: 3000,
            lt: 5000,
            name: '3000-5000',
            color: '#ffde33'
        },
        {
            gte: 5000,
            lt: 5000,
            name: '≥5000',
            color: '#c03'
        }
    ];
    var services = $scope.services = [], denpendencies = [], callCostChartData = [];

    var moment = require('moment');
    var toolbox = {
        show: true,
        top: 10,
        right: 10,
        feature: {
            mark: {show: true},
            dataZoom: {show: true},
            // dataView: {show: true, readOnly: false}
            restore: {show: true}
            // saveAsImage : {show: true}
        }
    };

    $scope.analyse = function () {
        $scope.loading = true;
        var param = {};
        param.startTime = moment($scope.startDate).format('x');
        param.endTime = moment($scope.endDate).format('x');

        $.when($myhttp.get('api/v1/services', null, function (data) {
            $scope.services = services = data;
            //默认选择第一个
            if (!$scope.serviceId) {
                $scope.serviceId = services[0];
            }
            setTimeout(function () {
                $('#serviceIdSelect').val($scope.serviceId);
            }, 1000);

        })).then(function () {
            $scope.loading = false;
            $location.search('startDate', param.startTime);
            $location.search('endDate', param.endTime);
            $location.search('serviceId', $scope.serviceId);
            loadTraces(param);
        });
    };
    function loadDependencies() {
        $myhttp.get('api/v1/dependencies', {
            endTs: param.endTime,
            lookback: param.endTime - param.startTime,
            serviceName: $scope.serviceId,
            limit: 10000
        }, function (data) {
            makeCallCostChart(data);
        });
    }

    function loadTraces(param) {
        var chart = echarts.init($('#call-cost-chart')[0]);
        chart.showLoading();
        $myhttp.get('api/v1/traces', {
            endTs: param.endTime,
            lookback: param.endTime - param.startTime,
            serviceName: $scope.serviceId,
            limit: 10000
        }, function (data) {
            traces = data;
            console.log(traces);
            makeCallCostChart(traces, chart);
            makeCallCountChart(traces);
        });
    }

    /**
     * 返回耗时最长的调用
     * @param traces
     * @returns {number}
     */
    function statisticCallCost(traces) {
        var callCostStatistics = $scope.callCostStatistics;
        callCostStatistics.total = traces.length;
        callCostStatistics.top5 = _.sortBy(traces, 'duration');
        callCostStatistics.top5 = callCostStatistics.top5.slice(callCostStatistics.top5.length - 5).reverse();
        return callCostStatistics.top5[0] ? callCostStatistics.top5[0].duration : 0;
    }

    /**
     * 对调用耗时数据进行归类
     * @param traces
     */
    function classifyCallCost(traces) {
        var maxDuration = statisticCallCost(traces);
        $scope.callCostLevel[$scope.callCostLevel.length - 1].lt = Math.max($scope.callCostLevel[$scope.callCostLevel.length - 1].gte, maxDuration) + 1;
        var data = {};
        _.each($scope.callCostLevel, function (l) {
            data[l.name] = [];
        });
        _.each(traces, function (t) {
            var level = getCallCostLevel(t.value);
            // _.set(t, 'itemStyle.normal.color', level.color);
            data[level.name].push(t);
        });
        return data;
    }

    $scope.makeTraceUrl = function (trace) {
        if (!trace) {
            return '#';
        }
        return 'http://localhost:7001/traces/' + trace.id + '?serviceName=' + $scope.serviceId;
    };

    function getCallCostLevel(duration) {
        for (var i = 0; i < $scope.callCostLevel.length; i++) {
            if (duration < $scope.callCostLevel[i].lt) {
                return $scope.callCostLevel[i];
            }
        }
    }

    function makeCallCostChart(traces, chart) {
        traces = _.sortBy(traces.map(function (t) {
            return t[0];
        }), 'timestamp');
        traces.forEach(function (t) {
            t.xaxis = moment(t.timestamp / 1000).format('DD.HH:mm:ss');
            t.value = t.duration / 1000;
        });
        $scope.callCostChartData = chartData = classifyCallCost(traces);
        // 指定图表的配置项和数据
        var option = {
            color: _.map($scope.callCostLevel, function (l) {
                return l.color;
            }),
            title: {
                text: '耗时(ms)',
                // subtext: 'Circular layout',
                // top: 'bottom',
                left: 'center'
            },
            // dataZoom: [{
            //     start: 90
            // }, {
            //     type: 'inside'
            // }],
            tooltip: {
                enterable: true,
                formatter: function (params, ticket, callback) {
                    var html = [];
                    html.push('<i class="fa  fa-circle" style="color:' + params.color + '"></i>&nbsp;');
                    html.push(params.data.name + '<br/>');
                    html.push('调用时间:' + moment(params.data.timestamp / 1000).format('YYYY-MM-DD HH:mm:ss') + '<br/>');
                    html.push('耗时:' + params.data.value + 'ms<br/>');
                    html.push('<a href="' + $scope.makeTraceUrl(params.data) + '" target="_blank">查看详情</a>')
                    console.log(params);
                    return html.join('');
                }
            },
            toolbox: toolbox,
            xAxis: [
                {
                    type: 'value',
                    scale: true
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    scale: false
                }
            ],
            legend: [{
                // selectedMode: 'single',
                top: 50,
                right: 20,
                orient: 'vertical',
                data: _.map($scope.callCostLevel, function (a) {
                    return a.name;
                })
            }],
            xAxis: {
                data: traces.map(function (item) {
                    return item['xaxis'];
                })
            },
            yAxis: {
                splitLine: {
                    show: false
                }
            },
            animationDurationUpdate: 1500,
            animationEasingUpdate: 'quinticInOut',
            visualMap: [
                // {
                //     min: 0,
                //     max: $scope.callCostLevel[$scope.callCostLevel.length - 1].lt,
                //     calculable: true,
                //     // orient: 'horizontal',
                //     top: 30,
                //     right: 10,
                //     controller: {
                //         outOfRange: {
                //             color: ['#999'],
                //             symbolSize: [30, 100]
                //         },
                //         inRange: {
                //             color: ['#009966', 'rgba(3,4,5,0.4)', '#']
                //         }
                //     }
                //
                // }
                // ,{
                //     top: 0,
                //     left: 10,
                //     pieces: $scope.callCostLevel,
                //     orient: 'horizontal',
                //     top: 10,
                //     outOfRange: {
                //         color: '#999'
                //     }
                // }
            ],
            series: _.map(chartData, function (data, name) {
                return {
                    name: name,
                    type: 'scatter',
                    data: data
                };
            })
        };
        chart.hideLoading();
// 使用刚指定的配置项和数据显示图表。
        chart.setOption(option);
    }

    /**
     * 服务类别
     * @param services
     * @returns {Array}
     */
    function getCategories(services) {
        var categories = [];
        for (var i = 0; i < services.length; i++) {
            categories[i] = {
                name: services[i]
            };
        }
        return categories;
    }

    function makeChart(denpendencies, services) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init($('#main')[0]);
        var links = [], nodes = [], categories = getCategories(services);
        var callCounter = {}, sendCounter = {};
        _.each(denpendencies, function (item, i) {

            if (item.parent === item.child) {
                return;//排除自身调用
            }
            callCounter[item.child] = (callCounter[item.child] || 0) + item.callCount;
            sendCounter[item.parent] = (callCounter[item.parent] || 0) + item.callCount;
            var link = {
                source: item.parent,
                target: item.child,
                data: {
                    callTotal: item.callCount
                },
                lineStyle: {
                    normal: {
                        width: 2
                    }
                }
            };
            link.id = link.name = item.parent + "->" + item.child;
            links.push(link);
        });

        _.each(categories, function (c, i) {
            var count = callCounter[c.name] || 0;
            nodes.push({
                id: c.name,
                name: c.name,
                category: c.name,
                value: i,
                symbolSize: count,
                label: {
                    normal: {
                        show: false //node.symbolSize > 10
                    }
                },
                draggable: true,
                data: {
                    callTotal: count,
                    sendTotal: sendCounter[c.name] || 0
                }
            })
            ;
        });
        // 对符号大小进行计算
        var maxNode = _.maxBy(nodes, function (n) {
            return n.symbolSize;
        });
        var maxSize = Math.max(maxNode.symbolSize, 5);
        _.each(nodes, function (n) {
            n.symbolSize = calcValWithMaxAndMin(15, 50, n.symbolSize, maxSize);
        });
        // //对连接线大小进行计算
        // var maxLink = _.maxBy(links, function (l) {
        //     return l.data.callTotal;
        // });
        // var maxWidth = Math.max(maxLink.data.callTotal, 5);
        // _.each(links, function (n) {
        //     n.lineStyle.normal.width = calcValWithMaxAndMin(1, 5, n.data.callTotal, maxWidth);
        // });

        // 指定图表的配置项和数据
        var option = {
            // title: {
            //     text: 'Les Miserables',
            //     subtext: 'Circular layout',
            //     top: 'bottom',
            //     left: 'right'
            // },
            tooltip: {
                enterable: true,
                formatter: function (params, ticket, callback) {
                    var html = [];
                    html.push('<i class="fa  fa-circle" style="color:' + params.color + '"></i>&nbsp;');
                    html.push(params.name + '<br/>');
                    if (params.dataType === 'node') {
                        html.push('请求了' + params.data.data.sendTotal + '次<br/>');
                        html.push('被请求了' + params.data.data.callTotal + '次');
                    } else if (params.dataType === 'edge') {
                        html.push('请求了' + params.data.data.callTotal + '次<br/>');
                    }
                    console.log(params);
                    return html.join('');
                }
            },
            // legend: [{
            //     // selectedMode: 'single',
            //     data: categories.map(function (a) {
            //         return a.name;
            //     })
            // }],
            animationDurationUpdate: 1500,
            animationEasingUpdate: 'quinticInOut',
            series: [
                {
                    name: 'Les Miserables',
                    type: 'graph',
                    layout: 'force',//'circular',
                    edgeSymbol: ['none', 'arrow'],
                    force: {
                        repulsion: [2000, 2500]
                    },
                    // circular: {
                    //     rotateLabel: true
                    // },
                    data: nodes,
                    links: links,
                    categories: categories,
                    roam: true,
                    label: {
                        normal: {
                            position: 'right',
                            formatter: '{b}'
                        }
                    },
                    lineStyle: {
                        normal: {
                            color: 'source',
                            curveness: 0.4
                        }
                    }
                }
            ]
        };

// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }

    /**
     * 将给定val对应到变化范围内
     * @param base 基础值
     * @param range 变化范围
     * @param val 当前值
     * @param max 最大值
     */
    function calcValWithMaxAndMin(base, range, val, max) {
        return base + val / max * range;
    }

    $scope.analyse();


    //-------------------------------------------------------------------------------------
    /*服务数量统计*/


    /**
     * 提取具体的服务调用数据
     * @param traces
     */
    function classifyCallCount(traces) {
        var callCountData = {};
        _.each(traces, function (spans) {
            var data = {};
            _.each(spans, function (span) {
                if (!data[span.name]) {
                    data[span.name] = span;
                }
            });
            _.each(data, function (v, k) {
                if (!callCountData[k]) {
                    callCountData[k] = {count: 1, data: [v]};
                } else {
                    callCountData[k].count++;
                    callCountData[k].data.push(v);
                }
            });
        });
        return callCountData;
    }

    var callCountChart = {};

    function statisticCallCount(callCountChart) {
        $scope.callCountStatistics = {total: 0, top5: []};
        var data = _.values(callCountChart);
        $scope.callCountStatistics.total = data.length;
        $scope.callCountStatistics.top5 = _.sortBy(data, 'count').slice(-5).reverse();
    }

    function makeCallCountChart(traces) {
        var chart = echarts.init($('#call-count-chart')[0]);
        chart.showLoading();
        callCountChart = classifyCallCount(traces);
        statisticCallCount(callCountChart);
        var xaxis = [], series = [];
        _.each(callCountChart, function (v, k) {
            if (v.count > 1) {
                series.push(v.count);
                xaxis.push(k);
            }

        });
        var option = {
            title: {
                text: '调用数量(次)',
                // subtext: 'Circular layout',
                // top: 'bottom',
                left: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                enterable: true,
                formatter: function (params, ticket, callback) {
                    params = params[0];
                    var html = [];
                    html.push('<i class="fa  fa-circle" style="color:' + params.color + '"></i>&nbsp;');
                    html.push(params.name + '<br/>');
                    // html.push('调用时间:' + moment(params.data.timestamp / 1000).format('YYYY-MM-DD HH:mm:ss') + '<br/>');
                    html.push('调用:' + params.data + '次<br/>');
                    html.push('<a href="javascript:makeServiceCallDetailChart(\'' + params.name + '\')">查看详情</a>');
                    console.log(params);
                    return html.join('');
                }
            },
            toolbox: toolbox,
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: xaxis,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '访问统计',
                    type: 'bar',
                    barWidth: '60%',
                    data: series
                }
            ]
        };
        chart.hideLoading();
// 使用刚指定的配置项和数据显示图表。
        chart.setOption(option);
    }

    window.makeServiceCallDetailChart = $scope.makeServiceCallDetailChart = function (serviceName) {
        var chart = echarts.init($('#call-service-detail')[0]);
        var data = _.sortBy(callCountChart[serviceName].data, 'timestamp');
        if (!data || !data.length) {
            alert('没有调用数据可展示!');
            return;
        }
        _.each(data, function (t) {
            t.xaxis = moment(t.timestamp / 1000).format('DD.HH:mm:ss');
            t.value = t.duration / 1000;
        });
        var max = _.maxBy(data, function (d) {
                return d.value;
            }), min = _.minBy(data, function (d) {
                return d.value;
            }), avg = _.sumBy(data, function (d) {
                    return d.value
                }) / data.length,
            group = _.groupBy(data, function (d) {
                return d.value > avg ? 'gt' : 'lte';
            });
        avg = avg.toFixed(2) - 0;
        $scope.callCostDetail = {
            max: max,
            min: min,
            avg: avg,
            total: data.length,
            gt: group.gt ? group.gt.length : 0,
            lte: group.lte ? group.lte.length : 0
        };
        var pieces = [
            {
                gte: 0,
                lt: avg,
                label: '平均值及以下',
                color: '#096'
            },
            {
                gte: avg,
                lte: max.value,
                label: '平均值之上',
                color: '#c03'
            }
        ];
        var option = {
            title: {
                text: serviceName,
                left: 'center'
            },
            toolbox: toolbox,
            tooltip: {
                trigger: 'axis',
                enterable: true,
                formatter: function (params, ticket, callback) {
                    params = params[0];
                    if (!params) {
                        return '';
                    }
                    var html = [];
                    html.push('<i class="fa  fa-circle" style="color:' + params.color + '"></i>&nbsp;');
                    html.push(params.name + '<br/>');
                    // html.push('调用时间:' + moment(params.data.timestamp / 1000).format('YYYY-MM-DD HH:mm:ss') + '<br/>');
                    html.push('耗时:' + params.data.value + 'ms<br/>');
                    html.push('<a href="' + $scope.makeTraceUrl(params.data) + '" target="_blank">查看详情</a>');
                    console.log(params);
                    return html.join('');
                },
                axisPointer: {
                    animation: false
                }
            },
            visualMap: [{
                type: 'piecewise',
                left: 10,
                pieces: pieces,
                orient: 'horizontal',
                top: 10,
                outOfRange: {
                    color: '#999',
                    opacity: 0,
                    colorAlpha: 0
                }
            }],
            xAxis: [{
                type: 'category',
                splitLine: {
                    show: false
                },
                data: _.map(data, function (d) {
                    return d.xaxis;
                })
            }],
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%'],
                splitLine: {
                    show: false
                }
            },
            series: [{
                name: '调用数据',
                type: 'line',
                // showSymbol: false,
                // hoverAnimation: false,
                data: data,
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    lineStyle: {
                        normal: {
                            type: 'solid',
                            color: '#ffde33'
                        }
                    },
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }]
        };
        chart.setOption(option);
        $scope.$digest();
    }
});