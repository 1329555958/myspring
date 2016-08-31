/**
 * Created by weichunhe on 2016/8/29.
 */
require("app").register.controller("ServiceDetailController", function ($scope, $timeout, $myhttp, $location) {
    var searchParam = $location.search();
    $scope.serviceId = searchParam.serviceId;
    $scope.startDate = new Date(searchParam.startDate - 0 || (new Date().getTime() - 7 * 24 * 3600000));
    $scope.endDate = searchParam.endDate ? new Date(searchParam.endDate - 0) : new Date();
    $scope.callCostStatistics = {total: 0, level1Total: 0, level2Total: 0, level3Total: 0};
    $scope.callLevel = {
        level1: {
            gte: 0,
            lt: 3000,
            name: '0-3000'
        },
        level2: {
            gte: 3000,
            lt: 5000,
            name: '3000-5000'
        },
        level3: {
            gte: 5000,
            name: '≥5000'
        }
    }
    ;
    var services = $scope.services = [], denpendencies = [], callCostChartData = [];

    var moment = require('moment');

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
        });
    }

    function statisticCallCost(traces) {
        var callCostStatistics = $scope.callCostStatistics;
        callCostStatistics.total = traces.length;
        callCostStatistics.top5 = _.sortBy(traces, 'duration');
        callCostStatistics.top5 = callCostStatistics.top5.slice(callCostStatistics.top5.length - 5).reverse();
        callCostStatistics.level1Total = 0; //0 -1000 毫秒
        callCostStatistics.level2Total = 0;
        callCostStatistics.level3Total = 0;
        traces.forEach(function (t) {
            if (t.duration < $scope.callLevel.level1.lt) {
                callCostStatistics.level1Total++;
            } else if (t.duration < $scope.callLevel.level2.lt) {
                callCostStatistics.level2Total++;
            } else {
                callCostStatistics.level3Total++;
            }
        });
    }

    $scope.makeTraceUrl = function (trace) {
        return 'http://localhost:7001/traces/' + trace.id + '?serviceName=' + $scope.serviceId;
    };

    function getCallCostLevelName(duration) {
        if (duration < $scope.callLevel.level1.lt) {
            return $scope.callLevel.level1.name;
        } else if (duration < $scope.callLevel.level2.lt) {
            return $scope.callLevel.level2.name;
        } else {
            return $scope.callLevel.level3.name;
        }
    }

    function makeCallCostChart(traces, chart) {
        traces = _.sortBy(traces.map(function (t) {
            return t[0];
        }), 'timestamp');


        traces.forEach(function (t) {
            t.xaxis = moment(t.timestamp / 1000).format('DD.HH:mm:ss');
            t.value = t.duration;
            t.category = getCallCostLevelName(t.duration);
        });

        statisticCallCost(traces);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '耗时(ms)',
                // subtext: 'Circular layout',
                // top: 'bottom',
                left: 'center'
            },
            dataZoom: [{
                start: 90
            }, {
                type: 'inside'
            }],
            tooltip: {
                enterable: true,
                formatter: function (params, ticket, callback) {
                    var html = [];
                    html.push('<i class="fa  fa-circle" style="color:' + params.color + '"></i>&nbsp;');
                    html.push(params.data.name + '<br/>');
                    html.push('调用时间:' + moment(params.data.timestamp / 1000).format('YYYY-MM-DD HH:mm:ss') + '<br/>');
                    html.push('耗时:' + params.data.duration + 'ms<br/>');
                    html.push('<a href="' + $scope.makeTraceUrl(params.data) + '" target="_blank">查看详情</a>')
                    console.log(params);
                    return html.join('');
                }
            },
            legend: [{
                // selectedMode: 'single',
                data: _.map($scope.callLevel, function (a) {
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
            // visualMap: {
            //     min: 0,
            //     max: 10000000,
            //     calculable: true,
            //     orient: 'horizontal',
            //     top: 10,
            //     right: 10
            // },
            visualMap: {
                top: 10,
                right: 10,
                pieces: [{
                    gte: $scope.callLevel.level1.gte,
                    lt: $scope.callLevel.level1.lt,
                    color: '#096'
                }, {
                    gte: $scope.callLevel.level2.gte,
                    lt: $scope.callLevel.level2.lt,
                    color: '#ffde33'
                }, {
                    gte: $scope.callLevel.level3.gte,
                    color: '#cc0033'
                }],
                outOfRange: {
                    color: '#999'
                }
            },
            series: [
                {
                    name: 'Les Miserables',
                    type: 'scatter',
                    // layout: 'force',//'circular',
                    // edgeSymbol: ['none', 'arrow'],
                    // force: {
                    //     repulsion: [2000, 2500]
                    // },
                    // circular: {
                    //     rotateLabel: true
                    // },
                    data: traces,
                    // links: links,
                    categories: _.map($scope.callLevel, function (l) {
                        return l.name;
                    }), roam: true,
                    label: {
                        normal: {
                            position: 'right',
                            formatter: '{b}'
                        }
                    }
                    ,
                    lineStyle: {
                        normal: {
                            color: 'source',
                            curveness: 0.4
                        }
                    }
                    ,
                    markLine: {
                        silent: true,
                        data: [{
                            yAxis: $scope.callLevel.level1.lt
                        }, {
                            yAxis: $scope.callLevel.level2.lt
                        }]
                    }
                }
            ]
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
});